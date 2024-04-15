package server.database.support;

import server.database.DatabaseConnector;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Helpline {
    public static String submitQuestion(String username, String question){
        String query = "INSERT INTO helpline_questions (asked_by, text, status) VALUES ((SELECT user_id FROM users WHERE username = ?), ?, 'pending')";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, question);
            pstmt.executeUpdate();
            return "Question submitted";
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return "Question submission failed";
        }
    }

    public static String answerQuestion(Long question_id, String response){
        String query = "INSERT INTO helpline_answers (question_id, response) VALUES (?, ?)";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setObject(1, question_id);
            pstmt.setString(2, response);
            pstmt.executeUpdate();
            String query2 = "UPDATE helpline_questions SET status = 'answered' WHERE question_id = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt2.setObject(1, question_id);
            pstmt2.executeUpdate();
            return "Answer submitted";
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return "Answer submission failed";
        }
    }
}
