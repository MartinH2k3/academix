package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterAdmin;
import com.academix.client.requests.RequesterUser;
import common.dto.AccountInfoDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import language.LocaleManager;
import server.logging.Logging;

import java.util.ResourceBundle;

public class AccountsController {
    @FXML
    private VBox allUsers;
    private MainApplication mainApplication;
    private LocaleManager localeManager;

    @FXML
    private Hyperlink requestsHyperlink;
    @FXML
    private Hyperlink questionsHyperlink;
    @FXML
    private Hyperlink accountsHyperlink;
    @FXML
    private Hyperlink accountSettingsHyperlink;
    @FXML
    private Hyperlink signOutHyperlink;
    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
    @FXML
    public void initialize(){
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        requestsHyperlink.setText(messages.getString("requests"));
        questionsHyperlink.setText(messages.getString("user_questions"));
        accountsHyperlink.setText(messages.getString("accounts"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        var admin = RequesterAdmin.getInstance();
        var list = admin.showAllUsers();
        for (String username : list) {
                var user = RequesterUser.getInstance();
                AccountInfoDTO acc = user.getAccountInfo(username);
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/academix/client/component_accounts.fxml"));
                    VBox vBox = loader.load();
                    HBox hBox1 = (HBox) vBox.getChildren().get(0);
                    Label label1 =(Label) hBox1.getChildren().get(0);
                    label1.setText(username);
                    HBox hBox2 = (HBox) vBox.getChildren().get(1);
                    Label label2 =(Label) hBox2.getChildren().get(0);
                    label2.setText(acc.firstName);
                    Label label3 =(Label) hBox2.getChildren().get(1);
                    label3.setText(acc.lastName);
                    HBox hBox3 = (HBox) vBox.getChildren().get(2);
                    Label label4 =(Label) hBox3.getChildren().get(0);
                    label4.setText(acc.email);
                    Label label5 =(Label) hBox3.getChildren().get(1);
                    label5.setText(acc.phoneNumber);
                    HBox hBox4 = (HBox) vBox.getChildren().get(3);
                    Button button1 = (Button) hBox4.getChildren().get(0);
                    button1.setOnAction(e->{
                        user.deleteAccount(username);
                    });

                }catch (Exception e){
                    Logging.getInstance().logException(e, "nepodarilo sa načitat component");
                }
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
    private void goToQuestionsFromUsers() {
        try {
            mainApplication.loadQuestionsFromUsers();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccounts() {
        try {
            mainApplication.loadAccounts();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccountSettings() {
        try {
            mainApplication.loadAccountSettingsAdmin("acc");
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
}
