package com.academix.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NotificationController {
    @FXML
    private Label errorText;

    private Stage stage;
    public void initialize(Stage stage, String text){
        this.stage = stage;
        errorText.setText(text);

    }
    @FXML
    private void close() {
        stage.close();
    }
}
