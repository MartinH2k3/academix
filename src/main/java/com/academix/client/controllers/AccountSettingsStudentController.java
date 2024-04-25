package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import server.logging.Logging;

public class AccountSettingsStudentController {
    private MainApplication mainApplication;
    private String back;


    @FXML
    private TextField firstNameTextfield;

    @FXML
    private TextField lastNameTextfield;

    @FXML
    private TextField phoneNumberTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private TextField currentPasswordField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private TextField repeatNewPasswordField;

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
    private Button backButton;

    @FXML
    private Button saveButton;
    // Event handler for the "Past results" hyperlink
    @FXML
    private void goToPastResults() {
        try{
            mainApplication.loadHomeStudentPane();
        }catch(Exception e){
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    // Event handler for the "Take quiz" hyperlink
    @FXML
    private void goToQuiz() {
        try {
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    // Event handler for the "Catalog of universities" hyperlink
    @FXML
    private void goToCatalog() {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    // Event handler for the "Account settings" hyperlink
    @FXML
    private void goToAccountSettings() {
        // Implement the logic to navigate to the account settings page
    }

    // Event handler for the "Help" hyperlink
    @FXML
    private void goToHelp() {
        // Implement the logic to navigate to the help page
    }

    // Event handler for the "Sign out" hyperlink
    @FXML
    private void signOut() {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    // Event handler for the "Save" button
    @FXML
    private void saveChanges() {
        // Implement the logic to save the changes made in the account settings
    }

    // Event handler for the "Back" button
    @FXML
    private void goBack() {
        switch (back) {
            case "help" -> {
                try {
                    mainApplication.loadHelpStudent();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case "catalog" -> {
                try {
                    mainApplication.loadCatalogFaculty();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case "quiz" -> {
                try {
                    mainApplication.loadQuizPane();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            default -> {
                try {
                    mainApplication.loadHomeStudentPane();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void setBack(String back) {
        this.back = back;
    }
}