module com.academix.academix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.httpserver;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires jdk.compiler;


    opens com.academix.client to javafx.fxml;
    exports com.academix.client;
    exports com.academix.client.controllers;
    opens com.academix.client.controllers to javafx.fxml;

    opens common to com.google.gson;
    exports common.dto;
    exports com.academix.client.requests;
    opens com.academix.client.requests to javafx.fxml;
}