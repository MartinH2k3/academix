package server.database;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {
    public static final Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/academix";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }
}
