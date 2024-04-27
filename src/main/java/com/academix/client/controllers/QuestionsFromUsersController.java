package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterAdmin;
import com.academix.client.requests.RequesterUser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.logging.Logging;

import java.util.Map;

public class QuestionsFromUsersController {
    private MainApplication mainApplication;
    @FXML
    private VBox allQuestions;
    public void initialize(){
       var admin = RequesterAdmin.getInstance();
        Map<Long,String> map = admin.getPendingQuestions();
        if (map != null && !map.isEmpty()) {
             for(Map.Entry<Long,String> question : map.entrySet()){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/academix/client/component_question.fxml"));
                    HBox hBox = loader.load();
                    Label label = (Label) hBox.getChildren().get(1);
                    label.setText(question.getValue());
                    label.setId(question.getKey()+"");
                    allQuestions.getChildren().add(hBox);
                } catch (Exception e) {
                    Logging.getInstance().logException(e, "nepodarilo sa načitat spravi");
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
    }

    @FXML
    private void goToQuestionsFromUsers() {
    }

    @FXML
    private void goToAccounts() {
    }

    @FXML
    private void goToAccountSettings() {
        try{
            mainApplication.loadAccountSettingsAdmin();
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
