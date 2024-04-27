package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import language.LocaleManager;

import java.util.Locale;
import java.util.ResourceBundle;
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

    private LocaleManager localeManager;

    public void setMainApp(MainApplication mainApplication){
        this.mainApplication = mainApplication;
    }

    @FXML
    private void initialize() {
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        myFacultyHyperlink.setText(messages.getString("my_faculty"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        addFacultyInformationButton.setText(messages.getString("add_faculty"));
    }

    @FXML
    private void skLanguage(){
        localeManager.setLocale(new Locale("SK"));
        updateUI();
    }

    @FXML
    private void enLanguage(){
        localeManager.setLocale(new Locale("EN"));
        updateUI();
    }

    private void updateUI() {
        ResourceBundle messages = localeManager.getMessages();

        myFacultyHyperlink.setText(messages.getString("my_faculty"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        addFacultyInformationButton.setText(messages.getString("add_faculty"));
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
