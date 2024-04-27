package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import language.LocaleManager;
import server.logging.Logging;

import java.util.Locale;
import java.util.ResourceBundle;

public class HomeStudentController {
    private MainApplication mainApplication;
    private LocaleManager localeManager;

    @FXML
    private HBox navigationBar;

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
    private Pane facultyPane1;

    @FXML
    private Pane facultyPane2;

    @FXML
    private Pane facultyPane3;

    @FXML
    private Text belongText;

    @FXML
    private Text facultyText1;

    @FXML
    private Text uniText1;

    @FXML
    private Text facultyText2;

    @FXML
    private Text uniText2;

    @FXML
    private Text facultyText3;

    @FXML
    private Text uniText3;

    @FXML
    private Button takeQuizButton;

    @FXML
    private void initialize() {
        // initialize screen with default or changed language during signup/login
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        pastResultHyperlink.setText(messages.getString("past_results"));
        takeQuizHyperlink.setText(messages.getString("take_quiz"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        takeALookText.setText(messages.getString("take_look"));
        belongText.setText(messages.getString("belong_to"));

        takeQuizButton.setText(messages.getString("quiz_button"));
    }

    @FXML
    private void skLanguage() {
        localeManager.setLocale(new Locale("SK"));
        updateUI();
    }

    @FXML
    private void enLanguage() {
        localeManager.setLocale(new Locale("EN"));
        updateUI();
    }

    private void updateUI() {
        ResourceBundle messages = localeManager.getMessages();

        pastResultHyperlink.setText(messages.getString("past_results"));
        takeQuizHyperlink.setText(messages.getString("take_quiz"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        takeALookText.setText(messages.getString("take_look"));
        belongText.setText(messages.getString("belong_to"));

        takeQuizButton.setText(messages.getString("quiz_button"));
    }

    public void goToPastResults(ActionEvent actionEvent) {
    }

    @FXML
    private void goToPastResults() {
        try {
            mainApplication.loadHomeStudentPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToQuiz() {
        try {
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToCatalog() {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }

    }
    @FXML
    private void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsStudentPane("home");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToHelp() {
    }
    @FXML
    private void signOut() {
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
