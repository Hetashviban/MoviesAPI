package com.example.moviesapi;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import javafx.beans.binding.StringExpression;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class APIUtility {
    /*This method will call the ODMb API from the with the movie title passed as an argument*/
    public static APIResponse callAPI(String movieName) throws IOException, InterruptedException {
        //If we received "Star Wars", we need to translate that to be "Star%20Wars"
        movieName = movieName.replace(" ", "%20"); /*This will replace all the space with %20*/

        String uri = "https://www.omdbapi.com/?apikey=f54d92f0&s="+movieName;

        //configure the environment to make a HTTP request
        //(this includes an update to the module-info.java file)
        HttpClient client = HttpClient.newHttpClient();
        //The below requests the API code
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        //this will save a file called movies.json file with the API's response
        //This will response to the http request
//        HttpResponse<Path> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers
//                .ofFile(Paths.get("movies.json")));

        //We are converting to a string so that we can input it in the resultbox
        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers
                                            .ofString());

        Gson gson = new Gson();
        return gson.fromJson(httpResponse.body(), APIResponse.class);
    }

    /*We will use this method to get the individual info about the movie selected using imdbID*/
    public static MovieDetails getMovieDetails(String imdbID) throws IOException, InterruptedException {
        //If we received "Star Wars", we need to translate that to be "Star%20Wars"
        imdbID = imdbID.trim().replaceAll(" ", "%20"); /*This will replace all the space with %20*/

        String uri = "https://www.omdbapi.com/?apikey=f54d92f0&i="+imdbID;

        //configure the environment to make a HTTP request
        //(this includes an update to the module-info.java file)
        HttpClient client = HttpClient.newHttpClient();
        //The below requests the API code
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        //this will save a file called movies.json file with the API's response
        //This will response to the http request
//        HttpResponse<Path> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers
//                .ofFile(Paths.get("movies.json")));

        //We are converting to a string so that we can input it in the resultbox
        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers
                .ofString());

        Gson gson = new Gson();
        return gson.fromJson(httpResponse.body(), MovieDetails.class);
    }


    //We will use this if our json file is not in the form of objects but in the form of array
//    public static Movie[] getMoviesFromFile(String fileName)
//    {
//        Gson gson = new Gson();
//        //this is called try...with resources when we use the ().
//        //anything created inside the ( ) will automatically have the .close() called once
//        //the resource is not required.
//        try(
//                FileReader fileReader = new FileReader(fileName);
//                JsonReader jsonReader = new JsonReader(fileReader);
//        )
//        {
//            return gson.fromJson(jsonReader, Movie[].class);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
