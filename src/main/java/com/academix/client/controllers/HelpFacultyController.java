package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import server.logging.Logging;

public class HelpFacultyController {
    @FXML
    private Button sendMessageButton;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextField messageTextField;
    @FXML
    private Text sentSuccessfullyText;
    private MainApplication mainApplication;
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
    public void initialize(){
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
