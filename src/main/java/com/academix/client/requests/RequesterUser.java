package com.academix.client.requests;

import com.academix.client.FormatCheck;
import com.academix.client.requests.RequestSender;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.dto.*;

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
     * @return user type "student", "admin", "faculty_representative" or an error message in case of invalid log in
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
     * gets information about the user
     * @param username to know whose information to get
     * @return AccountInfoDTO object with the information
     */
    public AccountInfoDTO getAccountInfo(String username) {
        String json = requestSender.sendRequest("/account_info?username=" + username, "GET");
        Gson gson = new Gson();
        return gson.fromJson(json, AccountInfoDTO.class);
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

    public String deleteAccount(String username) {
        return requestSender.sendRequest("/account/delete?username=" + username, "POST");
    }

    /**
     * sends a question to the helpline, which will later be answered by the admin
     * @param question to be sent
     * @return response from the server, confirming whether the question was sent
     */
    public String sendQuestion(String username, String subject, String question) {
        QuestionDTO dto = new QuestionDTO();
        dto.username = username;
        dto.subject = subject;
        dto.question = question;
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        return requestSender.sendRequest("/submit_question", json, "POST");
    }

    /**
     * gets all answers for questions posed by the user
     */
    public List<QnADTO> getResponses(String username) {
        String json = requestSender.sendRequest("/question_responses?username=" + username, "GET");
        Gson gson = new Gson();
        Type qnaListType = new TypeToken<List<QnADTO>>(){}.getType();
        return gson.fromJson(json, qnaListType);
    }

    /**
     * gets all faculties from the server
     * @return list of faculties
     */
    public List<FacultyDTO> get_faculties() {
        String response = requestSender.sendRequest("/faculties", "GET");
        Gson gson = new Gson();
        Type facultyListType = new TypeToken<List<FacultyDTO>>(){}.getType();
        return gson.fromJson(response, facultyListType);
    }

    /**
     * gets faculties from the server, but with pagination
     * @param page
     * @param page_size
     * @return list of faculties
     */
    public List<FacultyDTO> get_faculties(int page, int page_size) {
        String response = requestSender.sendRequest("/faculties?page=" + page + "&page_size=" + page_size, "GET");
        Gson gson = new Gson();
        Type facultyListType = new TypeToken<List<FacultyDTO>>(){}.getType();
        return gson.fromJson(response, facultyListType);
    }
}
