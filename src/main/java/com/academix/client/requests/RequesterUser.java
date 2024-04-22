package com.academix.client.requests;

import com.academix.client.FormatCheck;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.Base64EncoderDecoder;
import common.dto.AccountInfoDTO;
import common.dto.ChangePasswordDTO;
import common.dto.FacultyDTO;
import common.dto.LoginCredentialsDTO;

import java.lang.reflect.Type;
import java.util.List;

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
     * @param username has to be unique
     * @param password has to be at least 6 characters long
     * @return response from the server, status of the registration
     */
    public String register(String username, String password, String type) {
        if (!FormatCheck.isValidUsername(username)) {
            return "Error: Invalid username.";
        }
        if (!FormatCheck.isValidPassword(password)) {
            return "Error: Password too short.";
        }
        LoginCredentialsDTO dto = new LoginCredentialsDTO();
        dto.username = username;
        dto.password = password;
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        return requestSender.sendRequest("/register?type=" + type, json, "POST");
    }

    /**
     * logs in the user
     * @param username
     * @param password
     * @return response from the server, status of the login
     */
    public String login(String username, String password) {
        LoginCredentialsDTO dto = new LoginCredentialsDTO();
        dto.username = username;
        dto.password = password;
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        return requestSender.sendRequest("/login", json, "GET");
    }

    /**
     * Changes information about the user. Add null for the fields that are not to be updated.
     * @param username to know whose information to update
     * @param email part of the body
     * @param firstName part of the body
     * @param lastName part of the body
     * @param phoneNumber part of the body
     * @return response from the server, confirming whether the account was updated
     */
    public String updateAccountInfo(String username, String email, String firstName, String lastName, String phoneNumber) {
        AccountInfoDTO dto = new AccountInfoDTO();
        if (email != null && !FormatCheck.isValidEmail(email)) {
            return "Error: Invalid email.";
        }
        if (firstName != null && !FormatCheck.isValidFirstName(firstName)) {
            return "Error: Invalid first name.";
        }
        if (lastName != null && !FormatCheck.isValidLastName(lastName)) {
            return "Error: Invalid last name.";
        }
        if (phoneNumber != null && !FormatCheck.isValidPhoneNumber(phoneNumber)) {
            return "Error: Invalid phone number.";
        }
        dto.email = email;
        dto.firstName = firstName;
        dto.lastName = lastName;
        dto.phoneNumber = phoneNumber;
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        return requestSender.sendRequest("/account/update?username=" + username, json, "POST");
    }

    /**
     * @param username to know whose password to reset
     * @param oldPassword
     * @param newPassword
     * @return response, confirming whether the password was reset
     */
    public String resetPassword(String username, String oldPassword, String newPassword) {
        ChangePasswordDTO dto = new ChangePasswordDTO();
        dto.username = username;
        dto.oldPassword = oldPassword;
        dto.newPassword = newPassword;
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        return requestSender.sendRequest("/account/reset_password", json,"POST");
    }

    /**
     * sends a question to the helpline, which will later be answered by the admin
     * @param question to be sent
     * @return response from the server, confirming whether the question was sent
     */
    public String sendQuestion(String username, String question) {
        return requestSender.sendRequest("/submit_question?username=" + username, question, "POST");
    }

    public List<FacultyDTO> get_faculties() {
        String response = requestSender.sendRequest("/faculties", "GET");
        Gson gson = new Gson();
        Type facultyListType = new TypeToken<List<FacultyDTO>>(){}.getType();
        return gson.fromJson(response, facultyListType);
    }
}
