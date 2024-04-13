package com.academix.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private MainApplication mainApplication;

    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink goToRegisterHyperlink;

    @FXML
    private void initialize() {
        // You can add initialization logic here if needed
    }

    @FXML
    private void login() {
        // Implement the login logic here
    }

    @FXML
    private void goToRegisterHyperlink() {
        try {
            mainApplication.loadRegisterPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(ActionEvent actionEvent) {
    }

    public void switchToRegister(ActionEvent actionEvent) {
        goToRegisterHyperlink();
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    // You can add more methods and fields as needed
}
