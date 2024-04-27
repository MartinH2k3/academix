package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import server.logging.Logging;

public class ExactAnswerStudentController {
    public Label answerLabel;
    private MainApplication mainApplication;

    public void goToAnswers(ActionEvent actionEvent) {
    }

    public void goToQuiz(ActionEvent actionEvent) {
    }

    public void goToCatalog(ActionEvent actionEvent) {
    }

    public void goToAccountSettings(ActionEvent actionEvent) {
    }

    public void goToHelp(ActionEvent actionEvent) {
    }

    public void signOut(ActionEvent actionEvent) {
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
