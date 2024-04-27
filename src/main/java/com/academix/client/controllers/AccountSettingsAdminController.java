package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.logging.Logging;

public class AccountSettingsAdminController {
    private MainApplication mainApplication;
    private String back;
    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField repeatNewPasswordField;
    @FXML
    private TextField emailTextfield;
    @FXML
    private TextField phoneNumberTextfield;
    @FXML
    private TextField lastNameTextfield;
    @FXML
    private TextField firstNameTextfield;

    @FXML
    private void goToRequests() {
    }

    public void setBack(String back){
        this.back = back;

    }
    @FXML
    private void goToQuestionsFromUsers() {
        try{
            mainApplication.loadQuestionsFromUsers();
        }catch (Exception e){
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToAccounts() {
    }

    @FXML
    private void goToAccountSettings() {
    }

    @FXML
    private void signOut() {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goBack() {
        switch (back) {
            case "questions" -> {
                try {
                    mainApplication.loadQuestionsFromUsers();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "req" -> {
                try {
                    mainApplication.loadRequests();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "acc" -> {
                try {
                    mainApplication.loadAccounts();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            default -> {
                try {
                    mainApplication.loadHomeFaculty();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
        }
    }

    @FXML
    private void saveChanges(ActionEvent actionEvent) {
    }
    public void setMainApp(MainApplication mainApplication){
        this.mainApplication = mainApplication;
    }

}
