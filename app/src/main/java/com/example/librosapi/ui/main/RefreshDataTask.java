package com.example.librosapi.ui.main;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

public class RefreshDataTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids){

        LibrosAPIClient api =  new LibrosAPIClient();

        ArrayList<Libro> libros = api.getLibros();

        Log.d(null,libros.toString());

        return null;

    }
}
