package com.academix.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private BorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginPane = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this); // Pass reference to MainApplication to LoginController

        root = new BorderPane();
        root.setCenter(loginPane);

        primaryStage.setTitle("Main Application");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void loadRegisterPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent registerPane = loader.load();
        RegisterController registerController = loader.getController();
        registerController.setMainApp(this); // Pass reference to MainApplication to RegisterController
        root.setCenter(registerPane);
    }
    public void loadLoginPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent loginPane = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this); // Pass reference to MainApplication to RegisterController
        root.setCenter(loginPane);
    }
    public void loadCatalogStudentPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catalog_of_universities_student.fxml"));
        Parent catalog = loader.load();
        CatalogStudentController catalogStudentController = loader.getController();
        catalogStudentController.setMainApp(this); // Pass reference to MainApplication to RegisterController
        root.setCenter(catalog);
    }
    public void loadHomeStudentPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_student.fxml"));
        Parent homeStudent = loader.load();
        HomeStudentController homeStudentController = loader.getController();
        homeStudentController.setMainApp(this); // Pass reference to MainApplication to RegisterController
        root.setCenter(homeStudent);
    }
    public void loadAccountSettingsStudentPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account_settings_student.fxml"));
        Parent accountset = loader.load();
        AccountSettingsStudentController accountSettingsStudentController = loader.getController();
        accountSettingsStudentController.setMainApp(this); // Pass reference to MainApplication to RegisterController
        root.setCenter(accountset);
    }
    public void loadAccountSettingsFacultyPane() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account_settings_faculty.fxml"));
        Parent accountset = loader.load();
        AccountSettingsFacultyController accountSettingsFacultyController = loader.getController();
        accountSettingsFacultyController.setMainApp(this); // Pass reference to MainApplication to RegisterController
        root.setCenter(accountset);
    }
    public void loadQuizPane() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz_student.fxml"));
        Parent quiz = loader.load();
        QuizController quizController = loader.getController();
        quizController.initialize();
        quizController.setMainApp(this); // Pass reference to MainApplication to RegisterController
        root.setCenter(quiz);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
