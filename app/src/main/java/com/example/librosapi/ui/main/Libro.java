package com.example.librosapi.ui.main;

import java.util.ArrayList;

public class Libro {


    String description;
    String title;
    String authors;
    String first_publish_date;

    String base_cover;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getFirst_publish_date() {
        return first_publish_date;
    }

    public void setFirst_publish_date(String first_publish_date) {
        this.first_publish_date = first_publish_date;
    }

    public String getBase_cover() {
        return base_cover;
    }

    public void setBase_cover(String base_cover) {
        this.base_cover = base_cover;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", first_publish_date='" + first_publish_date + '\'' +
                ", base_cover='" + base_cover + '\'' +
                '}';
    }
}
