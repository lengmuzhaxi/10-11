package com.example.demo.dao.impl;

import com.example.demo.dao.GoodsDao;
import com.example.demo.entity.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GoodsDaoImpl implements GoodsDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void addGoods(GoodsInfo goods) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO tb_goods (name, price, type) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, goods.getName());
            stmt.setDouble(2, goods.getPrice());
            stmt.setString(3, goods.getType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException t) {}
            try { if (conn != null) conn.close(); } catch (SQLException t) {}
        }
    }

    @Override
    public List<GoodsInfo> findAllGoods() {
        List<GoodsInfo> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "SELECT * FROM tb_goods";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                GoodsInfo g = new GoodsInfo();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setPrice(rs.getDouble("price"));
                g.setType(rs.getString("type"));
                list.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException t) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException t) {}
            try { if (conn != null) conn.close(); } catch (SQLException t) {}
        }
        return list;
    }
}