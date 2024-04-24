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

    opens common to com.google.gson;
    exports common.dto;

}