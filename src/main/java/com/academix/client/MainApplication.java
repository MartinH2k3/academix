package com.academix.client;

import com.academix.client.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.awt.Desktop;

import java.util.Objects;

public class MainApplication extends Application {

    private BorderPane root;

    private Stage primaryStage;
    private String loggedInUser;

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Pane loginPane = loader.load();
            LoginController loginController = loader.getController();
            loginController.setMainApp(this);

            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/academix/client/A_icon.jpg")));

            // Set the loaded icon image as the icon for the primary stage
            primaryStage.getIcons().add(icon);

            root = new BorderPane();
            root.setCenter(loginPane);


            primaryStage.setTitle("Academix");
            primaryStage.setScene(new Scene(root, loginPane.getPrefWidth(), loginPane.getPrefHeight()));
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadRegisterPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent registerPane = loader.load();
        RegisterController registerController = loader.getController();
        registerController.setMainApp(this);
        root.setCenter(registerPane);
    }

    public void loadLoginPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginPane = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        root.setCenter(loginPane);
    }

    public void loadCatalogStudentPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog_of_universities_student.fxml"));
        Parent catalog = loader.load();
        CatalogStudentController catalogStudentController = loader.getController();
        catalogStudentController.setMainApp(this);
        root.setCenter(catalog);
    }

    public void loadHomeStudentPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_student.fxml"));
        Parent homeStudent = loader.load();
        HomeStudentController homeStudentController = loader.getController();
        homeStudentController.setMainApp(this);
        root.setCenter(homeStudent);
    }

    public void loadAccountSettingsStudentPane(String back) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account_settings_student.fxml"));
        Parent accountset = loader.load();
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
        Parent quiz = loader.load();
        QuizController quizController = loader.getController();
        quizController.setMainApp(this);
        root.setCenter(quiz);
    }

    public void loadHomeFaculty() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_faculty.fxml"));
        Parent home = loader.load();
        HomeFacultyConstroller homeFacultyConstroller = loader.getController();
        homeFacultyConstroller.setMainApp(this);
        root.setCenter(home);
    }

    public void loadMyFaculty(String back) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("my_faculty.fxml"));
        Parent MyFaculty = loader.load();
        MyFacultyController myFacultyController = loader.getController();
        myFacultyController.setMainApp(this);
        myFacultyController.setBack(back);
        root.setCenter(MyFaculty);
    }

    public void loadCatalogFaculty() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog_of_universities_faculty.fxml"));
        Parent catalog = loader.load();
        CatalogFacultyController catalogFacultyController = loader.getController();
        catalogFacultyController.setMainApp(this);
        root.setCenter(catalog);
    }

    public void loadHelpStudent() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help_student.fxml"));
        Parent helpStudent = loader.load();
        HelpStudentController helpStudentController = loader.getController();
        helpStudentController.setMainApp(this);
        root.setCenter(helpStudent);
    }

    public void loadHelpFaculty() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help_faculty.fxml"));
        Parent helpFaculty = loader.load();
        HelpFacultyController helpFacultyController = loader.getController();
        helpFacultyController.setMainApp(this);
        root.setCenter(helpFaculty);
    }

    public void loadHomeAdmin() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_admin.fxml"));
        Parent home = loader.load();
        HomeAdminController homeAdminController = loader.getController();
        homeAdminController.setMainApp(this);
        root.setCenter(home);
    }
    public void loadQuestionsFromUsers() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("questions_from_users.fxml"));
        Parent questions = loader.load();
        QuestionsFromUsersController questionsFromUsersController = loader.getController();
        questionsFromUsersController.setMainApp(this);
        root.setCenter(questions);
    }
    public void loadAccountSettingsAdmin(String back) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account_settings_admin.fxml"));
        Parent accset = loader.load();
        AccountSettingsAdminController accountSettingsAdminController = loader.getController();
        accountSettingsAdminController.setMainApp(this);
        accountSettingsAdminController.setBack(back);
        root.setCenter(accset);
    }
    public void loadRequests() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("requests.fxml"));
        Parent req = loader.load();
        RequestsController requestsController = loader.getController();
        requestsController.setMainApp(this);
        root.setCenter(req);
    }
    public void loadAccounts() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("accounts.fxml"));
        Parent acc = loader.load();
        AccountsController accountsController = loader.getController();
        accountsController.setMainApp(this);
        root.setCenter(acc);
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    public String getLoggedInUser(){
        return this.loggedInUser;
    }

    public static void main(String[] args) {
        launch(args);
    }



}
