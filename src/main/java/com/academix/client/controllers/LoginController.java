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
        Notification notification = Notification.getInstance();

        if (username.isEmpty() || password.isEmpty()) {
            notification.showNotification("Username or password were not entered");
            Logging.getInstance().logServerWarning("Meno alebo heslo neboli pri prihlasovaní vyplnené.");
            passwordField.setText("");
            return null;
        }
        String response = RequesterUser.getInstance().login(username, password);
        if (response.equals("Incorrect username or password")) {
            notification.showNotification("Incorrect username or password");
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
    }
    @FXML
    private void enLanguage() {
    }

    // You can add more methods and fields as needed
}
