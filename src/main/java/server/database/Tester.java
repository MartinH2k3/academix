package server.database;

import java.sql.Connection;

public class Tester {
    public static String test() {
        try (Connection conn = DatabaseConnector.connect()){
            return "Connection successful";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Connection failed";
    }
}
