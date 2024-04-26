package com.academix.client.controllers;

import com.academix.client.MainApplication;
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
import language.LocaleManager;
import server.logging.Logging;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {
    private MainApplication mainApplication;
    private LocaleManager localeManager;

    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink goToRegisterHyperlink;

    @FXML
    private Button skToggleButton;

    @FXML
    private Button enToggleButton;

    @FXML
    private void initialize() {
        // You can add initialization logic here if needed
        localeManager = LocaleManager.getInstance();
//        localeManager.setLocale(new Locale("sk"));

        ResourceBundle messages = localeManager.getMessages();
        usernameTextfield.setPromptText(messages.getString("username"));
        passwordField.setPromptText(messages.getString("password"));
        goToRegisterHyperlink.setText(messages.getString("noaccount"));
        loginButton.setText(messages.getString("login"));
    }

    @FXML
    private void login() {
        // Implement the login logic here
    }

    @FXML
    private void skLanguage(){
        localeManager.setLocale(new Locale("SK"));
        updateUI();
    }

    @FXML
    private void enLanguage() {
        localeManager.setLocale(new Locale("EN"));
        updateUI();
    }

    private void updateUI() {
        ResourceBundle messages = localeManager.getMessages();
        usernameTextfield.setPromptText(messages.getString("username"));
        passwordField.setPromptText(messages.getString("password"));
        goToRegisterHyperlink.setText(messages.getString("noaccount"));
        loginButton.setText(messages.getString("login"));
    }

    public void login(ActionEvent actionEvent) {
        try {
            mainApplication.loadHomeStudentPane();
//            mainApplication.loadHomeFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void switchToRegister(ActionEvent actionEvent) {
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
