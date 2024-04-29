package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
    private Hyperlink requestsHyperlink;
    @FXML
    private Hyperlink questionsHyperlink;
    @FXML
    private Hyperlink accountsHyperlink;
    @FXML
    private Hyperlink accountSettingsHyperlink1;
    @FXML
    private Hyperlink signOutHyperlink1;
    @FXML
    private VBox allRequests;
    @FXML
    private Label noRequestsLabel;
    @FXML
    public void initialize(){
        //todo treba otestovat ci to ide aj ako long alebo to iba mne robi (iba odstranit +"")
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        requestsHyperlink.setText(messages.getString("requests"));
        questionsHyperlink.setText(messages.getString("user_questions"));
        accountsHyperlink.setText(messages.getString("accounts"));
        accountSettingsHyperlink1.setText(messages.getString("account_settings"));
        signOutHyperlink1.setText(messages.getString("sign_out"));

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

    }
    @FXML

    private void goToRequests() {
        try {
            mainApplication.loadRequests();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToQuestionsFromUsers(ActionEvent actionEvent) {
        try {
            mainApplication.loadQuestionsFromUsers();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccounts(ActionEvent actionEvent) {
        try {
            mainApplication.loadAccounts();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccountSettings(ActionEvent actionEvent) {
        try {
            mainApplication.loadAccountSettingsAdmin("req");
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