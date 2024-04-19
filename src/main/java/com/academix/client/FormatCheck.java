package com.academix.client;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FormatCheck {

    // Email patter from https://www.freeformatter.com/
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[-a-z0-9~!$%^&*_=+}{'?]+(\\.[-a-z0-9~!$%^&*_=+}{'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$");
    // Shortest phone number is 7 digits in Saint Helena and 15 digits is the limit according to the current standard
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+\\d{7,15}$");
    // We decided 20 characters is enough when planning the database
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^\\w{3,20}$");
    // 40 is the longest first name according to an unverified research, using \p{L} would be more accurate, but this is safer
    private static final Pattern FIRST_NAME_PATTERN = Pattern.compile("^\\p{L}{1,40}$");
    // 40 is the longest last name according to an unverified research
    private static final Pattern LAST_NAME_PATTERN = Pattern.compile("^\\p{L}{1,40}$");
    // 61 was the longest university name in slovakia, 100 is a safe limit
    private static final Pattern UNIVERSITY_NAME_PATTERN = Pattern.compile("^\\w[\\w\s]{1,99}$");
    // For faculty, 100 is a safe limit as well
    private static final Pattern FACULTY_NAME_PATTERN = Pattern.compile("^\\w[\\w\s]{1,99}$");
    // Grade is a number between 1 and 5 and has two decimal places
    private static final Pattern GRADE_PATTERN = Pattern.compile("^([1-5](\\.\\d{1,2})?)$");
    // Regex for URL. http or https protocol, followed by a domain name, with path, with optional file at the end
    private static final Pattern URL_PATTERN = Pattern.compile("^(http|https)://[a-zA-Z0-9.-]+\\.[a-z]+(/[a-zA-Z0-9#]+/?)*(/\\w+\\.\\w+)?$");


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
     * Validates a password using Pattern and Matcher.
     * @param password the password to validate
     * @return true if the password is valid according to the PASSWORD_PATTERN, false otherwise
     */
    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return password.length() >= 8;
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

    /**
     * Checks if a string is a number using Integer.parseInt.
     * @param number the string to check
     * @return true if the string is a number, false otherwise
     */
    public static boolean isWholeNumber(String number) {
        try {
            Integer.parseInt(number); // not necessarily regex, but still belongs here, since it checks the format
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string is a number using Double.parseDouble.
     * @param number the string to check
     * @return true if the string is a number, false otherwise
     */
    public static boolean isNumber(String number){
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a grade is a number between 1 and 5 with 2 decimals using Pattern and Matcher.
     * @param grade the grade to validate
     * @return true if the grade is valid according to the GRADE_PATTERN, false otherwise
     */
    public static boolean isValidGrade(String grade) {
        if (grade == null) return false;
        Matcher matcher = GRADE_PATTERN.matcher(grade);
        return matcher.matches();
    }

    /**
     * Validates a URL using Pattern and Matcher.
     * @param url the URL to validate
     * @return true if the URL is valid according to the URL_PATTERN, false otherwise
     */
    public static boolean isValidUrl(String url) {
        if (url == null) return false;
        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }

    /**
     * Checks if a field is one of these: informatics, biology, mathematics, physics, medicine, veterinary medicine, law, business, marketing.
     * @param field the field to check
     * @return true if the field is a valid field of study, false otherwise
     */
    public static boolean isValidField(String field) {
        if (field == null) return false;
        String[] validFields = {"informatics", "biology", "mathematics", "physics", "medicine", "veterinary_medicine", "law", "business", "marketing"};
        for (String validField : validFields) {
            if (field.equalsIgnoreCase(validField)) {
                return true;
            }
        }
        return false;
    }
}