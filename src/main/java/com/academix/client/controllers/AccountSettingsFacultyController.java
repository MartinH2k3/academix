package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import language.LocaleManager;
import server.logging.Logging;

import java.util.ResourceBundle;

public class AccountSettingsFacultyController {
    private MainApplication mainApplication;

    private LocaleManager localeManager;

    @FXML
    private Hyperlink myFacultyHyperLink;

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


    private void initialize() {
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        myFacultyHyperLink.setText(messages.getString("my_faculty"));
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
    public void setMainApp(MainApplication mainApplication){

        this.mainApplication = mainApplication;
    }

    @FXML
    private void signOut() {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void saveChanges() {
        // Implement the logic to save the changes made in the account settings
    }
}
