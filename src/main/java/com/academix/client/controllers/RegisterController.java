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

import java.io.IOException;
import java.util.Formattable;

public class RegisterController {
    private MainApplication mainApplication;
    @FXML
    private Polygon invalidPasswordBubble;
    @FXML
    private Polygon invalidConfirmPasswordBubble;
    @FXML
    private Polygon invalidUsernameBubble;
    @FXML
    private Text invalidPasswordText;
    @FXML
    private Text invalidConfirmPasswordText;
    @FXML
    private Text invalidUsernameText;

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
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void register(ActionEvent actionEvent) {
        hidePasswordBubble(null);
        hideUsernameBubble(null);
        hideConfirmPasswordBubble(null);
        String username = usernameTextfield.getText();
        if (!FormatCheck.isValidUsername(username)){
            invalidUsernameBubble.setVisible(true);
            invalidUsernameText.setVisible(true);
            return;
        }
        if(passwordPasswordField.getCharacters().isEmpty()){
            invalidPasswordBubble.setVisible(true);
            invalidPasswordText.setVisible(true);
            return;
        }

        if(!confirmPasswordField.getText().equals(passwordPasswordField.getText())){
            invalidConfirmPasswordBubble.setVisible(true);
            invalidConfirmPasswordText.setVisible(true);
            return;
        }
        if (schoolEmployeeCheckbox.isSelected()){
            try {
                mainApplication.loadHomeFaculty();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                mainApplication.loadHomeStudentPane();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void hideUsernameBubble(MouseEvent mouseEvent) {
        invalidUsernameBubble.setVisible(false);
        invalidUsernameText.setVisible(false);

    }

    public void hidePasswordBubble(MouseEvent mouseEvent) {
        invalidPasswordBubble.setVisible(false);
        invalidPasswordText.setVisible(false);
    }

    public void hideConfirmPasswordBubble(MouseEvent mouseEvent) {
        invalidConfirmPasswordBubble.setVisible(false);
        invalidConfirmPasswordText.setVisible(false);
    }

    // You can add more methods and fields as needed
}