package com.academix.client.controllers;
import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class MyFacultyController {
    private MainApplication mainApplication;
    @FXML
    private TextField facultyTextfield;

    @FXML
    private TextField universityTextfield;

    @FXML
    private TextField phoneNumberTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private Line line1;

    @FXML
    private Text text;

    @FXML
    private Line line2;

    @FXML
    private Text belongText1;

    @FXML
    private Button takeQuizButton;

    @FXML
    private Button takeQuizButton1;

    @FXML
    private TextField nameTextfield;

    @FXML
    private Button addContactButton;

    @FXML
    private Line line3;

    @FXML
    private Text text1;

    @FXML
    private TextArea longDescriptionTextfield;

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
    private Text text3;

    @FXML
    private GridPane gridPane;

    @FXML
    private CheckBox checkBox1;

    @FXML
    private CheckBox checkBox2;

    @FXML
    private CheckBox checkBox3;

    @FXML
    private CheckBox checkBox4;

    @FXML
    private CheckBox checkBox5;

    @FXML
    private CheckBox checkBox6;

    @FXML
    private CheckBox checkBox7;

    @FXML
    private CheckBox checkBox8;

    @FXML
    private CheckBox checkBox9;

    @FXML
    private CheckBox checkBox10;

    @FXML
    private TextField averageTextfield;



    @FXML
    void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsFacultyPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToHelp() {
        // Implement action here
    }

    @FXML
    void goToCatalog() {
        try {
            mainApplication.loadCatalogFaculty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signOut() {
        try {
            mainApplication.logged_in_user = null;
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void save(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void addContact(ActionEvent actionEvent) {
    }

    public void goToMyFaculty(ActionEvent actionEvent) {
    }
}
