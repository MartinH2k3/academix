package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterUser;
import common.dto.QnADTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import language.LocaleManager;
import server.logging.Logging;

import java.util.ResourceBundle;

public class AnswerStudentController {
    @FXML
    private VBox allAnswers;
    private MainApplication mainApplication;

    private LocaleManager localeManager;

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
    private Button switcher;

    public void initialize(){

        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        takeQuizHyperlink.setText(messages.getString("take_quiz"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        switcher.setText(messages.getString("switcher"));

        var user = RequesterUser.getInstance();
        var list =  user.getResponses(mainApplication.getLoggedInUser());
        if (list != null && !list.isEmpty()) {
            for (QnADTO answer : list) {
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/academix/client/component_answer.fxml"));
                    HBox hBox = loader.load();
                    Label label = (Label) hBox.getChildren().get(0);
                    label.setText(answer.questionSubject);
                    Button button = (Button) hBox.getChildren().get(1);
                    button.setOnAction(e ->{
                        try{
                        mainApplication.loadExactAnswerStudent("Your question: \n"+ answer.question + "\nAdmin answer: \n" + answer.answer);
                        } catch (Exception ex){
                            Logging.getInstance().logException(ex, "Nepodarilo sa prejsť medzi scénami");
                        }
                    });
                    allAnswers.getChildren().add(hBox);
                } catch (Exception e){
                    Logging.getInstance().logException(e, "Doslo k chybe");
                }

            }
        }
    }

    @FXML
    private void goToQuiz( ) {
        try {
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToCatalog( ) {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToAccountSettings( ) {
        try {
            mainApplication.loadAccountSettingsStudentPane("help");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToHelp( ) {
        try {
            mainApplication.loadHelpStudent();
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

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    private void switchToSendHelp() {
        try {
            mainApplication.loadHelpStudent();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
}
