package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import server.logging.Logging;

public class AccountsController {
    @FXML
    private VBox allUsers;
    private MainApplication mainApplication;
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
    public void initialize(){
        //for (:) {

        //}

    }
    @FXML
    private void goToRequests() {
        try {
            mainApplication.loadRequests();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToQuestionsFromUsers() {
        try {
            mainApplication.loadQuestionsFromUsers();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccounts() {
        try {
            mainApplication.loadAccounts();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsAdmin("acc");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void signOut(ActionEvent actionEvent) {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
}
