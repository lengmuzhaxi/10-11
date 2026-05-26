package com.mingrisoft.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DaoTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();

            stmt.executeUpdate("DROP TABLE IF EXISTS jdbc_test");
            String createTableSql = "CREATE TABLE jdbc_test (" +
                                    "id INT PRIMARY KEY, " +
                                    "name VARCHAR(20))";
            stmt.executeUpdate(createTableSql);
            System.out.println("jdbc_test表创建完毕");

            String insertSql = "INSERT INTO jdbc_test (id, name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertSql);

            pstmt.setInt(1, 1);
            pstmt.setString(2, "tom");
            int row1 = pstmt.executeUpdate();
            System.out.println("第1次插入返回" + row1 + "条结果");

            pstmt.setInt(1, 2);
            pstmt.setString(2, "张三");
            int row2 = pstmt.executeUpdate();
            System.out.println("第2次插入返回" + row2 + "条结果");

            pstmt.setInt(1, 3);
            pstmt.setString(2, "999");
            int row3 = pstmt.executeUpdate();
            System.out.println("第3次插入返回" + row3 + "条结果");

            System.out.println("---数据库查询的结果----");
            System.out.println("id\tname");
            System.out.println("--------------------");

            String querySql = "SELECT id, name FROM jdbc_test";
            rs = stmt.executeQuery(querySql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + "\t" + name);
            }

        } catch (Exception e) {
            System.err.println("程序运行出错，请检查数据库服务是否开启或连接配置是否正确！");
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                DBUtil.close(null, pstmt, null);
            }
            DBUtil.close(conn, stmt, rs);
        }
    }
}
