package com.example.librosapi.ui.main;

import android.net.Uri;

import com.example.librosapi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class LibrosAPIClient {


    ArrayList<Libro> getLibros() {
        String BASE_URL = "https://openlibrary.org/people/seabelis/lists/OL203940L/picks/export?format=json";

        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon().
                //appendPath("works").
                        build();

        String url = builtUri.toString();
        ArrayList<Libro> response = doCall(url);

        return response;
    }

    private ArrayList<Libro> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Libro> processJson(String jsonResponse) {
        ArrayList<Libro> libros = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonLibros = data.getJSONArray("works");
            for (int i = 0; i < jsonLibros.length(); i++) {
                JSONObject jsonLibro = jsonLibros.getJSONObject(i);

                Libro libro = new Libro();

                try {
                    libro.setTitle(jsonLibro.getString("title"));
                } catch (JSONException ex) {
                    libro.title = "sin titulo";
                }
                try {
                    libro.setFirst_publish_date(jsonLibro.getString("first_publish_date"));
                } catch (JSONException ex) {
                    libro.first_publish_date = "Fecha de publicación no encontrada";
                }
                try {
                    libro.setAuthors(jsonLibro.getString("authors"));
                } catch (JSONException ex) {
                    libro.authors = "sin autor";
                }
                try {
                    libro.setDescription(jsonLibro.getString("description"));
                } catch (JSONException ex) {
                    libro.authors = "sin descripcion";
                }

                //libro.setBase_cover(jsonLibro.getString("base_cover"));

                libros.add(libro);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

        return libros;
    }
}
