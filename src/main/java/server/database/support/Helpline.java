package server.database.support;

import server.database.DatabaseConnector;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Helpline {
    /**
     * Create an entry for the question asked by the user
     * @param username Username of the user asking the question
     * @param question Question asked by the user
     * @return Success or failure message
     */
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

    /**
     * Create an entry for the answer to the question with provided id
     * @param question_id ID of the question to answer
     * @param response Answer to the question
     * @return Success or failure message
     */
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

    /**
     * Get all questions that are pending (unanswered)
     * @return Map of question_id to question text
     */
    public static Map<Long, String> getQuestions(){
        String query = "SELECT question_id, text FROM helpline_questions WHERE status = 'pending'";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            Map<Long, String> questions = new HashMap<>();
            while (rs.next()){
                questions.put(rs.getLong("question_id"), rs.getString("text"));
            }
            return questions;
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return null;
        }
    }
}
