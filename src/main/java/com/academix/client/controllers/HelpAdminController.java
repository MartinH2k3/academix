package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import language.LocaleManager;
import server.logging.Logging;

import java.util.ResourceBundle;

public class HelpAdminController {
    private MainApplication mainApplication;
    private LocaleManager localeManager;
    @FXML
    private Button cancelButton;
    @FXML
    private Button sendButton;
    @FXML
    private Label questionLabel;
    @FXML
    private TextArea answerTextArea;
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
    public void initialize(){
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        requestsHyperlink.setText(messages.getString("requests"));
        questionsHyperlink.setText(messages.getString("user_questions"));
        accountsHyperlink.setText(messages.getString("accounts"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        cancelButton.setText(messages.getString("cancel"));
        sendButton.setText(messages.getString("send_button"));

        answerTextArea.setPromptText(messages.getString("answer"));
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
