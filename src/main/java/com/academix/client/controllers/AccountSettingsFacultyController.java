package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import server.logging.Logging;
import javafx.scene.control.Hyperlink;
import language.LocaleManager;
import java.util.ResourceBundle;

public class AccountSettingsFacultyController {
    @FXML
    private TextField firstNameTextfield;
    @FXML
    private TextField lastNameTextfield;
    @FXML
    private TextField phoneNumberTextfield;
    @FXML
    private TextField emailTextfield;
    @FXML
    private Text belongText1;
    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField repeatNewPasswordField;
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private Hyperlink accountSettingsHyperlink;

    private MainApplication mainApplication;
    private String back;

    private LocaleManager localeManager;

    @FXML
    private Hyperlink myFacultyHyperlink;

    @FXML
    private Hyperlink catalogHyperlink;

    @FXML
    private Hyperlink helpHyperlink;

    @FXML
    private Hyperlink signOutHyperlink;

    @FXML
    private Text basicText;

    @FXML
    private Text passwordText;

    @FXML
    private void initialize() {
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        myFacultyHyperlink.setText(messages.getString("my_faculty"));
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
    private void goBack() {
        switch (back) {
            case "help" -> {
                try {
                    mainApplication.loadHelpFaculty();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "catalog" -> {
                try {
                    mainApplication.loadCatalogFaculty();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "myfaculty" -> {
                try {
                    mainApplication.loadMyFaculty("accset");
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

    @FXML
    private void goToMyFaculty(ActionEvent actionEvent) {
        try {
            mainApplication.loadMyFaculty("accset");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToCatalog() {
        try {
            mainApplication.loadCatalogFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    public void goToAccountSettings(ActionEvent actionEvent) {
    }

    @FXML
    public void goToHelp(ActionEvent actionEvent) {
        try {
            mainApplication.loadHelpFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    public void signOut(ActionEvent actionEvent) {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void setBack(String back) {
        this.back = back;
    }
}
