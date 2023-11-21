package com.example.moviesapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class APIResponse {
    private String totalResults;
    @SerializedName("Response")
    private String response;

    @SerializedName("Search")
    private ArrayList<Movie> movies;

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}
