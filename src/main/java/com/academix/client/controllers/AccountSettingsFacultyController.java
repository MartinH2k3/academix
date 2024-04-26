package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AccountSettingsFacultyController {
    public TextField firstNameTextfield;
    public TextField lastNameTextfield;
    public TextField phoneNumberTextfield;
    public TextField emailTextfield;
    public Text belongText1;
    public PasswordField currentPasswordField;
    public PasswordField newPasswordField;
    public PasswordField repeatNewPasswordField;
    public Button backButton;
    public Button saveButton;
    private MainApplication mainApplication;
    private String back;
    public void setMainApp(MainApplication mainApplication){
        this.mainApplication = mainApplication;
    }

    public void goBack(ActionEvent actionEvent) {
    }

    public void saveChanges(ActionEvent actionEvent) {
    }

    public void goToMyFaculty(ActionEvent actionEvent) {
    }

    public void goToCatalog(ActionEvent actionEvent) {
    }

    public void goToAccountSettings(ActionEvent actionEvent) {
    }

    public void goToHelp(ActionEvent actionEvent) {
    }

    public void signOut(ActionEvent actionEvent) {
    }
}
