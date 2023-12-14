package com.example.moviesapi;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SearchViewController{

    @FXML
    private Button fetchAllButton;

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

    private int page, totalNumberOfMovies;

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
        page = 1;

        String movieName = searchTextField.getText().trim();
        APIResponse apiResponse = APIUtility.callAPI(movieName,page);
        totalNumberOfMovies = Integer.parseInt(apiResponse.getTotalResults());
        //Handling exception where if the user clicks search button without entering any text in the search field
        if (apiResponse.getMovies() != null){
            titlesVBox.setVisible(true);
            listView.getItems().clear();
            listView.getItems().addAll(apiResponse.getMovies());
            updateLabels();
        }
        else {
            titlesVBox.setVisible(false);
            msgLabel.setVisible(true);
            msgLabel.setText("Enter a movie title and click \"Search\"");
        }
        }

        private void updateLabels()
    {
        //Setting the text of the label to show the results showing
        resultsMsgLabel.setText("Showing " + listView.getItems().size() + " of " + totalNumberOfMovies);
        if (listView.getItems().size() < totalNumberOfMovies)
        {
            fetchAllButton.setVisible(true);
        }
        else
        {
            fetchAllButton.setVisible(false);
        }
    }

    @FXML
    void getMovieDetails(ActionEvent event) throws IOException {
        Movie movieSelected = listView.getSelectionModel().getSelectedItem();
        SceneChanger.changeScenes(event, "info-view.fxml", movieSelected.getImdbID());
    }

    @FXML
    void fetchAllMovies(){
        Thread fetchThread = new Thread(()-> {
            progressBar.setVisible(true);
            //Whatever goes here is the run method
            page++;
            try {
                APIResponse apiResponse = APIUtility.callAPI(searchTextField.getText().trim(),page);
                listView.getItems().addAll(apiResponse.getMovies());
                Platform.runLater(()->{
                    updateLabels();
                    progressBar.setProgress((double) listView.getItems().size()/totalNumberOfMovies);
                }); //When the java fx file is available update it
            } catch (IOException  | InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (listView.getItems().size()<totalNumberOfMovies)
                fetchAllMovies();
            else
                progressBar.setVisible(false);
        });
        fetchThread.start();
    }
}
