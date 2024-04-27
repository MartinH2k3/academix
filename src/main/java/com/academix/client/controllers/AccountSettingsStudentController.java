package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterUser;
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
            mainApplication.loadAccountSettingsStudentPane(back);
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToHelp() {
        try {
            mainApplication.loadHelpStudent();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
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

    @FXML
    private void saveChanges() {
        if (isPersonalInfoFilled() && isPasswordFilled()) {
            RequesterUser.getInstance().updateAccountInfo(mainApplication.getLoggedInUser(), emailTextfield.getText(), firstNameTextfield.getText(), lastNameTextfield.getText(), phoneNumberTextfield.getText());
            RequesterUser.getInstance().resetPassword(mainApplication.getLoggedInUser(), currentPasswordField.getText(), newPasswordField.getText());
        } else if (isPasswordFilled()) {
            RequesterUser.getInstance().resetPassword(mainApplication.getLoggedInUser(), currentPasswordField.getText(), newPasswordField.getText());
        } else if (isPersonalInfoFilled()) {
            RequesterUser.getInstance().updateAccountInfo(mainApplication.getLoggedInUser(), emailTextfield.getText(), firstNameTextfield.getText(), lastNameTextfield.getText(), phoneNumberTextfield.getText());
        } else {}
    }

    private boolean isPersonalInfoFilled() {
        return !firstNameTextfield.getText().isEmpty()
                || !lastNameTextfield.getText().isEmpty()
                || !phoneNumberTextfield.getText().isEmpty()
                || !emailTextfield.getText().isEmpty();
    }

    private boolean isPasswordFilled() {
        return !currentPasswordField.getText().isEmpty()
                && !newPasswordField.getText().isEmpty()
                && !repeatNewPasswordField.getText().isEmpty();
    }

    @FXML
    private void goBack() {
        switch (back) {
            case "help" -> {
                try {
                    mainApplication.loadHelpStudent();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "catalog" -> {
                try {
                    mainApplication.loadCatalogStudentPane();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "quiz" -> {
                try {
                    mainApplication.loadQuizPane();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            default -> {
                try {
                    mainApplication.loadHomeStudentPane();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
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
