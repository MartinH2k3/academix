package com.academix.client.controllers;

import common.dto.FacultyDTO;

import java.awt.*;
import java.net.URI;

public class ResultController {
    private FacultyDTO facultyDTO;
    public void initialize(){

    }
    public void schoolInfo() {
        try {
            Desktop.getDesktop().browse(new URI(facultyDTO.website_url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void gallery() {

    }

    public void goToQuiz() {
    }

    public void goToCatalog() {
    }

    public void goToAccountSettings() {
    }

    public void goToHelp() {
    }

    public void signOut() {
    }
}
