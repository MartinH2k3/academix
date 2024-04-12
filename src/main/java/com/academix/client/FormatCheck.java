package com.academix.client;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FormatCheck {

    // Email patter from https://www.freeformatter.com/
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$");
    // Shortest phone number is 7 digits in Saint Helena and 15 digits is the limit according to the current standard
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+\\d{7,15}$");
    // We decided 20 characters is enough when planning the database
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    // 40 is the longest first name according to an unverified research, using \p{L} would be more accurate, but this is safer
    private static final Pattern FIRST_NAME_PATTERN = Pattern.compile("^\\p{L}{1,40}$");
    // 40 is the longest last name according to an unverified research
    private static final Pattern LAST_NAME_PATTERN = Pattern.compile("^\\p{L}{1,40}$");
    // 61 was the longest university name in slovakia, 100 is a safe limit
    private static final Pattern UNIVERSITY_NAME_PATTERN = Pattern.compile("^\\w{1,100}$");
    // For faculty, 100 is a safe limit as well
    private static final Pattern FACULTY_NAME_PATTERN = Pattern.compile("^\\w{1,100}$");


    /**
     * Validates an email address using Pattern and Matcher.
     * @param email the email address to validate
     * @return true if the email address is valid according to the EMAIL_PATTERN, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    /**
     * Validates a phone number using Pattern and Matcher.
     * @param phoneNumber the phone number to validate
     * @return true if the phone number is valid according to the PHONE_PATTERN, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * Validates a username using Pattern and Matcher.
     * @param username the username to validate
     * @return true if the username is valid according to the USERNAME_PATTERN, false otherwise
     */
    public static boolean isValidUsername(String username) {
        if (username == null) return false;
        Matcher matcher = USERNAME_PATTERN.matcher(username);
        return matcher.matches();
    }

    /**
     * Validates a first name using Pattern and Matcher.
     * @param firstName the first name to validate
     * @return true if the first name is valid according to the FIRST_NAME_PATTERN, false otherwise
     */
    public static boolean isValidFirstName(String firstName) {
        if (firstName == null) return false;
        Matcher matcher = FIRST_NAME_PATTERN.matcher(firstName);
        return matcher.matches();
    }

    /**
     * Validates a last name using Pattern and Matcher.
     * @param lastName the last name to validate
     * @return true if the last name is valid according to the LAST_NAME_PATTERN, false otherwise
     */
    public static boolean isValidLastName(String lastName) {
        if (lastName == null) return false;
        Matcher matcher = LAST_NAME_PATTERN.matcher(lastName);
        return matcher.matches();
    }

    /**
     * Validates a university name using Pattern and Matcher.
     * @param universityName the university name to validate
     * @return true if the university name is valid according to the UNIVERSITY_NAME_PATTERN, false otherwise
     */
    public static boolean isValidUniversityName(String universityName) {
        if (universityName == null) return false;
        Matcher matcher = UNIVERSITY_NAME_PATTERN.matcher(universityName);
        return matcher.matches();
    }

    /**
     * Validates a faculty name using Pattern and Matcher.
     * @param facultyName the faculty name to validate
     * @return true if the faculty name is valid according to the FACULTY_NAME_PATTERN, false otherwise
     */
    public static boolean isValidFacultyName(String facultyName) {
        if (facultyName == null) return false;
        Matcher matcher = FACULTY_NAME_PATTERN.matcher(facultyName);
        return matcher.matches();
    }

}