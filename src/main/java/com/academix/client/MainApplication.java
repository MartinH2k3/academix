package com.academix.client;

import com.academix.client.controllers.AccountSettingsFacultyController;
import com.academix.client.controllers.AccountSettingsStudentController;
import com.academix.client.controllers.CatalogFacultyController;
import com.academix.client.controllers.CatalogStudentController;
import com.academix.client.controllers.HelpStudentController;
import com.academix.client.controllers.HomeFacultyConstroller;
import com.academix.client.controllers.HomeStudentController;
import com.academix.client.controllers.LoginController;
import com.academix.client.controllers.MyFacultyController;
import com.academix.client.controllers.QuizController;
import com.academix.client.controllers.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private BorderPane root;

    private Stage primaryStage;
    public String loggedInUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Pane loginPane = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);

        root = new BorderPane();
        root.setCenter(loginPane);


        primaryStage.setTitle("Main Application");
        primaryStage.setScene(new Scene(root, loginPane.getPrefWidth(), loginPane.getPrefHeight()));
        primaryStage.show();
    }

    public void loadRegisterPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Pane registerPane = loader.load();
        RegisterController registerController = loader.getController();
        registerController.setMainApp(this);
        root.setCenter(registerPane);
    }

    public void loadLoginPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Pane loginPane = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        root.setCenter(loginPane);
    }

    public void loadCatalogStudentPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog_of_universities_student.fxml"));
        Pane catalog = loader.load();
        CatalogStudentController catalogStudentController = loader.getController();
        catalogStudentController.setMainApp(this);
        root.setCenter(catalog);
    }

    public void loadHomeStudentPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_student.fxml"));
        Pane homeStudent = loader.load();
        HomeStudentController homeStudentController = loader.getController();
        homeStudentController.setMainApp(this);
        root.setCenter(homeStudent);
    }

    public void loadAccountSettingsStudentPane(String back) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account_settings_student.fxml"));
        Pane accountset = loader.load();
        AccountSettingsStudentController accountSettingsStudentController = loader.getController();
        accountSettingsStudentController.setMainApp(this);
        accountSettingsStudentController.setBack(back);
        root.setCenter(accountset);
    }

    public void loadAccountSettingsFacultyPane(String back) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account_settings_faculty.fxml"));
        Pane accountset = loader.load();
        AccountSettingsFacultyController accountSettingsFacultyController = loader.getController();
        accountSettingsFacultyController.setMainApp(this);
        accountSettingsFacultyController.setBack(back);
        root.setCenter(accountset);
    }

    public void loadQuizPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz_student.fxml"));
        Pane quiz = loader.load();
        QuizController quizController = loader.getController();
        quizController.initialize();
        quizController.setMainApp(this);
        root.setCenter(quiz);
    }

    public void loadHomeFaculty() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_faculty.fxml"));
        Pane home = loader.load();
        HomeFacultyConstroller homeFacultyConstroller = loader.getController();
        homeFacultyConstroller.setMainApp(this);
        root.setCenter(home);
    }

    public void loadMyFaculty() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("my_faculty.fxml"));
        Pane MyFaculty = loader.load();
        MyFacultyController myFacultyController = loader.getController();
        myFacultyController.setMainApp(this);
        root.setCenter(MyFaculty);
    }

    public void loadCatalogFaculty() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog_of_universities_faculty.fxml"));
        Pane catalog = loader.load();
        CatalogFacultyController catalogFacultyController = loader.getController();
        catalogFacultyController.setMainApp(this);
        root.setCenter(catalog);
    }

    public void loadHelpStudent() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help_student.fxml"));
        Pane helpStudent = loader.load();
        HelpStudentController helpStudentController = loader.getController();
        helpStudentController.setMainApp(this);
        root.setCenter(helpStudent);
    }

    public void loadHelpFaculty() {
    }

    public static void main(String[] args) {
        launch(args);
    }


}
