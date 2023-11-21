package com.example.moviesapi;

import com.google.gson.Gson;
import javafx.beans.binding.StringExpression;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers
                                            .ofString());

        Gson gson = new Gson();
        return gson.fromJson(httpResponse.body(), APIResponse.class);
    }
}
