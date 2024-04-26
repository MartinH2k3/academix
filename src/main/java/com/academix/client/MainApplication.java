package com.academix.client;

import com.academix.client.controllers.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class MainApplication extends Application {

    private BorderPane root;
    private Stage primaryStage;
    public String logged_in_user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Pane loginPane = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);

        root = new BorderPane();
        root.setCenter(loginPane);


        primaryStage.setTitle("Main Application");
        primaryStage.setScene(new Scene(root, loginPane.getPrefWidth(), loginPane.getPrefHeight()));
        primaryStage.show();
        this.primaryStage = primaryStage;
        // Create a label for the notification message
        Label notificationLabel = new Label("Wrong password!");

        // Create a button to dismiss the notification
        Button okButton = new Button("OK");


        // Create a layout to hold the notification label and button
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(notificationLabel, okButton);

        // Create a scene and set it on the stage
        Scene scene = new Scene(vbox, 250, 150);

        // Create a new stage for the notification
        Stage notificationStage = new Stage();
        okButton.setOnAction(e -> notificationStage.close());
        notificationStage.setScene(scene);
        notificationStage.setTitle("Wrong Password Notification");

        // Set the stage style to undecorated (no window decorations)
        notificationStage.initStyle(StageStyle.UNDECORATED);

        // Show the stage
        notificationStage.show();
    }

    public void loadRegisterPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Pane registerPane = loader.load();
        RegisterController registerController = loader.getController();
        registerController.setMainApp(this);
        primaryStage.setWidth(registerPane.getPrefWidth());
        primaryStage.setHeight(registerPane.getPrefHeight());
        root.setCenter(registerPane);
    }
    public void loadLoginPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Pane loginPane = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        primaryStage.setWidth(loginPane.getPrefWidth());
        primaryStage.setHeight(loginPane.getPrefHeight());
        root.setCenter(loginPane);
    }
    public void loadCatalogStudentPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog_of_universities_student.fxml"));
        Pane catalog = loader.load();
        CatalogStudentController catalogStudentController = loader.getController();
        catalogStudentController.setMainApp(this);
        primaryStage.setWidth(catalog.getPrefWidth());
        primaryStage.setHeight(catalog.getPrefHeight());
        root.setCenter(catalog);
    }
    public void loadHomeStudentPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_student.fxml"));
        Pane homeStudent = loader.load();
        HomeStudentController homeStudentController = loader.getController();
        homeStudentController.setMainApp(this);
        primaryStage.setWidth(homeStudent.getPrefWidth());
        primaryStage.setHeight(homeStudent.getPrefHeight());
        root.setCenter(homeStudent);
    }
    public void loadAccountSettingsStudentPane(String back) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account_settings_student.fxml"));
        Pane accountset = loader.load();
        AccountSettingsStudentController accountSettingsStudentController = loader.getController();
        accountSettingsStudentController.setMainApp(this);
        accountSettingsStudentController.setBack(back);
        primaryStage.setWidth(accountset.getPrefWidth());
        primaryStage.setHeight(accountset.getPrefHeight());
        root.setCenter(accountset);
    }
    public void loadAccountSettingsFacultyPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account_settings_faculty.fxml"));
        Pane accountset = loader.load();
        AccountSettingsFacultyController accountSettingsFacultyController = loader.getController();
        accountSettingsFacultyController.setMainApp(this);
        primaryStage.setWidth(accountset.getPrefWidth());
        primaryStage.setHeight(accountset.getPrefHeight());
        root.setCenter(accountset);
    }
    public void loadQuizPane() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz_student.fxml"));
        Pane quiz = loader.load();
        QuizController quizController = loader.getController();
        quizController.initialize();
        quizController.setMainApp(this);
        primaryStage.setWidth(quiz.getPrefWidth());
        primaryStage.setHeight(quiz.getPrefHeight());
        root.setCenter(quiz);
    }
    public void loadHomeFaculty() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_faculty.fxml"));
        Pane home = loader.load();
        HomeFacultyConstroller homeFacultyConstroller = loader.getController();
        homeFacultyConstroller.setMainApp(this);
        primaryStage.setWidth(home.getPrefWidth());
        primaryStage.setHeight(home.getPrefHeight());
        root.setCenter(home);
    }
    public void loadMyFaculty() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("my_faculty.fxml"));
        Pane MyFaculty = loader.load();
        MyFacultyController myFacultyController = loader.getController();
        myFacultyController.setMainApp(this);
        primaryStage.setWidth(MyFaculty.getPrefWidth());
        primaryStage.setHeight(MyFaculty.getPrefHeight());
        root.setCenter(MyFaculty);
    }
    public void loadCatalogFaculty() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog_of_universities_faculty.fxml"));
        Pane catalog = loader.load();
        CatalogFacultyController catalogFacultyController = loader.getController();
        catalogFacultyController.setMainApp(this);
        primaryStage.setWidth(catalog.getPrefWidth());
        primaryStage.setHeight(catalog.getPrefHeight());
        root.setCenter(catalog);
    }
    public void loadHelpStudent() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help_student.fxml"));
        Pane helpStudent = loader.load();
        HelpStudentController helpStudentController = loader.getController();
        helpStudentController.setMainApp(this);
        primaryStage.setWidth(helpStudent.getPrefWidth());
        primaryStage.setHeight(helpStudent.getPrefHeight());
        root.setCenter(helpStudent);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
