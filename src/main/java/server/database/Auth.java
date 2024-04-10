package server.database;

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

    private static void addStudent(){

    }

    private static void addFacultyRepresentative(){

    }

    public static String register(String username, String password, String type){
        String query = "INSERT INTO users (username, password_hash, type) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, Hasher.hashPassword(password));
            pstmt.setString(3, type);
            pstmt.executeUpdate();
            return "Registration successful";
        } catch (Exception e) {
            e.printStackTrace(); // Log required
        }
        return "Registration failed";
    }
}
