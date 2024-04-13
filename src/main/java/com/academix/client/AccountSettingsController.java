package com.academix.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class AccountSettingsController {
    private MainApplication mainApplication;


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
    private Button takeQuizButton;

    @FXML
    private Button takeQuizButton1;
    // Event handler for the "Past results" hyperlink
    @FXML
    private void goToPastResults() {
        // Implement the logic to navigate to the past results page
    }

    // Event handler for the "Take quiz" hyperlink
    @FXML
    private void goToQuiz() {
        try {
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Event handler for the "Catalog of universities" hyperlink
    @FXML
    private void goToCatalog() {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
        // Implement the logic to navigate back to the previous page
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
