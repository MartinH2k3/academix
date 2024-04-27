package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import server.logging.Logging;

public class HelpAdminController {
    private MainApplication mainApplication;
    @FXML
    private Button cancelButton;
    @FXML
    private Button sendButton;
    @FXML
    private Label questionLabel;
    @FXML
    private TextArea answerTextArea;
    public void initialize(){

    }
    @FXML
    private void signOut( ) {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsAdmin("questions");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccounts(ActionEvent actionEvent) {
        try {
            mainApplication.loadAccounts();
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
    private void goToRequests() {
        try {
            mainApplication.loadRequests();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    private void setMainApp(MainApplication mainApplication){
        this.mainApplication = mainApplication;
    }
    public void setText(String text){
        questionLabel.setText(text);
    }
}
