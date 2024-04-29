package com.academix.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import language.LocaleManager;

import java.util.Locale;

public class NotificationController {
    @FXML
    private Label errorText;
    @FXML
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
