package com.example.moviesapi;

import javafx.beans.binding.StringExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {
    public static void changeScenes(ActionEvent event, String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        //get the stage from the ActionEvent
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //We copy-pasted the above method and added an argument
    //Overloading means they have the same method but different argument set
    public static void changeScenes(ActionEvent event, String fxmlFileName, String imdbID) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        //get the Controller for the destination view and call the "movieLoad()" method
        MovieLoader controller = fxmlLoader.getController();
        controller.loadMovie(imdbID);

        //get the stage from the ActionEvent
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
