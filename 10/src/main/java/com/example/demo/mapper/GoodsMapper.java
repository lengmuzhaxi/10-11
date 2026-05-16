package com.example.demo.mapper;
import com.example.demo.entity.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface GoodsMapper {
    int addGoods(GoodsInfo goods);
    List<GoodsInfo> findAllGoods();
}