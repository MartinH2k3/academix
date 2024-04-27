package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import server.logging.Logging;

public class HelpStudentController {
    private MainApplication mainApplication;
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
