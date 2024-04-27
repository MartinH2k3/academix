package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import server.logging.Logging;

public class HomeFacultyConstroller {
    @FXML
    private Hyperlink myFacultyHyperlink;
    @FXML
    private Hyperlink catalogHyperlink;
    @FXML
    private Hyperlink accountSettingsHyperlink;
    @FXML
    private Hyperlink signOutHyperlink;
    @FXML
    private Hyperlink helpHyperlink;
    @FXML
    private Button addFacultyInformationButton;
    private MainApplication mainApplication;

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
    @FXML
    private void goToCatalog() {
        try {
            mainApplication.loadCatalogFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsFacultyPane("home");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToHelp() {
        try {
            mainApplication.loadHelpFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void signOut() {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToMyFaculty() {
        try {
            mainApplication.loadMyFaculty("myfaculty");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
}
