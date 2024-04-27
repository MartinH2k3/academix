package server.database.user;

import server.database.DatabaseConnector;
import server.logging.Logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserGetter {
    public static List<String> getAllUsers() {
        String query = "SELECT username FROM users";
        try (Connection conn = DatabaseConnector.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            List<String> usernames = new ArrayList<>();
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
            return usernames;
        } catch (SQLException e) {
            Logging.getInstance().logException(e, "Error with SQL query.");
            return null;
        }
    }
}
