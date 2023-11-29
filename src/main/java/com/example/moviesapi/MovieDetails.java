package com.example.moviesapi;

import com.google.gson.annotations.SerializedName;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieDetails {
    @SerializedName("Genre")
    private String genre;

    @SerializedName("Poster")//This name has to match the name in the json file
    private String image;

    @SerializedName("Language")
    private String language;

    @SerializedName("Plot")
    private String plot;
    @SerializedName("Rated")
    private String rated;
    @SerializedName("Ratings")
    private ArrayList<MovieRatings> ratings;
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;

    public String getGenre() {
        return genre;
    }

    public String getImage() {
        return image;
    }

    public String getLanguage() {
        return language;
    }

    public String getPlot() {
        return plot;
    }

    public String getRated() {
        return rated;
    }

    public ArrayList<MovieRatings> getRatings() {
        return ratings;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }
}
