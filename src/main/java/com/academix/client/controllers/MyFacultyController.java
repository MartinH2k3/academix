package com.academix.client.controllers;
import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterFaculty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import language.LocaleManager;

import java.util.ResourceBundle;
import server.logging.Logging;
import java.util.HashMap;
import java.util.Map;

public class MyFacultyController {
    private MainApplication mainApplication;

    private LocaleManager localeManager;

    @FXML
    public TextField galleryUrlTextfield;
    @FXML
    public TextField urlTextfield;
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
    private Line line2;

    @FXML
    private RadioButton busRadioButton;
    @FXML
    private RadioButton marRadioButton;
    @FXML
    private RadioButton infRadioButton;
    private String back;
    @FXML
    private TextField facultyTextfield;

    @FXML
    private TextField universityTextfield;

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

    private final Map<RadioButton, String> radioButtonMap = new HashMap<>();

    public void init(){
        radioButtonMap.put(vetMedRadioButton, "veterinary_medicine");
        radioButtonMap.put(bioRadioButton, "biology");
        radioButtonMap.put(physRadioButton, "physics");
        radioButtonMap.put(matRadioButton, "mathematics");
        radioButtonMap.put(medRadioButton, "medicine");
        radioButtonMap.put(ecoRadioButton, "economics");
        radioButtonMap.put(psyRadioButton, "psychology");
        radioButtonMap.put(socRadioButton, "sociology");
        radioButtonMap.put(lawRadioButton, "law");
        radioButtonMap.put(busRadioButton, "business");
        radioButtonMap.put(marRadioButton, "marketing");
        radioButtonMap.put(infRadioButton, "informatics");
    }

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
    private TextField nameTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private TextField phoneNumberTextfield;

    @FXML
    private Button addContactButton;

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
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void goToHelp() {
        try {
            mainApplication.loadHelpFaculty();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
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
        init();
        String facultyField = getSelectedFacultyType();
        RequesterFaculty.getInstance().createFaculty(mainApplication.getLoggedInUser(), universityTextfield.getText(), facultyTextfield.getText(), shortDescriptionTextfield.getText(), facultyField, averageTextfield.getText(),urlTextfield.getText() ,galleryUrlTextfield.getText());
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
    private void goToMyFaculty() {
    }

    private String getSelectedFacultyType() {
        for (Map.Entry<RadioButton, String> entry : radioButtonMap.entrySet()) {
            if (entry.getKey().isSelected()) {
                return entry.getValue();
            }
        }
        return "";
    }

    public void setBack(String back) {
        this.back = back;
    }

}
