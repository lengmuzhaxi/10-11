package com.mingrisoft.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(Connection conn, java.sql.Statement stmt, java.sql.ResultSet rs) {
        if (rs != null) { try { rs.close(); } catch (SQLException e) { } }
        if (stmt != null) { try { stmt.close(); } catch (SQLException e) { } }
        if (conn != null) { try { conn.close(); } catch (SQLException e) { } }
    }
}
