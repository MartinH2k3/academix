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

import java.util.ResourceBundle;

public class HelpFacultyController {
    @FXML
    private Button sendMessageButton;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextField messageTextField;
    @FXML
    private Text sentSuccessfullyText;
    @FXML
    private Hyperlink myFacultyHyperlink;
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
    private Button switcher;
    private MainApplication mainApplication;

    private LocaleManager localeManager;

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
    @FXML
    public void initialize(){
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        myFacultyHyperlink.setText(messages.getString("my_faculty"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        subjectTextField.setPromptText(messages.getString("help_subject"));
        messageTextField.setPromptText(messages.getString("help_message"));
        takeALookText.setText(messages.getString("help_contact_admin"));

        sendMessageButton.setText(messages.getString("send_button"));
        switcher.setText(messages.getString("switcher"));

        sentSuccessfullyText.setVisible(false);
    }
    @FXML
    private void SendHelpMessage() {
        RequesterUser.getInstance().sendQuestion(mainApplication.getLoggedInUser(), subjectTextField.getText(), messageTextField.getText());
        sentSuccessfullyText.setVisible(true);
    }
    @FXML
    private void goToMyFaculty() {
        try {
            mainApplication.loadMyFaculty("help");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToCatalog() {
        try {
            mainApplication.loadCatalogFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML

    private void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsFacultyPane("help");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToHelp() {
    }
    @FXML
    private void signOut( ) {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void switchToReceivedHelp( ) {
        try {
            mainApplication.loadAnswerFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
}
