package com.example.moviesapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Movies R Us!");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/Logo.png")));
        stage.setScene(scene);
        stage.show();

    }
}