package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import server.logging.Logging;

public class AnswerStudentController {
    public VBox allAnswers;
    private MainApplication mainApplication;

    public void goToQuiz( ) {
    }

    public void goToCatalog( ) {
    }

    public void goToAccountSettings( ) {
    }

    public void goToHelp( ) {
    }

    public void signOut( ) {
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
}
