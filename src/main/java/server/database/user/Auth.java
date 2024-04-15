package server.database.user;

import server.database.DatabaseConnector;
import server.database.security.Hasher;
import server.database.support.Requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    public static String login(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
        try (Connection conn = DatabaseConnector.connect()){
            PreparedStatement pstmt = conn.prepareStatement(query);
            var passwordHash = Hasher.hashPassword(password);
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            if (pstmt.executeQuery().next()) {
                return "Login successful";
            }
            else {
                return "Incorrect username or password";
            }
        }
        catch (Exception e) {
            e.printStackTrace(); // Log required
            }
        return "Login failed";
    }

    private static void addStudent(Connection conn, String username){
        String query = "INSERT INTO students (user_id) VALUES ((SELECT user_id FROM users WHERE username = ?))";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
        }
    }

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
