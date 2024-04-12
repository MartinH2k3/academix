package server.database;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection connection = null;

    public static final Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:postgresql://localhost:5432/academix?user=postgres";
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }
}
