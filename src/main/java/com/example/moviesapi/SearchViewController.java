package com.example.moviesapi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SearchViewController {

    @FXML
    private ListView<Movie> listView;

    @FXML
    private Label msgLabel;

    @FXML
    private ImageView posterImageView;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private HBox resultsBox;

    @FXML
    private Label resultsMsgLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private VBox selectedVBox;

    @FXML
    private VBox titlesVBox;

    @FXML
    private void initialize()
    {
        progressBar.setVisible(false);
        selectedVBox.setVisible(false);
        titlesVBox.setVisible(false);
        msgLabel.setVisible(false);

        //This will add the movie poster
        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, movieSelected) -> {
                    //The if else statements - when a movie poster is selected and when a user searches for a new movie - the old poster visibility will be set to false
                    if (movieSelected != null) {
                        selectedVBox.setVisible(true);
                        //When there is no poster of a particular move available - it adds a default image poster
                        try {
                            posterImageView.setImage(new Image(movieSelected.getPoster()));
                        }
                        catch (IllegalArgumentException e){
                            posterImageView.setImage(new Image(Main.class.getResourceAsStream("images/default_poster.png")));
                        }
                    } else {
                        selectedVBox.setVisible(false);
                    }
                });
    }

    @FXML
    void searchForMovies(ActionEvent event) throws IOException, InterruptedException {
        String movieName = searchTextField.getText().trim();
        APIUtility.callAPI(movieName);
        APIResponse apiResponse = APIUtility.callAPI(movieName);

        //Handling exception where if the user clicks search button without entering any text in the search field
        if (apiResponse.getMovies() != null){
            titlesVBox.setVisible(true);
            listView.getItems().clear();
            listView.getItems().addAll(apiResponse.getMovies());

            //Setting the text of the label to show the results showing
            resultsMsgLabel.setText("Showing " + listView.getItems().size() + " of " + apiResponse.getTotalResults());
            msgLabel.setVisible(false);
        }
        else {
            titlesVBox.setVisible(false);
            msgLabel.setVisible(true);
            msgLabel.setText("Enter a movie title and click \"Search\"");
        }
        }

    @FXML
    void getMovieDetails(ActionEvent event) throws IOException {
        Movie movieSelected = listView.getSelectionModel().getSelectedItem();
        SceneChanger.changeScenes(event, "info-view.fxml", movieSelected.getImdbID());
    }
}
