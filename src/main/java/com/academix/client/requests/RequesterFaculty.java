package com.academix.client.requests;

import com.google.gson.Gson;
import common.Base64EncoderDecoder;
import common.dto.FacultyDTO;

public class RequesterFaculty {
    private static RequesterFaculty requesterFaculty = null;
    private RequestSender requestSender;

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
