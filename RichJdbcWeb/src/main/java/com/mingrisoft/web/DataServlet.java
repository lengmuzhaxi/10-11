package com.mingrisoft.web;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 将接口路径映射为 /api/data
@WebServlet("/api/data")
public class DataServlet extends HttpServlet {

    class UserRecord {
        int id;
        String name;
        UserRecord(int id, String name) { this.id = id; this.name = name; }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 声明返回格式为标准的 JSON 数据流
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Map<String, Object> resultMap = new HashMap<>();
        List<UserRecord> records = new ArrayList<>();
        StringBuilder dbLog = new StringBuilder();

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            
            // 1. 建表
            stmt.executeUpdate("DROP TABLE IF EXISTS jdbc_test");
            stmt.executeUpdate("CREATE TABLE jdbc_test (id INT PRIMARY KEY, name VARCHAR(20))");
            dbLog.append("✅ jdbc_test表创建完毕<br>");
            
            // 2. 插入数据
            String insertSql = "INSERT INTO jdbc_test (id, name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertSql);
            
            pstmt.setInt(1, 1); pstmt.setString(2, "tom"); pstmt.executeUpdate();
            dbLog.append("✅ 第1次插入返回1条结果<br>");
            
            pstmt.setInt(1, 2); pstmt.setString(2, "张三"); pstmt.executeUpdate();
            dbLog.append("✅ 第2次插入返回1条结果<br>");
            
            pstmt.setInt(1, 3); pstmt.setString(2, "999"); pstmt.executeUpdate();
            dbLog.append("✅ 第3次插入返回1条结果<br>");

            // 3. 查询数据
            rs = stmt.executeQuery("SELECT id, name FROM jdbc_test");
            while (rs.next()) {
                records.add(new UserRecord(rs.getInt("id"), rs.getString("name")));
            }
            
            // 装填成功数据
            resultMap.put("success", true);
            resultMap.put("log", dbLog.toString());
            resultMap.put("data", records);

        } catch (Exception e) {
            resultMap.put("success", false);
            resultMap.put("log", "❌ 数据库操作失败：" + e.getMessage());
        } finally {
            if (pstmt != null) DBUtil.close(null, pstmt, null);
            DBUtil.close(conn, stmt, rs);
        }

        // 使用 Gson 一键将 Map 转化为 JSON 字符串输出给前端
        out.print(new Gson().toJson(resultMap));
        out.flush();
        out.close();
    }
}
