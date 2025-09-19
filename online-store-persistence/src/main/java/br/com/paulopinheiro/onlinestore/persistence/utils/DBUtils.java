package br.com.paulopinheiro.onlinestore.persistence.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static final String JDBC_JAVADB_HOST = "jdbc:derby://localhost:1527/";
    private static final String DB_NAME = "learn_it_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DBUtils() {}
	
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_JAVADB_HOST + DB_NAME, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
