package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import server.logging.Logging;

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
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    

    public void goToAccountSettings(ActionEvent actionEvent) {
        try {
            mainApplication.loadAccountSettingsFacultyPane("home");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void goToHelp(ActionEvent actionEvent) {
    }

    public void signOut(ActionEvent actionEvent) {
        try {
            mainApplication.logged_in_user = null;
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void goToMyFaculty(ActionEvent actionEvent) {
        try {
            mainApplication.loadMyFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
}
