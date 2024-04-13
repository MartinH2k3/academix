package com.academix.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class QuizController {
    private final Color WHITE = Color.WHITE;
    private final Color NEG4 = Color.web("#FF0000");
    private final Color NEG3 = Color.web("#ff8500");
    private final Color NEG2 = Color.web("#ffbe00");
    private final Color NEG1 = Color.web("#fff000");
    private final Color POS1 = Color.web("#f0ff00");
    private final Color POS2 = Color.web("#d3ff00");
    private final Color POS3 = Color.web("#a7ff00");
    private final Color POS4 = Color.web("#00FF00");
    @FXML
    private Hyperlink pastResultHyperlink;

    @FXML
    private Hyperlink takeQuizHyperlink;

    @FXML
    private Hyperlink catalogHyperlink;
    @FXML
    private Button resultsButton;

    @FXML
    private Hyperlink accountSettingsHyperlink;

    @FXML
    private Hyperlink helpHyperlink;

    @FXML
    private Hyperlink signOutHyperlink;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label label;

    @FXML
    private HBox hBox;

    @FXML
    private Circle negative4Circle;

    @FXML
    private Circle negative3Circle;

    @FXML
    private Circle negative2Circle;

    @FXML
    private Circle negative1Circle;

    @FXML
    private Circle positive1Circle;

    @FXML
    private Circle positive2Circle;

    @FXML
    private Circle positive3Circle;

    @FXML
    private Circle positive4Circle;
    private MainApplication mainApplication;

    public void initialize() {
        // Initialize your controller here
        negative4Circle.setOnMouseClicked(event -> toggleSelection(negative4Circle, NEG4));
        negative3Circle.setOnMouseClicked(event -> toggleSelection(negative3Circle, NEG3));
        negative2Circle.setOnMouseClicked(event -> toggleSelection(negative2Circle, NEG2));
        negative1Circle.setOnMouseClicked(event -> toggleSelection(negative1Circle, NEG1));
        positive1Circle.setOnMouseClicked(event -> toggleSelection(positive1Circle, POS1));
        positive2Circle.setOnMouseClicked(event -> toggleSelection(positive2Circle, POS2));
        positive3Circle.setOnMouseClicked(event -> toggleSelection(positive3Circle, POS3));
        positive4Circle.setOnMouseClicked(event -> toggleSelection(positive4Circle, POS4));
    }

    private void toggleSelection(Circle circle, Color col) {
      positive1Circle.setFill(WHITE);
      positive2Circle.setFill(WHITE);
      positive3Circle.setFill(WHITE);
      positive4Circle.setFill(WHITE);
      negative1Circle.setFill(WHITE);
      negative2Circle.setFill(WHITE);
      negative3Circle.setFill(WHITE);
      negative4Circle.setFill(WHITE);
      circle.setFill(col);
    }
    public void goToPastResults(ActionEvent actionEvent) {

    }

    public void goToQuiz(ActionEvent actionEvent) {

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
            mainApplication.loadAccountSettingsPane();
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
        this.mainApplication=mainApplication;
    }
}
