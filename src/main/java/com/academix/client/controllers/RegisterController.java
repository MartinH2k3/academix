package com.academix.client.controllers;

import com.academix.client.FormatCheck;
import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import server.logging.Logging;

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

    @FXML
    public void register(ActionEvent actionEvent) {
        String username = usernameTextfield.getText();
        String password = passwordPasswordField.getText();


        if (!FormatCheck.isValidUsername(username)){
            return;
        }
        if(passwordPasswordField.getCharacters().isEmpty()){
            return;
        }

        if(!confirmPasswordField.getText().equals(passwordPasswordField.getText())){
            return;
        }
        String response;
        if (schoolEmployeeCheckbox.isSelected()){
            try {
                response = RequesterUser.getInstance().register(username,password,"faculty_representative");
                if (response.equals("Registration successful")){
                    mainApplication.loadHomeFaculty();
                }
            } catch (Exception e) {
                Logging.getInstance().logException(e, "Pri registrácii nastala chyba.");
            }
        }else {
            try {
                response = RequesterUser.getInstance().register(username,password,"student");
                if (response.equals("Registration successful")){
                    mainApplication.loadHomeStudentPane();
                }
            } catch (Exception e) {
                Logging.getInstance().logException(e, "Pri registrácii nastala chyba.");
            }
        }
    }


    // You can add more methods and fields as needed
}