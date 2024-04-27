package server.database.support;

import server.database.DatabaseConnector;
import server.logging.Logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
            String message = "Request submission failed";
            Logging.getInstance().logException(e, "Pri vkladaní záznamu do databázy došlo k chybe");
            return message;
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
            String message = "Request answer failed";
            Logging.getInstance().logException(e, "Pri vkladaní záznamu do databázy došlo k chybe");
            return message;
        }
    }

    public static Map<Long, String> getRequests(){
       String query = "SELECT request_id, username FROM requests JOIN users ON requests.user_id = users.user_id WHERE status = 'pending'";
    try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
          ResultSet rs = pstmt.executeQuery();
          Map<Long, String> requests = new HashMap<>();
          while (rs.next()) {
            requests.put(rs.getLong("request_id"), rs.getString("username"));
          }
          return requests;
    } catch (SQLException e) {
          Logging.getInstance().logException(e, "Pri SQL dopyte na databázu došlo k chybe");
          return null;
         }
    }
}
