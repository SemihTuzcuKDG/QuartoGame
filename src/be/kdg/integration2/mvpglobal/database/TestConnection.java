package be.kdg.integration2.mvpglobal.database;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Database connection successful!");
                conn.close();
            } else {
                System.out.println("Database connection failed.");
            }
        } catch (SQLException e) {
            System.err.println("Error testing database connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
