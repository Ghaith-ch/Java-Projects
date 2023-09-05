package com.example.Labb4_JSON_Movies;

public class Movie {

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    private String id;
    private String title;
    private String url;


    public Movie(String id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url=url;
    }

    @Override
    public String toString() {
        return  "Title: " + title + "\n" +
                "Id: " + id + "\n" +
                "Url: " + url;
    }
}
