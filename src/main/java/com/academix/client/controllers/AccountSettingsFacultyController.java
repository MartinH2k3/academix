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

    public void setMainApp(MainApplication mainApplication) {
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
                    mainApplication.loadMyFaculty();
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
            mainApplication.loadMyFaculty();
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
            mainApplication.loggedInUser = null;
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void setBack(String back) {
        this.back = back;
    }
}
