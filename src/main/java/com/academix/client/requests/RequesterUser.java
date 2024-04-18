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

    public String register(String username, String password) {
        return requestSender.sendRequest("/register?username=" + username + "&password=" + password, "POST");
    }

    public String login(String username, String password) {
        return requestSender.sendRequest("/login?username=" + username + "&password=" + password, "GET");
    }

    /**
     * @param username to know whose information to update
     * @param email
     * @param firstName
     * @param lastName
     * @return
     */
    public String updateAccount(String username, String email, String firstName, String lastName, String phoneNumber) {
        return requestSender.sendRequest("/account/update?username=" + username + "&email=" + email + "&first_name=" + firstName + "&last_name=" + lastName + "&phone_number=" + phoneNumber, "POST");
    }

    public String sendQuestion(String question) {
        question = Base64EncoderDecoder.encode(question);
        return requestSender.sendRequest("/submit_question?username=" + username + "&question=" + question, "POST");
    }
}
