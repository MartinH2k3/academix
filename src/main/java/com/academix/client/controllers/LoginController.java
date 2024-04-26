package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.logging.Logging;

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


    private String loginCheck() {
        String username = usernameTextfield.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Logging.getInstance().logServerWarning("Meno alebo heslo neboli pri prihlasovaní vyplnené.");
        }
        String response = RequesterUser.getInstance().login(username, password);
        if (response != null) {
            mainApplication.logged_in_user = username;
            return response;
        } else {
            Logging.getInstance().logServerWarning("Meno alebo heslo nie sú správne.");
            return null;
        }
    }

    @FXML
    private void login() {
        String userType = loginCheck();
        if (userType != null){
            try {
                mainApplication.loadHomeStudentPane();
            } catch (Exception e) {
                Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
           }
        }
    }
    @FXML
    private void switchToRegister(ActionEvent actionEvent) {
        try {
            mainApplication.loadRegisterPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    // You can add more methods and fields as needed
}
