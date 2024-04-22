package com.academix.client.requests;

import com.google.gson.Gson;
import common.dto.FacultyDTO;

import java.util.Map;

public class RequesterStudent {
    private RequestSender requestSender;
    private static RequesterStudent requesterStudent = null;

    private RequesterStudent() {
        this.requestSender = RequestSender.getInstance();
    }

    public static RequesterStudent getInstance() {
        if (requesterStudent == null) {
            requesterStudent = new RequesterStudent();
        }
        return requesterStudent;
    }

    public Map<String, Map<String, String>> getQuiz() {
        String json = requestSender.sendRequest("/quiz", "GET");
        Gson gson = new Gson();
        return gson.fromJson(json, Map.class);
    }

    public FacultyDTO facultyBasedOnQuiz(String field, String grade) {
        String json = requestSender.sendRequest("/quiz_result?field=" + field + "&grade=" + grade, "GET");
        Gson gson = new Gson();
        return gson.fromJson(json, FacultyDTO.class);
    }
}
