package data;


import data.interfaces.IDB;
import java.sql.*;

public class PostgresDB implements IDB {
    private static PostgresDB instance;
    private String host;
    private String username;
    private String password;
    private String dbName;
    private Connection connection;

    private PostgresDB(String host, String username, String password, String dbName) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }

    public static PostgresDB getInstance(String host, String username, String password, String dbName) {
        if (instance == null) {
            instance = new PostgresDB(host, username, password, dbName);
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }
        try {
            Class.forName("org.postgresql.Driver");
            String url = host + "/" + dbName;
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}