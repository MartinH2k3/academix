package com.academix.client;

import com.academix.client.LoginController;
import com.academix.client.RegisterController;
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
        Parent registerPane = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this); // Pass reference to MainApplication to RegisterController
        root.setCenter(registerPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
