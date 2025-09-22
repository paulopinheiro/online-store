package br.com.paulopinheiro.onlinestore.persistence.utils;

import br.com.paulopinheiro.onlinestore.persistence.utils.connectionpools.DbcpDemo;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private DBUtils() {}
	
    public static Connection getConnection() {
        try {
            return DbcpDemo.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
