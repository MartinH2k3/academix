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
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
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
        String password = passwordPasswordField.getText();

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