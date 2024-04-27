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
import javafx.stage.Stage;
import language.LocaleManager;
import server.logging.Logging;

import java.io.IOException;
import java.util.Formattable;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterController {
    private MainApplication mainApplication;
    private LocaleManager localeManager;
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
    private Button skToggleButton;

    @FXML
    private Button enToggleButton;


    @FXML
    private void initialize() {
        // You can add initialization logic here if needed
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        usernameTextfield.setPromptText(messages.getString("username"));
        passwordPasswordField.setPromptText(messages.getString("password"));
        confirmPasswordField.setPromptText(messages.getString("confirmpassword"));
        goToLoginHyperlink.setText(messages.getString("account"));
        registerButton.setText(messages.getString("register"));
        schoolEmployeeCheckbox.setText(messages.getString("employeetoggle"));
    }

    @FXML
    private void skLanguage() {
        localeManager.setLocale(new Locale("SK"));
        updateUI();
    }

    @FXML
    private void enLanguage() {
        localeManager.setLocale(new Locale("EN"));
        updateUI();
    }

    private void updateUI() {
        ResourceBundle messages = localeManager.getMessages();

        usernameTextfield.setPromptText(messages.getString("username"));
        passwordPasswordField.setPromptText(messages.getString("password"));
        confirmPasswordField.setPromptText(messages.getString("confirmpassword"));
        goToLoginHyperlink.setText(messages.getString("account"));
        registerButton.setText(messages.getString("register"));
        schoolEmployeeCheckbox.setText(messages.getString("employeetoggle"));
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
        mainApplication.setLoggedInUser(username);
    }

    public void hideUsernameBubble(MouseEvent mouseEvent) {
        invalidUsernameBubble.setVisible(false);
        invalidUsernameText.setVisible(false);

    }

    public void hidePasswordBubble(MouseEvent mouseEvent) {
        invalidPasswordBubble.setVisible(false);
        invalidPasswordText.setVisible(false);
    }
}