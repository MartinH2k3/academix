package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.Notification;
import com.google.gson.Gson;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import server.logging.Logging;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;


public class QuizController {

    //int[n] kde n je pocet otazok dal som zatial 20
    private int[] results = new int[20];
    private final Color WHITE = Color.WHITE;
    private final Color NEG4 = Color.web("#FF0000");
    private final Color NEG3 = Color.web("#ff8500");
    private final Color NEG2 = Color.web("#ffbe00");
    private final Color NEUT = Color.web("#ffff00");
    private final Color POS2 = Color.web("#d3ff00");
    private final Color POS3 = Color.web("#a7ff00");
    private final Color POS4 = Color.web("#00FF00");
    @FXML
    private VBox allAnswers;
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

    private MainApplication mainApplication;

    public void initialize() {
        Gson gson = new Gson();
        try (InputStream inputStream = getClass().getResourceAsStream("/com/academix/client/quiz.json");
             InputStreamReader reader = new InputStreamReader(inputStream)) {
            Map<String, Map<String,String>> questions = gson.fromJson(reader, Map.class);

            // Access the questions
            for (Map.Entry<String, Map<String,String>> entry : questions.entrySet()) {
                Map<String,String> question = entry.getValue();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/academix/client/component_quiz.fxml"));
                VBox vBox = loader.load();
                Label label = (Label) vBox.getChildren().get(0);
                label.setText(question.get("question"));
                allAnswers.getChildren().add(vBox);
                setCirclesOnClick((HBox) vBox.getChildren().get(2));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void setCirclesOnClick(HBox hBox){
        ObservableList<Node> list = hBox.getChildren();
        list.get(0).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(0),hBox.getChildren(), NEG4));
        list.get(1).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(1),hBox.getChildren(), NEG3));
        list.get(2).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(2),hBox.getChildren(), NEG2));
        list.get(3).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(3),hBox.getChildren(), NEUT));
        list.get(4).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(4),hBox.getChildren(), POS2));
        list.get(5).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(5),hBox.getChildren(), POS3));
        list.get(6).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(6),hBox.getChildren(), POS4));

    }

    private void toggleSelection(Circle circle, ObservableList<Node> list, Color col) {
        for (Node node: list) {
            Circle toWhite = (Circle) node;
            toWhite.setFill(WHITE);
        }
      circle.setFill(col);
    }
    public void goToPastResults(ActionEvent actionEvent) {
        try {
            mainApplication.loadHomeStudentPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void goToQuiz(ActionEvent actionEvent) {
        try {
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void goToCatalog(ActionEvent actionEvent) {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void goToAccountSettings(ActionEvent actionEvent) {
        try {
            mainApplication.loadAccountSettingsStudentPane("quiz");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void goToHelp(ActionEvent actionEvent) {
    }

    public void signOut(ActionEvent actionEvent) {
        try {
            mainApplication.loggedInUser = null;
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void ComputeResult() {
        var index = 0;
        for (Node node: allAnswers.getChildren()) {
            Node hBox = ((VBox) node).getChildren().get(2);
            var onlyOne = false;
            for (Node circle : ((HBox) hBox).getChildren()) {
                if (((Circle) circle).getFill() != WHITE) {
                    if (onlyOne) {
                        var notification = Notification.getInstance();
                        notification.showNotification("Please select one everywhere");
                        return;
                    }

                    results[index] = ((HBox) hBox).getChildren().indexOf(circle);
                    index++;
                    onlyOne = true;
                }
            }
        }
        //todo: spracovanie vysledkov
        for (int i=0;i<index;i++) {
            System.out.println(results[i]);
        }
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication=mainApplication;
    }


}
