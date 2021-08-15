package com.nicatdursunlu.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class AbstractDao {

    public  Connection connection() throws Exception {

        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
