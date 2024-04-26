package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class HomeAdminController {
    private MainApplication mainApplication;
    @FXML
    private Hyperlink signOutHyperlink;
    @FXML
    private Hyperlink accountSettingsHyperlink;

    @FXML
    private void signOut() {
    }

    @FXML
    private void goToQuestionsFromUsers() {
    }

    @FXML
    private void goToAccounts() {
    }

    @FXML
    private void goToAccountSettings() {
    }

    @FXML
    private void goToRequests() {
    }

    public void setMainApp(MainApplication mainApplication){
        this.mainApplication = mainApplication;
    }
}
