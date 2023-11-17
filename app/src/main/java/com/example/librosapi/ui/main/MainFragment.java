package com.example.librosapi.ui.main;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.librosapi.R;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private ArrayList<Libro> items;
    private LibroAdapter adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id== R.id.refresh) {
            refresh();
        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        items =  new ArrayList<> ();

        adapter = new LibroAdapter(
                getContext(),
                R.layout.lv_libros_row,
                R.id.txttitulo,
                items
        );

        ListView lvlibros = view.findViewById(R.id.lvLibros);

        lvlibros.setAdapter(adapter);

        return view;
    }

    void refresh(){

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            LibrosAPIClient api = new LibrosAPIClient();
            ArrayList<Libro> libros = api.getLibros();
            handler.post(() -> {
                adapter.clear();
                for (Libro libro : libros) {
                    adapter.add(libro);
                }
            });
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }
}