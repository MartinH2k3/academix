package server.database.user;

import server.database.DatabaseConnector;
import server.database.security.Hasher;
import server.database.support.Requests;
import server.logging.Logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    /**
     * Login the user with the given username and password
     * @param username username of the user
     * @param password password of the user
     * @return String status of the login
     */
    public static String login(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
        try (Connection conn = DatabaseConnector.connect()){
            PreparedStatement pstmt = conn.prepareStatement(query);
            var passwordHash = Hasher.hashPassword(password);
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            if (pstmt.executeQuery().next()) {
                Logging.getInstance().logLogin(username);
                return "Login successful";
            }
            else {
                String message = "Incorrect username or password";
                Logging.getInstance().logServerWarning(message);
                return message;
            }
        }
        catch (Exception e) {
            Logging.getInstance().logException(e,"Pri prihlasovaní používateľa" + username + " došlo k chybe");
            }
        return "Login failed";
    }

    /**
     * Add a user to the students table, with reference to the user table
     * @param conn Connection object to the database
     * @param username Username of the student
     */
    private static void addStudent(Connection conn, String username){
        String query = "INSERT INTO students (user_id) VALUES ((SELECT user_id FROM users WHERE username = ?))";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logging.getInstance().logException(e, "Pri vkladaní záznamu do databázy došlo k chybe");
        }
    }

    /**
     * Add a user to the faculty_representatives table, with reference to the user table
     * @param conn Connection object to the database
     * @param username Username of the faculty representative
     */
    private static void addFacultyRepresentative(Connection conn, String username){
        String query = "INSERT INTO faculty_representatives (user_id) VALUES ((SELECT user_id FROM users WHERE username = ?))";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
        }
    }

    /**
     * Register a user with the given username, password and type
     * @param username username of the user
     * @param password password of the user
     * @param type type of the user
     * @return String status of the registration
     */
    public static String register(String username, String password, String type) {
        // Check if username already exists
        String checkUserQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement checkStmt = conn.prepareStatement(checkUserQuery)) {

            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return "User already exists";
            }

            // If user does not exist, proceed with registration
            String query = "INSERT INTO users (username, password_hash, type) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                pstmt.setString(2, Hasher.hashPassword(password));  // Custom class for SHA-256 hashing
                pstmt.setString(3, type);
                pstmt.executeUpdate();

                // Adding user to specific type tables
                if (type.equals("student")) {
                    addStudent(conn, username);
                } else if (type.equals("faculty_representative")) {
                    addFacultyRepresentative(conn, username);
                    Requests.submitFacultyRepRequest(username); // Creates a request that admin can approve
                }
                return "Registration successful";
            } catch (SQLException e) {
                e.printStackTrace();  // Better to use logging in real applications
                return "Error during registration";
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Better to use logging in real applications
            return "Database connection error";
        }
    }


    /**
     * Reset the password of the user with the given username
     * @param username username of the user
     * @param oldPassword old password of the user
     * @param newPassword new password of the user
     * @return String status of the password reset
     */
    public static String resetPassword(String username, String oldPassword, String newPassword){
        String query = "UPDATE users SET password_hash = ? WHERE username = ? AND password_hash = ?";
        try (Connection conn = DatabaseConnector.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Hasher.hashPassword(newPassword));
            pstmt.setString(2, username);
            pstmt.setString(3, Hasher.hashPassword(oldPassword));
            if (pstmt.executeUpdate() > 0){
                return "Password reset successful";

            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
        }
        return "Password reset failed";
    }
}
