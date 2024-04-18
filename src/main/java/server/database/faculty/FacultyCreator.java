package server.database.faculty;

import server.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyCreator {
    /**
     * Check if the user is a verified faculty representative
     * @param username Username of the user
     * @return True if the user is a verified faculty representative, false otherwise
     */
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

    /**
     * Add a faculty to the database
     * @param username Username of the faculty representative
     * @param university_name Name of the university. University will be added if it does not exist
     * @param faculty_name Name of the faculty
     * @param description Description of the faculty
     * @param field Field of the faculty
     * @param minimal_grade Minimal grade required to join the faculty
     * @param website_url URL of the faculty website
     * @param title_image_url URL of the title image of the faculty
     * @return Success or failure message
     */
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

    /**
     * Add a university to the database
     * @param name Name of the university
     * @return Success or failure message
     */
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


    /**
     * Check if a university exists in the database
     * @param name Name of the university
     * @return True if the university exists, false otherwise
     */
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











