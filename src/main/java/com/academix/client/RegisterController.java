package com.academix.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    private MainApplication mainApplication;

    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private CheckBox schoolEmployeeCheckbox;

    @FXML
    private Button registerButton;

    @FXML
    private Hyperlink goToLoginHyperlink;


    @FXML
    private void initialize() {
        // You can add initialization logic here if needed
    }

    @FXML
    private void goToQuiz() {
        // Implement the action to go to the quiz page
    }

    public void switchToLogin(ActionEvent actionEvent) {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    // You can add more methods and fields as needed
}