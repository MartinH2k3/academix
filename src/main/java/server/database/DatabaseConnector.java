package server.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection connection = null;

    /**
     * Connect to the database
     * @return Connection object
     * @throws SQLException
     */
    public static final Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties prop = new Properties();
            InputStream input = DatabaseConnector.class.getClassLoader().getResourceAsStream("db.properties");

            try {
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = prop.getProperty("db.url");
            //String url = "jdbc:postgresql://localhost:5432/academix?user=postgres";
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }
}
