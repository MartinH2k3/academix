package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import server.logging.Logging;

public class AccountSettingsFacultyController {
    @FXML
    public TextField firstNameTextfield;
    @FXML
    public TextField lastNameTextfield;
    @FXML
    public TextField phoneNumberTextfield;
    @FXML
    public TextField emailTextfield;
    @FXML
    public Text belongText1;
    @FXML
    public PasswordField currentPasswordField;
    @FXML
    public PasswordField newPasswordField;
    @FXML
    public PasswordField repeatNewPasswordField;
    @FXML
    public Button backButton;
    @FXML
    public Button saveButton;

    private MainApplication mainApplication;
    private String back;
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
    public void saveChanges(ActionEvent actionEvent) {
    }
    @FXML
    public void goToMyFaculty(ActionEvent actionEvent) {
        try {
            mainApplication.loadMyFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    public void goToCatalog(ActionEvent actionEvent) {
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
            mainApplication.logged_in_user = null;
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void setBack(String back) {
        this.back = back;
    }
}
