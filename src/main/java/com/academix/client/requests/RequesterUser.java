package com.academix.client.requests;

import common.Base64EncoderDecoder;

public class RequesterUser {
    private String username;
    private RequestSender requestSender;
    private static RequesterUser requesterUser = null;

    private RequesterUser(String username) {
        this.username = username;
        this.requestSender = RequestSender.getInstance();
    }

    public RequesterUser getInstance(String username) {
        if (requesterUser == null) {
            requesterUser = new RequesterUser(username);
        }
        this.username = username;
        return requesterUser;
    }

    public void sendQuestion(String question) {
        question = Base64EncoderDecoder.encode(question);
        requestSender.sendRequest("/submit_question?username=" + username + "&question=" + question, "POST");
    }
}
