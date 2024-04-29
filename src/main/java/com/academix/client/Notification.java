package com.academix.client;

import com.academix.client.controllers.NotificationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import language.LocaleManager;

import java.util.Locale;

public class Notification {
    private static Notification instance;

    private LocaleManager localeManager;

    private Notification() {}

    public static synchronized Notification getInstance() {
        if (instance == null) {
            instance = new Notification();
        }
        return instance;
    }

    public void showNotification(String message) {
        try {
            localeManager = LocaleManager.getInstance();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("notification.fxml"));
            Parent parent = loader.load();
            NotificationController notificationController = loader.getController();
            Stage stage = new Stage();

            // Assuming you have a method like initializeNotification in your controller
            notificationController.initialize(stage, message);

            stage.setScene(new Scene(parent, 450, 110));
            stage.setResizable(false);
            if(localeManager.getLocale().equals(new Locale("SK"))){stage.setTitle("Upozornenie");} else {
            stage.setTitle("Warning");}
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
