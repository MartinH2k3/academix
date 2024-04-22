package com.academix.client.requests;

import com.google.gson.Gson;

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
}
