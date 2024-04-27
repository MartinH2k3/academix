package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import language.LocaleManager;
import server.logging.Logging;

import java.util.Map;
import java.util.ResourceBundle;

public class RequestsController {
    private MainApplication mainApplication;

    private LocaleManager localeManager;
    @FXML
    private VBox allRequests;
    @FXML
    private Label noRequestsLabel;
    public void initialize(){
        //todo treba otestovat ci to ide aj ako long alebo to iba mne robi (iba odstranit +"")
        var admin = RequesterAdmin.getInstance();
        Map<Long, String> map = admin.getPendingRequests();
        noRequestsLabel.setVisible(false);
        if(!map.isEmpty()){
            for (Map.Entry<Long, String> req: map.entrySet()){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/academix/client/component_request.fxml"));
                    HBox hBox = loader.load();
                    Label label = (Label) hBox.getChildren().get(0);
                    label.setText(req.getValue());
                    Button accept = (Button) hBox.getChildren().get(1);
                    accept.setOnAction(e ->{
                        admin.acceptRequest(req.getKey()+"");
                        try {
                            mainApplication.loadRequests();
                        } catch (Exception ex) {
                            Logging.getInstance().logException(ex, "Nepodarilo sa prejsť medzi scénami");
                        }
                    });
                    Button reject = (Button) hBox.getChildren().get(2);
                    reject.setOnAction(e ->{
                        admin.rejectRequest(req.getKey()+"");
                        try {
                            mainApplication.loadRequests();
                        } catch (Exception ex) {
                            Logging.getInstance().logException(ex, "Nepodarilo sa prejsť medzi scénami");
                        }
                    });
                    allRequests.getChildren().add(hBox);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            noRequestsLabel.setVisible(true);
        }

        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

    }
    @FXML

    private void goToRequests(ActionEvent actionEvent) {
    }
    @FXML
    private void goToQuestionsFromUsers(ActionEvent actionEvent) {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccounts(ActionEvent actionEvent) {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccountSettings(ActionEvent actionEvent) {
        try {
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void signOut(ActionEvent actionEvent) {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    public void setMainApp(MainApplication mainApplication){
    this.mainApplication = mainApplication;
    }
}