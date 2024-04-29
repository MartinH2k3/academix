package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.Notification;
import com.academix.client.UserTypeEnum;
import com.academix.client.requests.RequesterUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import language.LocaleManager;
import server.logging.Logging;

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

        ResourceBundle messages = localeManager.getMessages();

        usernameTextfield.setPromptText(messages.getString("username"));
        passwordField.setPromptText(messages.getString("password"));
        loginButton.setText(messages.getString("login"));
        goToRegisterHyperlink.setText(messages.getString("noaccount"));
    }

    private String loginCheck() {
        String username = usernameTextfield.getText().trim();
        String password = passwordField.getText().trim();
        Notification notification = Notification.getInstance();

        if (username.isEmpty() || password.isEmpty()) {
            if(localeManager.getLocale().equals(new Locale("SK"))){
                notification.showNotification("Nezadali ste meno alebo heslo.");
            } else {
            notification.showNotification("Username or password were not entered");}
            Logging.getInstance().logServerWarning("Meno alebo heslo neboli pri prihlasovaní vyplnené.");
            passwordField.setText("");
            return null;
        }
        String response = RequesterUser.getInstance().login(username, password);
        if (response.equals("Incorrect username or password")) {
            if(localeManager.getLocale().equals(new Locale("SK"))){
                notification.showNotification("Nesprávne meno alebo heslo.");
            } else {
            notification.showNotification("Incorrect username or password");}
            Logging.getInstance().logServerWarning("Meno alebo heslo nie sú správne.");
            passwordField.setText("");
            return null;
        } else {
            mainApplication.setLoggedInUser(username);
            return response;
        }
    }

    @FXML
    private void login() {
        String userType = loginCheck();
        if (userType != null){
            if (UserTypeEnum.ADMIN.toString().equals(userType.toUpperCase())) {
                try {
                    mainApplication.loadHomeAdmin();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }else if(UserTypeEnum.FACULTY_REPRESENTATIVE.toString().equals(userType.toUpperCase())) {
                try {
                    mainApplication.loadHomeFaculty();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }else if(UserTypeEnum.STUDENT.toString().equals(userType.toUpperCase())){
                try {
                    mainApplication.loadHomeStudentPane();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
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
    @FXML
    private void skLanguage() {
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
        loginButton.setText(messages.getString("login"));
        goToRegisterHyperlink.setText(messages.getString("noaccount"));
    }

    // You can add more methods and fields as needed
}
