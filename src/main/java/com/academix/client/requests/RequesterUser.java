package com.academix.client.requests;

import common.Base64EncoderDecoder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RequesterUser {
    private String username;
    private RequestSender requestSender;

    public RequesterUser(String username) {
        this.username = username;
        this.requestSender = RequestSender.getInstance();
    }

    public void sendQuestion(String question) {
        question = Base64EncoderDecoder.encode(question);
        requestSender.sendRequest("/submit_question?username=" + username + "&question=" + question, "POST");
    }
}
