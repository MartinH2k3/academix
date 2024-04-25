package server.database.support;

import common.dto.QnADTO;
import server.database.DatabaseConnector;
import server.logging.Logging;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            String message = "Question submission failed";
            Logging.getInstance().logException(e, "Pri vkladaní záznamu do databázy došlo k chybe");
            return message;
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
            String message = "Answer submission failed";
            Logging.getInstance().logException(e, "Pri SQL dopyte na databázu došlo k chybe");
            return message;
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

    /**
     * Get all answers for the questions by the user
     */
    public static List<QnADTO> getAnswers(String username){
        String query = "SELECT helpline_questions.text, helpline_answers.response FROM helpline_questions JOIN helpline_answers ON helpline_questions.question_id = helpline_answers.question_id WHERE helpline_questions.asked_by = (SELECT user_id FROM users WHERE username = ?)";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            List<QnADTO> answers = new ArrayList<>();
            while (rs.next()){
                QnADTO qna = new QnADTO(rs.getString("text"), rs.getString("response"));
                answers.add(qna);
            }
            return answers;
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
            return null;
        }
    }
}
