package br.com.paulopinheiro.onlinestore.persistence.utils.connectionpools;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class DbcpDemo {
    private static final String DB_DRIVER_NAME="org.apache.derby.client.ClientAutoloadedDriver";
    private static final BasicDataSource ds = new BasicDataSource();

    static {
        ds.setDriverClassName(DB_DRIVER_NAME);
        ds.setUrl("jdbc:derby://localhost:1527/learn_it_db");
        ds.setUsername("app");
        ds.setPassword("app");
        ds.setMinIdle(3);
        ds.setTimeBetweenEvictionRunsMillis(1000);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(200);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
