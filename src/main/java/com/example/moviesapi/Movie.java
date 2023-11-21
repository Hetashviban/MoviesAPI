package com.example.moviesapi;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("Title") //We will make sure that the bracket in the serialized name is spelt the same way as in our json file
    private String title; //We can name this however we want, as we have spelt it in the serialized name

    @SerializedName("Year")
    private String year;

    private String imdbID;

    @SerializedName("Type")
    private String type;
    @SerializedName("Poster")
    private String poster;

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

    public String toString()
    {
        return String.format("%s-%s", year, title);
    }
}
