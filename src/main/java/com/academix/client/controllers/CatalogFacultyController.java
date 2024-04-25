package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import server.logging.Logging;

public class CatalogFacultyController {
    private MainApplication mainApplication;

    @FXML
    private Hyperlink myFacultyHyperlink;

    @FXML
    private Hyperlink catalogHyperlink;

    @FXML
    private Hyperlink accountSettingsHyperlink;

    @FXML
    private Hyperlink helpHyperlink;

    @FXML
    private Hyperlink signOutHyperlink;


    @FXML
    private Text takeALookText;

    @FXML
    private Button SelectButton1;

    @FXML
    private TextField searchTextfield;

    @FXML
    private ImageView imageView;

    @FXML
    private Text takeALookText1;

    @FXML
    private Button SelectButton2;

    @FXML
    void goToCatalog() {

    }

    @FXML
    void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsFacultyPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void goToHelp() {
        // Implement action here
    }

    @FXML
    void signOut() {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void goToMyFaculty() {

    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void schoolInfo(ActionEvent actionEvent) {
    }
}