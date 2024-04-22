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
                FacultyDTO faculty = new FacultyDTO();
                faculty.university_name = rs.getString("university_name");
                faculty.faculty_name = rs.getString("faculty_name");
                faculty.description = rs.getString("description");
                faculty.field = rs.getString("field");
                faculty.minimal_grade = rs.getString("minimal_grade");
                faculty.website_url = rs.getString("website_url");
                faculty.title_image_url = rs.getString("title_image_url");
                faculties.add(faculty);
            }
            return faculties;
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return null;
        }
    }
}
