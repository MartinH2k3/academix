package server.database.user;

import server.database.DatabaseConnector;
import server.logging.Logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * AccountInfoUpdate class is responsible for updating the account information of the user
 */
public class AccountInfoUpdate {
    /**
     * Update the email of the user
     * @param username username of the user, by which he is located in the database
     * @param email email of the user
     * @return String status of the update
     */
    public static String updateEmail(String username, String email) {
        String query = "UPDATE users SET email = ? WHERE username = ?";
        try (Connection conn = DatabaseConnector.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            return "Email updated";
        } catch (SQLException e) {
            Logging.getInstance().logException(e, "Pri aktualizácii emailu používateľa došlo k chybe");
        }
        return "Email update failed";
    }

    /**
     * Update the phone number of the user
     * @param username username of the user, by which he is located in the database
     * @param phoneNumber phone number of the user
     * @return String status of the update
     */
    public static String updatePhoneNumber(String username, String phoneNumber) {
        String query = "UPDATE users SET phone_number = ? WHERE user_id = (SELECT user_id FROM users WHERE username = ?)";
        try (Connection conn = DatabaseConnector.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            return "Phone number updated";
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
        }
        return "Phone number update failed";
    }

    /**
     * Update the first name of the user
     * @param username username of the user, by which he is located in the database
     * @param firstName first name of the user
     * @return String status of the update
     */
    public static String updateFirstName(String username, String firstName) {
        String query = "UPDATE users SET first_name = ? WHERE user_id = (SELECT user_id FROM users WHERE username = ?)";
        try (Connection conn = DatabaseConnector.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            return "First name updated";
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
        }
        return "First name update failed";
    }

    /**
     * Update the last name of the user
     * @param username username of the user, by which he is located in the database
     * @param lastName last name of the user
     * @return String status of the update
     */
    public static String updateLastName(String username, String lastName) {
        String query = "UPDATE users SET last_name = ? WHERE user_id = (SELECT user_id FROM users WHERE username = ?)";
        try (Connection conn = DatabaseConnector.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, lastName);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            return "Last name updated";
        } catch (SQLException e) {
            e.printStackTrace(); // Log required
        }
        return "Last name update failed";
    }
}
