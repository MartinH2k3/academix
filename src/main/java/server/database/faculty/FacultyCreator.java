package server.database.faculty;

import server.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyCreator {
    public static Boolean facultyMiddleware(String username) {
        String query = "SELECT verified FROM faculty_representatives WHERE user_id = (SELECT user_id FROM users WHERE username = ?)";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("verified");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
        }
        return false;
    }

    public static String addFaculty(String username, String university_name, String faculty_name, String description, String field, String minimal_grade, String website_url, String title_image_url) {
        if (!facultyMiddleware(username)) {
            return "User is not a verified faculty representative";
        }

        if (!universityExists(university_name)) {
            addUniversity(university_name);
        }

        String query = "INSERT INTO faculties (name, parent_university_id, description, field, minimal_grade, website_url, title_image_url) VALUES (?, (SELECT university_id FROM universities WHERE name = ?), ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, faculty_name);
            pstmt.setString(2, university_name);
            pstmt.setString(3, description);
            pstmt.setString(4, field);
            pstmt.setBigDecimal(5, new java.math.BigDecimal(minimal_grade));
            pstmt.setString(6, website_url);
            pstmt.setString(7, title_image_url);
            pstmt.executeUpdate();
            return "Faculty added";
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return "Faculty addition failed";
        }
    }

    private static String addUniversity(String name){
        String query = "INSERT INTO universities (name) VALUES (?)";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            return "University added";
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return "University addition failed";
        }
    }

    private static Boolean universityExists(String name){
        String query = "SELECT COUNT(*) FROM universities WHERE name = ?";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
        }
        return false;
    }
}











