package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import server.logging.Logging;

public class ExactAnswerFacultyController {
    @FXML
    private Label answerLabel;
    private MainApplication mainApplication;

    @FXML
    private void goToMyFaculty( ) {
        try {
            mainApplication.loadMyFaculty("help");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToCatalog( ) {
        try {
            mainApplication.loadCatalogFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccountSettings( ) {
        try {
            mainApplication.loadAccountSettingsFacultyPane("help");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToHelp( ) {
        try {
            mainApplication.loadHelpFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
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
    private void goToAnswers( ) {
        try {
            mainApplication.loadAnswerFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    public void setMessage(String answer) {
        answerLabel.setText(answer);
    }
    
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
