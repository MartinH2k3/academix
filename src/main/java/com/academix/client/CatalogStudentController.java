package com.academix.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CatalogStudentController {
    private MainApplication mainApplication;
    @FXML
    private Hyperlink pastResultHyperlink;

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

    @FXML
    private ScrollBar scrollBar;

    @FXML
    private Label label1;

    @FXML
    private Text takeALookText;

    @FXML
    private Button SelectButton1;

    @FXML
    private TextField usernameTextfield;

    @FXML
    private ImageView imageView;

    @FXML
    private Label label2;

    @FXML
    private Text takeALookText1;

    @FXML
    private Button SelectButton2;
    public void goToPastResults(ActionEvent actionEvent) {
    }

    public void goToQuiz(ActionEvent actionEvent) {
        try {
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToCatalog(ActionEvent actionEvent) {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToAccountSettings(ActionEvent actionEvent) {
        try {
            mainApplication.loadAccountSettingsStudentPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToHelp(ActionEvent actionEvent) {
    }

    public void signOut(ActionEvent actionEvent) {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
