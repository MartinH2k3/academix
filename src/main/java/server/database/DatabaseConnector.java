package server.database;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection connection = null;


    public static final Connection connect() throws SQLException {
        if (connection == null) {
            String url = "jdbc:postgresql://localhost:5432/academix";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            connection = DriverManager.getConnection(url, props);
        }
        return connection;
    }
}
