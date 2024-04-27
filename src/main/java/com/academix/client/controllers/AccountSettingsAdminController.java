package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import language.LocaleManager;
import server.logging.Logging;

import java.util.ResourceBundle;

public class AccountSettingsAdminController {
    private MainApplication mainApplication;

    private LocaleManager localeManager;
    private String back;
    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField repeatNewPasswordField;
    @FXML
    private TextField emailTextfield;
    @FXML
    private TextField phoneNumberTextfield;
    @FXML
    private TextField lastNameTextfield;
    @FXML
    private TextField firstNameTextfield;

    @FXML
    private Hyperlink requestsHyperlink;

    @FXML
    private Hyperlink questionsHyperlink;

    @FXML
    private Hyperlink accountsHyperlink;

    @FXML
    private Hyperlink accountSettingsHyperlink;

    @FXML
    private Hyperlink signOutHyperlink;

    @FXML
    private Text belongText;

    @FXML
    private Text belongText1;

    @FXML
    private Button backButton;

    @FXML
    private Button saveButton;


    @FXML
    private void initialize() {
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        requestsHyperlink.setText(messages.getString("requests"));
        questionsHyperlink.setText(messages.getString("user_questions"));
        accountsHyperlink.setText(messages.getString("accounts"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        belongText.setText(messages.getString("basic"));
        belongText1.setText(messages.getString("password"));

        firstNameTextfield.setPromptText(messages.getString("first_name"));
        lastNameTextfield.setPromptText(messages.getString("last_name"));
        phoneNumberTextfield.setPromptText(messages.getString("phone_number"));
        emailTextfield.setPromptText(messages.getString("email"));

        currentPasswordField.setPromptText(messages.getString("current_password"));
        newPasswordField.setPromptText(messages.getString("new_password"));
        repeatNewPasswordField.setPromptText(messages.getString("confirmPassword"));

        backButton.setText(messages.getString("back"));
        saveButton.setText(messages.getString("save_button"));
    }
    @FXML
    private void goToRequests() {
    }

    public void setBack(String back){
        this.back = back;

    }
    @FXML
    private void goToQuestionsFromUsers() {
        try{
            mainApplication.loadQuestionsFromUsers();
        }catch (Exception e){
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToAccounts() {
    }

    @FXML
    private void goToAccountSettings() {
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
    private void goBack() {
        switch (back) {
            case "questions" -> {
                try {
                    mainApplication.loadQuestionsFromUsers();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "req" -> {
                try {
                    mainApplication.loadRequests();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "acc" -> {
                try {
                    mainApplication.loadAccounts();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            default -> {
                try {
                    mainApplication.loadHomeFaculty();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
        }
    }

    @FXML
    private void saveChanges(ActionEvent actionEvent) {
    }
    public void setMainApp(MainApplication mainApplication){
        this.mainApplication = mainApplication;
    }

}
