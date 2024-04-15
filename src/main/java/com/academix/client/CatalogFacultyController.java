package com.academix.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
        // Implement action here
    }

    @FXML
    void goToAccountSettings() {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    void goToMyFaculty() {

    }
    public void setMainApplication(MainApplication mainApplication){
        this.mainApplication=mainApplication;
    }
}
