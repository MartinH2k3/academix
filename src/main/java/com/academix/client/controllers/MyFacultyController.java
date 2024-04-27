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
import server.logging.Logging;
import java.util.HashMap;
import java.util.Map;

public class MyFacultyController {

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
    void goToAccountSettings() {
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
