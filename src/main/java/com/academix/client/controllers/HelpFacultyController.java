package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelpFacultyController {
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextField messageTextField;
    @FXML
    private Text sentSuccessfullyText;
    private MainApplication mainApplication;
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
    public void initialize(){
        sentSuccessfullyText.setVisible(false);
    }
    @FXML
    private void SendHelpMessage() {
        RequesterUser.getInstance().sendQuestion(mainApplication.loggedInUser, subjectTextField.getText(), messageTextField.getText());
        sentSuccessfullyText.setVisible(true);
    }
    @FXML
    private void goToMyFaculty() {
    }
    @FXML
    private void goToCatalog() {
    }
    @FXML

    private void goToAccountSettings() {
    }
    @FXML
    private void goToHelp() {
    }
    @FXML
    private void signOut( ) {
    }
}
