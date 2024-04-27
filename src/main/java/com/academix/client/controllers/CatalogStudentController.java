package com.academix.client.controllers;

import com.academix.client.MainApplication;
import com.academix.client.requests.RequesterUser;
import common.dto.FacultyDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import language.LocaleManager;
import server.logging.Logging;

import java.util.ResourceBundle;

import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.Desktop;

public class CatalogStudentController {
    private MainApplication mainApplication;

    private LocaleManager localeManager;

    @FXML
    private TextField searchTextField;
    @FXML
    private ImageView searchImage;
    @FXML
    private VBox allSchools;
    @FXML
    private TextField pageTextField;


    @FXML
    private Hyperlink takeQuizHyperlink;

    @FXML
    private Hyperlink catalogHyperlink;

    @FXML
    private Hyperlink accountSettingsHyperlink;

    @FXML
    private Hyperlink helpHyperlink;

    @FXML
    private Hyperlink signOutHyperlink;

    @FXML
    private TextField searchTextfield;

    @FXML
    private ScrollBar scrollBar;

    @FXML
    private Label label1;

    @FXML
    private Text takeALookText;

    @FXML
    private Button SelectButton1;

    @FXML
    private TextField usernameTextfield;

    @FXML
    private ImageView imageView;

    @FXML
    private Label label2;

    @FXML
    private Text takeALookText1;

    @FXML
    private BorderPane pane;
    public void initialize() {
        localeManager = LocaleManager.getInstance();

        ResourceBundle messages = localeManager.getMessages();

        takeQuizHyperlink.setText(messages.getString("take_quiz"));
        catalogHyperlink.setText(messages.getString("uni_catalog"));
        accountSettingsHyperlink.setText(messages.getString("account_settings"));
        helpHyperlink.setText(messages.getString("help"));
        signOutHyperlink.setText(messages.getString("sign_out"));

        searchTextfield.setPromptText(messages.getString("search"));
        RequesterUser user = RequesterUser.getInstance();
        int pageSize = 5;
        String val = pageTextField.getText();
        var faculties = user.get_faculties(Integer.parseInt(val), pageSize);
        if (faculties != null && !faculties.isEmpty()) {
            for (FacultyDTO facultyDTO : faculties) {
                try {
                    System.out.println(pageTextField.getText());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/academix/client/component_catalog.fxml"));
                    VBox vBox = loader.load();
                    HBox hBoxSchool = (HBox) vBox.getChildren().get(0);
                    Label label = (Label) hBoxSchool.getChildren().get(0);
                    label.setText(facultyDTO.university_name + ": " + facultyDTO.faculty_name);
                    HBox hBoxDescription = (HBox) vBox.getChildren().get(1);
                    Label labelDescription = (Label) hBoxDescription.getChildren().get(0);
                    labelDescription.setText(facultyDTO.description);
                    Button button = (Button) hBoxDescription.getChildren().get(1);
                    button.setOnAction(e -> {
                        try {
                            Desktop.getDesktop().browse(new URI(facultyDTO.website_url));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    });
                    allSchools.getChildren().add(vBox);
                } catch (Exception ex) {
                    Logging.getInstance().logException(ex, "nepodarilo sa načitat spravi");
                }
            }

        }
    }

    @FXML
    private void goToQuiz() {
        try {
            mainApplication.loadQuizPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToCatalog() {
        try {
            mainApplication.loadCatalogStudentPane();
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToAccountSettings(ActionEvent actionEvent) {
        try {
            mainApplication.loadAccountSettingsStudentPane("catalog");
        } catch (Exception e) {
            Logging.getInstance().logException(e, "Nepodarilo sa prejsť medzi scénami");
        }
    }
    @FXML
    private void goToHelp() {
        try {
            mainApplication.loadHelpStudent();
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

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
    @FXML
    private void goToPreviousPage(MouseEvent mouseEvent) {
        paging(-1);
    }
    private void paging(int x){
        try {
            String val = pageTextField.getText();
            int pageNumber = Integer.parseInt(val);
            pageNumber += x;
            if (pageNumber <= 0) pageNumber = 1;
            String nextPage = String.valueOf(pageNumber);
            pageTextField.setText(nextPage);
        } catch (NumberFormatException e) {
            Logging.getInstance().logException(e, "Doslo k chybe");
        }
    }
    @FXML
    private void goToNextPage(MouseEvent mouseEvent) {
        paging(1);
    }

    @FXML
    private void search() {

    }
}
