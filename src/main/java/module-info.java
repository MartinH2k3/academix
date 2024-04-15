module com.academix.academix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.httpserver;
    requires com.google.gson;


    opens com.academix.client to javafx.fxml;
    exports com.academix.client;
}