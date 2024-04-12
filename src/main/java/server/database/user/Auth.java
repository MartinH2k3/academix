package server.database.user;

import server.database.DatabaseConnector;
import server.database.security.Hasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public static String register(String username, String password, String type){
        String query = "INSERT INTO users (username, password_hash, type) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, Hasher.hashPassword(password));
            pstmt.setString(3, type);
            pstmt.executeUpdate();
            if (type.equals("student")) {
                addStudent(conn, username);
            } else if (type.equals("faculty_representative")) {
                addFacultyRepresentative(conn, username);
            }
            return "Registration successful";
        } catch (Exception e) {
            e.printStackTrace(); // Log required
        }
        return "Registration failed";
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
