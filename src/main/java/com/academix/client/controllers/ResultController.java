package com.academix.client.controllers;

import com.academix.client.MainApplication;
import common.dto.FacultyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import language.LocaleManager;
import server.logging.Logging;

import java.awt.*;
import java.net.URI;
import java.util.ResourceBundle;

public class ResultController {

    private MainApplication mainApplication;

    @FXML
    private HBox resultsSchoolHBox;

    @FXML
    private Text takeALookText;

    @FXML
    private Label shortDescription;

    @FXML
    private Button selectButton;

    @FXML
    private Button schoolGalleryButton;

    @FXML
    private Hyperlink takeQuizHyperlink;

    @FXML
    private Hyperlink catalogHyperlink;

    @FXML
    private Hyperlink accountSettingsHyperlink;

    @FXML
    private Hyperlink helpHyperlink;

    @FXML
    private Hyperlink signOutHyperlink;

    private FacultyDTO facultyDTO;

    private LocaleManager localeManager;

    @FXML
    private void initialize() {
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        takeQuizHyperlink.setText(messages.getString("take_quiz"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        schoolGalleryButton.setText(messages.getString("gallery"));
        selectButton.setText(messages.getString("visit"));
    }

    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
        updateLabels();
    }

    private void updateLabels() {
        if (facultyDTO != null) {
            takeALookText.setText(facultyDTO.university_name);
            takeALookText.setText(facultyDTO.faculty_name);
            shortDescription.setText(facultyDTO.description);
        }
    }

    @FXML
    void goToAccountSettings(ActionEvent event) {
        try {
            mainApplication.loadAccountSettingsStudentPane("result");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void goToCatalog(ActionEvent event) {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    void goToHelp(ActionEvent event) {
        try {
            mainApplication.loadHelpStudent();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }       }

    @FXML
    void goToQuiz(ActionEvent event) {
        try {
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    void gallery(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI(facultyDTO.title_image_url));
        } catch (Exception ex) {
            Logging.getInstance().logException(ex, "Nepodarilo sa vyvolať prehliadačové vyhľadávanie");
        }
    }

    @FXML
    void schoolInfo(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI(facultyDTO.website_url));
        } catch (Exception ex) {
            Logging.getInstance().logException(ex, "Nepodarilo sa vyvolať prehliadačové vyhľadávanie");
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

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication=mainApplication;
    }
}
