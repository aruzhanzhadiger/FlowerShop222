package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FlowerShop",
                    "postgres",
                    "0000"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}