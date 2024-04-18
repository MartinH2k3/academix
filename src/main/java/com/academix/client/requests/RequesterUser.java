package com.academix.client.requests;

import common.Base64EncoderDecoder;

public class RequesterUser {
    private RequestSender requestSender;
    private static RequesterUser requesterUser = null;

    private RequesterUser() {
        this.requestSender = RequestSender.getInstance();
    }

    public static RequesterUser getInstance() {
        if (requesterUser == null) {
            requesterUser = new RequesterUser();
        }
        return requesterUser;
    }

    /**
     * registers the user
     * @param username
     * @param password
     * @return response from the server, status of the registration
     */
    public String register(String username, String password) {
        return requestSender.sendRequest("/register?username=" + username + "&password=" + password, "POST");
    }

    /**
     * logs in the user
     * @param username
     * @param password
     * @return response from the server, status of the login
     */
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

    /**
     * @param username to know whose password to reset
     * @param oldPassword
     * @param newPassword
     * @return response, confirming whether the password was reset
     */
    public String resetPassword(String username, String oldPassword, String newPassword) {
        return requestSender.sendRequest("/account/reset_password?username=" + username + "&old_password=" + oldPassword + "&new_password=" + newPassword, "POST");
    }

    /**
     * sends a question to the helpline, which will later be answered by the admin
     * @param question to be sent
     * @return response from the server, confirming whether the question was sent
     */
    public String sendQuestion(String username, String question) {
        question = Base64EncoderDecoder.encode(question);
        return requestSender.sendRequest("/submit_question?username=" + username + "&question=" + question, "POST");
    }
}
