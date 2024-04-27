package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.Notification;
import com.academix.client.requests.RequesterUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import language.LocaleManager;
import server.logging.Logging;

import java.util.Locale;
import java.util.ResourceBundle;

public class AccountSettingsStudentController {
    private MainApplication mainApplication;

    private LocaleManager localeManager;
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
    private Text basicText;

    @FXML
    private Text passwordText;

    // Event handler for the "Past results" hyperlink

    @FXML
    private void initialize() {

        String email;
        String firstName;
        String lastName;
        String phoneNumber;



        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        pastResultHyperlink.setText(messages.getString("past_results"));
        takeQuizHyperlink.setText(messages.getString("take_quiz"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        basicText.setText(messages.getString("basic_text"));
        passwordText.setText(messages.getString("password"));

        firstNameTextfield.setPromptText(messages.getString("first_name"));
        lastNameTextfield.setPromptText(messages.getString("last_name"));
        phoneNumberTextfield.setPromptText(messages.getString("phone_number"));
        emailTextfield.setPromptText(messages.getString("email"));

        currentPasswordField.setPromptText(messages.getString("current_password"));
        newPasswordField.setPromptText(messages.getString("new_password"));
        repeatNewPasswordField.setPromptText(messages.getString("confirmpassword"));

        backButton.setText(messages.getString("back"));
        saveButton.setText(messages.getString("save"));
    }

    @FXML
    private void goToPastResults() {
        try {
            mainApplication.loadHomeStudentPane();
        } catch (Exception e) {
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
        try {
            mainApplication.loadAccountSettingsStudentPane(back);
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    // Event handler for the "Help" hyperlink
    @FXML
    private void goToHelp() {
        try {
            mainApplication.loadHelpStudent();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    // Event handler for the "Sign out" hyperlink
    @FXML
    private void signOut() {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    // Event handler for the "Save" button
    @FXML
    private void saveChanges() {
        // Implement the logic to save the changes made in the account settings
        String firstName = firstNameTextfield.getText();
        String lastName = lastNameTextfield.getText();
        String email = emailTextfield.getText();
        String phoneNumber = phoneNumberTextfield.getText();
        String username = mainApplication.getLoggedInUser();
        Notification notification = Notification.getInstance();

        String response = RequesterUser.getInstance().updateAccountInfo(username, email, firstName, lastName, phoneNumber);

        if(response != "Last name update failed"){
            if(localeManager.getLocale().equals(new Locale("SK"))){
                notification.showNotification("Priezvisko bolo aktualizované");
            }
            notification.showNotification("Last name was updated");
        }
        else if(response != "First name update failed") {
            if(localeManager.getLocale().equals(new Locale("SK"))){
                notification.showNotification("Meno bolo aktualizované");
            }
            notification.showNotification("Name was updated");
        }
        else if(response != "Email update failed"){
            if(localeManager.getLocale().equals(new Locale("SK"))){
                notification.showNotification("Email bol aktualizovaný");
            }
            notification.showNotification("Email was updated");
        }
        else if(response != "Phone number update failed"){
            if(localeManager.getLocale().equals(new Locale("SK"))){
                notification.showNotification("Telefónne číslo bolo aktualizované");
            }
            notification.showNotification("Phone number was updated");
        }
    }

    // Event handler for the "Back" button
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
