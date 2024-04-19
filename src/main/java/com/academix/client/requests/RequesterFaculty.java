package com.academix.client.requests;

import com.academix.client.FormatCheck;
import com.google.gson.Gson;
import common.dto.FacultyDTO;


public class RequesterFaculty {
    private static RequesterFaculty requesterFaculty = null;
    private final RequestSender requestSender;

    private RequesterFaculty() {
        requestSender = RequestSender.getInstance();
    }

    public static RequesterFaculty getInstance() {
        if (requesterFaculty == null) {
            requesterFaculty = new RequesterFaculty();
        }
        return requesterFaculty;
    }

    public String createFaculty(String username, String university_name, String faculty_name, String description, String field, String minimal_grade, String website_url, String title_image_url) {
        if (!FormatCheck.isValidUniversityName(university_name)){
            return "Error: Invalid university name.";
        }
        if (!FormatCheck.isValidFacultyName(faculty_name)){
            return "Error: Invalid faculty name.";
        }
        if (!FormatCheck.isValidField(field)){
            return "Error: Invalid field.";
        }
        if (!FormatCheck.isValidGrade(minimal_grade)){
            return "Error: Invalid minimal grade.";
        }
        if (!FormatCheck.isValidUrl(website_url)){
            return "Error: Invalid website url.";
        }
        if (!FormatCheck.isValidUrl(title_image_url)){
            return "Error: Invalid title image url.";
        }
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.university_name = university_name;
        facultyDTO.faculty_name = faculty_name;
        facultyDTO.description = description;
        facultyDTO.field = field;
        facultyDTO.minimal_grade = minimal_grade;
        facultyDTO.website_url = website_url;
        facultyDTO.title_image_url = title_image_url;

        Gson gson = new Gson();
        String json = gson.toJson(facultyDTO);
        return requestSender.sendRequest("/create_faculty?username=" + username, json, "POST");
    }
}
