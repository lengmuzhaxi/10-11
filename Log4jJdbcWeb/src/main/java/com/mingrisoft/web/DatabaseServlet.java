package com.mingrisoft.web;

import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"", "/"})
public class DatabaseServlet extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(DatabaseServlet.class);

    // ✅ 修复点：定义为 public static 内部类，并提供标准的 getter 方法
    public static class UserRecord {
        private int id; 
        private String name;
        public UserRecord(int id, String name) { this.id = id; this.name = name; }
        
        // EL 表达式访问 ${record.id} 时会调用 getId()
        public int getId() { return id; }
        public String getName() { return name; }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("--- 开始执行 JDBC 任务 ---");
        List<UserRecord> records = new ArrayList<>();
        Connection conn = null; Statement stmt = null; PreparedStatement pstmt = null; ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            stmt.executeUpdate("DROP TABLE IF EXISTS jdbc_test");
            stmt.executeUpdate("CREATE TABLE jdbc_test (id INT PRIMARY KEY, name VARCHAR(20))");
            
            String insertSql = "INSERT INTO jdbc_test (id, name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertSql);
            
            pstmt.setInt(1, 1); pstmt.setString(2, "tom"); pstmt.executeUpdate();
            pstmt.setInt(1, 2); pstmt.setString(2, "张三"); pstmt.executeUpdate();
            pstmt.setInt(1, 3); pstmt.setString(2, "999"); pstmt.executeUpdate();

            rs = stmt.executeQuery("SELECT id, name FROM jdbc_test");
            while (rs.next()) {
                records.add(new UserRecord(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            logger.error("数据库操作失败", e);
        } finally {
            DBUtil.close(conn, stmt, rs);
        }

        request.setAttribute("records", records);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}