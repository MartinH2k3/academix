package com.academix.client.controllers;

import com.academix.client.MainApplication;
import javafx.application.Preloader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class QuizController {
    //int[n] kde n je pocet otazok dal som zatial 20
    private int[] results = new int[20];
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
    private HBox hBox1;
    @FXML
    private Pane pane;

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
        for (Node node: pane.getChildren()) {
            if(node instanceof HBox){
                setCirclesOnClick((HBox) node);
            }
        }

    }
    private void setCirclesOnClick(HBox hBox){
        ObservableList<Node> list = hBox.getChildren();
        list.get(0).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(0),hBox.getChildren(), NEG4));
        list.get(1).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(1),hBox.getChildren(), NEG3));
        list.get(2).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(2),hBox.getChildren(), NEG2));
        list.get(3).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(3),hBox.getChildren(), NEG1));
        list.get(4).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(4),hBox.getChildren(), POS1));
        list.get(5).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(5),hBox.getChildren(), POS2));
        list.get(6).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(6),hBox.getChildren(), POS3));
        list.get(7).setOnMouseClicked(event -> toggleSelection((Circle)  list.get(7),hBox.getChildren(), POS4));

    }

    private void toggleSelection(Circle circle, ObservableList<Node> list, Color col) {
        for (Node node: list) {
            Circle toWhite = (Circle) node;
            toWhite.setFill(WHITE);
        }
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

    public void ComputeResult(MouseEvent mouseEvent) {
        var index = 0;
        for (Node node: pane.getChildren()) {
            if(node instanceof HBox){
                var onlyOne = false;
                for (Node circle: ((HBox) node).getChildren()) {
                    if (((Circle)circle).getFill()!=WHITE) {
                        if (onlyOne) {
                            //todo: nezaklikol nic v nejakej otazke treba upozornenie
                            System.out.println("chyba");
                            return;
                        }

                        results[index] = ((HBox) node).getChildren().indexOf(circle);
                        index++;
                        onlyOne = true;
                    }
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
