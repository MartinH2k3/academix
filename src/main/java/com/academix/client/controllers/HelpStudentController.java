package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import language.LocaleManager;
import server.logging.Logging;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelpStudentController {
    private MainApplication mainApplication;

    private LocaleManager localeManager;
    @FXML
    private Hyperlink pastResultHyperlink;

    @FXML
    private Hyperlink takeQuizHyperlink;

    @FXML
    private Hyperlink catalogHyperlink;

    @FXML
    private Hyperlink accountSettingsHyperlink;

    @FXML
    private Hyperlink helpHyperlink;

    @FXML
    private Hyperlink signOutHyperlink;

    @FXML
    private Text takeALookText;

    @FXML
    private TextField messageTextField;

    @FXML
    private Button sendMessageButton;

    @FXML
    private Text sentSuccessfullyText;

    @FXML
    private TextField subjectTextField;

    public void initialize() {
        sentSuccessfullyText.setVisible(false);

        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        pastResultHyperlink.setText(messages.getString("past_results"));
        takeQuizHyperlink.setText(messages.getString("take_quiz"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        subjectTextField.setPromptText(messages.getString("help_subject"));
        messageTextField.setPromptText(messages.getString("help_message"));
        takeALookText.setText(messages.getString("help_contact_admin"));

        sendMessageButton.setText(messages.getString("send_button"));
    }

    @FXML
    void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsStudentPane("help");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void goToCatalog() {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void goToHelp() {
        try {
            mainApplication.loadHelpStudent();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void goToQuiz() {
        try {
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void SendHelpMessage() {
        RequesterUser.getInstance().sendQuestion(mainApplication.getLoggedInUser(), subjectTextField.getText(), messageTextField.getText());
        sentSuccessfullyText.setVisible(true);
    }

    @FXML
    void signOut() {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }


    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    private void switchToReceivedHelp( ) {
        try {
            mainApplication.loadAnswerStudent();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
}
