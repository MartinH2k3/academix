package server.database.support;

import server.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Requests {
    /**
     * Create an entry for the request to become a faculty representative
     * @param username Username of the user requesting to become a faculty representative
     * @return Success or failure message
     */
    public static String submitFacultyRepRequest(String username){
        String query = "INSERT INTO requests (user_id, status) VALUES ((SELECT user_id FROM users WHERE username = ?), 'pending')";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            return "Request submitted";
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return "Request submission failed";
        }
    }

    /**
     * Approves the request to become a faculty representative
     * @param request_id
     * @param approve
     * @return
     */
    public static String answerRequest(Long request_id, Boolean approve){
        String query = "UPDATE requests SET status = ? WHERE request_id = ?";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, approve ? "approved" : "rejected");
            pstmt.setLong(2, request_id);
            pstmt.executeUpdate();
            String query2 = "UPDATE faculty_representatives SET verified = ? WHERE user_id = (SELECT user_id FROM requests WHERE request_id = ?)";
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt2.setBoolean(1, approve);
            pstmt2.setLong(2, request_id);
            pstmt2.executeUpdate();
            return "Request answered";
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return "Request answer failed";
        }
    }
}
