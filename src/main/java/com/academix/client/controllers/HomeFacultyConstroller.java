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
