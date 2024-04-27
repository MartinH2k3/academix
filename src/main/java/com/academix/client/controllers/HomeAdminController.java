package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import language.LocaleManager;
import server.logging.Logging;

import java.util.ResourceBundle;

public class HomeAdminController {
    private MainApplication mainApplication;

    private LocaleManager localeManager;
    @FXML
    private Hyperlink requestsHyperlink;

    @FXML
    private Hyperlink questionsHyperlink;

    @FXML
    private Hyperlink accountsHyperlink;

    @FXML
    private Hyperlink accountSettingsHyperlink;

    @FXML
    private Hyperlink signOutHyperlink;

    @FXML
    private void initialize() {
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        requestsHyperlink.setText(messages.getString("requests"));
        questionsHyperlink.setText(messages.getString("user_questions"));
        accountsHyperlink.setText(messages.getString("accounts"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        signOutHyperlink.setText(messages.getString("sign_out"));
    }
    @FXML
    private void signOut() {

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
            mainApplication.loadAccountSettingsAdmin("home");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToRequests() {
        try {
            mainApplication.loadRequests();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void setMainApp(MainApplication mainApplication){
        this.mainApplication = mainApplication;
    }
}
