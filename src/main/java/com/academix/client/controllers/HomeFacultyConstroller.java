package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class HomeFacultyConstroller {
    @FXML
    public Hyperlink myFacultyHyperlink;
    @FXML
    public Hyperlink catalogHyperlink;
    @FXML
    public Hyperlink accountSettingsHyperlink;
    @FXML
    public Hyperlink signOutHyperlink;
    @FXML
    public Hyperlink helpHyperlink;
    @FXML
    public Button addFacultyInformationButton;
    private MainApplication mainApplication;
    public void setMainApp(MainApplication mainApplication){
        this.mainApplication = mainApplication;
    }

    public void goToCatalog(ActionEvent actionEvent) {
        try {
            mainApplication.loadCatalogFaculty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void goToAccountSettings(ActionEvent actionEvent) {
        try {
            mainApplication.loadAccountSettingsFacultyPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToHelp(ActionEvent actionEvent) {
    }

    public void signOut(ActionEvent actionEvent) {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToMyFaculty(ActionEvent actionEvent) {
        try {
            mainApplication.loadMyFaculty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
