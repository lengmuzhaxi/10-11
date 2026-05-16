package com.example.demo.controller;

import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.Users;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class ManagementController {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UsersMapper usersMapper;

    // 整合展示的主页面
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("goodsList", goodsMapper.findAllGoods());
        model.addAttribute("usersList", usersMapper.findAllUsers());
        return "index";
    }

    // 处理商品添加
    @PostMapping("/addGoods")
    public String addGoods(GoodsInfo goods) {
        goodsMapper.addGoods(goods);
        return "redirect:/";
    }

    // 处理用户注册
    @PostMapping("/addUser")
    public String addUser(Users user) {
        user.setCreateTime(new Date());
        usersMapper.insertUser(user);
        return "redirect:/";
    }
}