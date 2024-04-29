package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterAdmin;
import com.academix.client.requests.RequesterUser;
import common.dto.QuestionDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import language.LocaleManager;
import server.logging.Logging;

import java.util.Map;
import java.util.ResourceBundle;

public class QuestionsFromUsersController {
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
    @FXML
    private Button markAllButton;
    @FXML
    private Button unmarkAllButton;
    @FXML
    private Button deleteMarkedButton;
    @FXML
    private VBox allQuestions;
    @FXML
    public void initialize(){
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        requestsHyperlink.setText(messages.getString("requests"));
        questionsHyperlink.setText(messages.getString("user_questions"));
        accountsHyperlink.setText(messages.getString("accounts"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        markAllButton.setText(messages.getString("mark"));
        unmarkAllButton.setText(messages.getString("unmark"));
        deleteMarkedButton.setText(messages.getString("delete"));

       var admin = RequesterAdmin.getInstance();
        var map = admin.getPendingQuestions();
        if (map != null && !map.isEmpty()) {
             for(Map.Entry<Long, QuestionDTO> question : map.entrySet()){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/academix/client/component_question.fxml"));
                    HBox hBox = loader.load();
                    Label label = (Label) hBox.getChildren().get(1);
                    label.setText(question.getValue().subject);
                    label.setId(question.getKey()+"");
                    Button button = (Button) hBox.getChildren().get(2);
                    button.setOnAction(e->{
                        try{
                            mainApplication.loadHelpAdmin(question.getValue().question);
                        }catch (Exception ex){
                            Logging.getInstance().logException(ex, "Nepodarilo sa prejsť medzi scénami");
                        }
                    });
                    allQuestions.getChildren().add(hBox);
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "nepodarilo sa načitat komponent");

                }
            }
        }
    }
    @FXML
    private void markAll() {
        ObservableList<Node> questions = allQuestions.getChildren();
        for (Node node: questions) {
           CheckBox checkBox = (CheckBox) ((HBox)node).getChildren().get(0);
           checkBox.setSelected(true);
        }
    }

    @FXML
    private void unmarkAll() {
        ObservableList<Node> questions = allQuestions.getChildren();
        for (Node node: questions) {
            CheckBox checkBox = (CheckBox) ((HBox)node).getChildren().get(0);
            checkBox.setSelected(false);
        }
    }

    @FXML
    private void delete() {
        //todo: mne nefunguje posielanie treba to otestovat
        ObservableList<Node> questions = allQuestions.getChildren();
        var admin = RequesterAdmin.getInstance();
        for (Node node: questions) {
            CheckBox checkBox = (CheckBox) ((HBox)node).getChildren().get(0);
            Label label = (Label) ((HBox)node).getChildren().get(1);
            if(checkBox.isSelected()){
                admin.answerQuestion(Long.getLong(label.getId()),"your message has been deleted.");
            }
        }
        try{
            mainApplication.loadQuestionsFromUsers();
        }catch (Exception e){
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
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
        try{
            mainApplication.loadAccountSettingsAdmin("questions");
        }catch (Exception e){
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    @FXML
    private void signOut() {
        try {
            mainApplication.setLoggedInUser(null);
            mainApplication.loadLoginPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
