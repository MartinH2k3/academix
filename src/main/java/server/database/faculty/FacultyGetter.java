package server.database.faculty;

import common.dto.FacultyDTO;
import server.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class FacultyGetter {
    /**
     * Get all faculties
     * @return List of all faculties as FacultyDTO objects
     */
    public static List<FacultyDTO> getAllFaculties() {
        String query = "SELECT universities.name as university_name, faculties.name as faculty_name, description, field, minimal_grade, website_url, title_image_url FROM faculties JOIN universities ON faculties.parent_university_id = universities.university_id";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            List<FacultyDTO> faculties = new ArrayList<>();
            while (rs.next()) {
                FacultyDTO faculty = new FacultyDTO(rs.getString("university_name"),
                        rs.getString("faculty_name"), rs.getString("description"),
                        rs.getString("field"), rs.getString("minimal_grade"),
                        rs.getString("website_url"), rs.getString("title_image_url"));
                faculties.add(faculty);
            }
            return faculties;
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return null;
        }
    }

    public static FacultyDTO getFacultyAfterQuiz(String field, String grade) {
        String query = "SELECT universities.name as university_name, faculties.name as faculty_name, description, field, minimal_grade, website_url, title_image_url FROM faculties JOIN universities ON faculties.parent_university_id = universities.university_id WHERE LOWER(field) = LOWER(?) AND minimal_grade >= ? ORDER BY minimal_grade LIMIT 1;";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, field);
            pstmt.setBigDecimal(2, new java.math.BigDecimal(grade));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                FacultyDTO faculty = new FacultyDTO(rs.getString("university_name"),
                        rs.getString("faculty_name"), rs.getString("description"),
                        rs.getString("field"), rs.getString("minimal_grade"),
                        rs.getString("website_url"), rs.getString("title_image_url"));
                return faculty;
            }
            else {
                System.out.println("No fitting university found.");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return null;
        }
    }
}
