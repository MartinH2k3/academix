package com.academix.client.controllers;
import com.academix.client.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import language.LocaleManager;

import java.util.ResourceBundle;
import server.logging.Logging;

public class MyFacultyController {
    private MainApplication mainApplication;

    private LocaleManager localeManager;
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
    private TextArea longDescriptionTextfield;

    @FXML
    private TextField shortDescriptionTextfield;

    @FXML
    private Line line4;

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
    private Text basicText;

    @FXML
    private Text contactText;

    @FXML
    private Text descriptionText;

    @FXML
    private Text shortDescription;

    @FXML
    private Text catalogOption;

    @FXML
    private void initialize(){
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        myFacultyHyperlink.setText(messages.getString("my_faculty"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        basicText.setText(messages.getString("basic_text"));
        contactText.setText(messages.getString("contact"));
        descriptionText.setText(messages.getString("description"));
        shortDescription.setText(messages.getString("short_description"));
        catalogOption.setText(messages.getString("catalog_text"));

        facultyTextfield.setPromptText(messages.getString("faculty"));
        universityTextfield.setPromptText(messages.getString("university"));
        averageTextfield.setPromptText(messages.getString("average_grade"));
        nameTextfield.setPromptText(messages.getString("full_name"));
        phoneNumberTextfield.setPromptText(messages.getString("phone_number"));
        emailTextfield.setPromptText(messages.getString("email"));

        addContactButton.setText(messages.getString("add_contact"));
        longDescriptionTextfield.setPromptText(messages.getString("description"));
        shortDescriptionTextfield.setPromptText(messages.getString("short_description"));
    }


    @FXML
    void goToAccountSettings(ActionEvent actionEvent) {
        try {
            mainApplication.loadAccountSettingsFacultyPane("myfaculty");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToHelp() {
        // Implement action here
    }

    @FXML
    void goToCatalog(ActionEvent actionEvent) {
        try {
            mainApplication.loadCatalogFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void signOut() {
        try {
            mainApplication.loggedInUser = null;
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
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
