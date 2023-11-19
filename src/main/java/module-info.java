module com.example.moviesapi {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.moviesapi to javafx.fxml;
    exports com.example.moviesapi;
}