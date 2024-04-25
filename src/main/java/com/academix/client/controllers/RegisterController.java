package com.academix.client.controllers;

import com.academix.client.FormatCheck;
import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.logging.Logging;

import java.io.IOException;
import java.util.Formattable;

public class RegisterController {
    private MainApplication mainApplication;
    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private CheckBox schoolEmployeeCheckbox;

    @FXML
    private Button registerButton;

    @FXML
    private Hyperlink goToLoginHyperlink;


    @FXML
    private void initialize() {
        // You can add initialization logic here if needed
    }

    public void switchToLogin(ActionEvent actionEvent) {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void register(ActionEvent actionEvent) {
        String username = usernameTextfield.getText();
        if (!FormatCheck.isValidUsername(username)){
            return;
        }
        if(passwordPasswordField.getCharacters().isEmpty()){
            return;
        }

        if(!confirmPasswordField.getText().equals(passwordPasswordField.getText())){
            return;
        }
        if (schoolEmployeeCheckbox.isSelected()){
            try {
                mainApplication.loadHomeFaculty();
            } catch (Exception e) {
                Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
            }
        }else {
            try {
                mainApplication.loadHomeStudentPane();
            } catch (Exception e) {
                Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
            }
        }
    }
}