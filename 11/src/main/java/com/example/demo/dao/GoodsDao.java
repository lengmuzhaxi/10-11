package com.example.demo.dao;
import com.example.demo.entity.GoodsInfo;
import java.util.List;

public interface GoodsDao {
    void addGoods(GoodsInfo goods);
    List<GoodsInfo> findAllGoods();
}