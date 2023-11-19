module com.example.moviesapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
                            
    opens com.example.moviesapi to javafx.fxml;
    exports com.example.moviesapi;
}