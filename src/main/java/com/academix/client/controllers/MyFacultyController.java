package com.academix.client.controllers;
import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import server.logging.Logging;

public class MyFacultyController {
    @FXML
    private RadioButton vetMedRadioButton;
    @FXML
    private RadioButton bioRadioButton;
    @FXML
    private RadioButton physRadioButton;
    @FXML
    private RadioButton matRadioButton;
    @FXML
    private RadioButton medRadioButton;
    @FXML
    private RadioButton ecoRadioButton;
    @FXML
    private RadioButton psyRadioButton;
    @FXML
    private RadioButton socRadioButton;
    @FXML
    private RadioButton lawRadioButton;
    @FXML
    private RadioButton busRadioButton;
    @FXML
    private RadioButton marRadioButton;
    @FXML
    private RadioButton infRadioButton;
    private MainApplication mainApplication;
    private String back;
    @FXML
    private TextField facultyTextfield;

    @FXML
    private TextField universityTextfield;


    @FXML
    private Text text;

    @FXML
    private Line line2;

    @FXML
    private Text belongText1;


    @FXML
    private TextField shortDescriptionTextfield;

    @FXML
    private Line line4;

    @FXML
    private Text text2;

    @FXML
    private HBox hbox;

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
    private TextField averageTextfield;



    @FXML
    void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsFacultyPane("myfaculty");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToHelp() {
        try {
            mainApplication.loadHelpFaculty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToCatalog() {
        try {
            mainApplication.loadCatalogFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void signOut() {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    private void save(ActionEvent actionEvent) {
    }

    @FXML
    private void back() {
        switch (back) {
            case "help" -> {
                try {
                    mainApplication.loadHelpFaculty();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "catalog" -> {
                try {
                    mainApplication.loadCatalogFaculty();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            case "accset" -> {
                try {
                    mainApplication.loadAccountSettingsFacultyPane("myfaculty");
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
            default -> {
                try {
                    mainApplication.loadHomeFaculty();
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
                }
            }
        }
    }
    @FXML
    private void goToMyFaculty(ActionEvent actionEvent) {
    }

    public void setBack(String back) {
        this.back = back;
    }
}
