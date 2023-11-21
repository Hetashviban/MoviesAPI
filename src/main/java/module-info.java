module com.example.moviesapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;
                            
    opens com.example.moviesapi to javafx.fxml, com.google.gson;
    exports com.example.moviesapi;
}