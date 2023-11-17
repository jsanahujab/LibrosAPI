package com.example.librosapi.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.librosapi.R;

import java.util.List;

public class LibroAdapter extends ArrayAdapter<Libro>  {

    public LibroAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Libro> objects) {
        super(context, resource, textViewResourceId, objects);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Libro libro = getItem(position);

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_libros_row,parent,false);
        }

        TextView titulo = convertView.findViewById(R.id.txttitulo);
        TextView publishdate = convertView.findViewById(R.id.txtpublishdate);
        //TextView autor = convertView.findViewById(R.id.txtautor);
        TextView descripcion = convertView.findViewById(R.id.txtdescripcion);


        ImageView imagen = convertView.findViewById(R.id.imgportada);

        titulo.setText(libro.getTitle());
        publishdate.setText(libro.getFirst_publish_date());
        descripcion.setText(libro.getDescription());
        //autor.setText(libro.getAuthors());
        Glide.with(getContext()).load("https://covers.openlibrary.org" + libro.getBase_cover()).into(imagen);

        return convertView;
    }
}
