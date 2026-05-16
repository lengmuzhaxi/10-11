package com.example.demo.controller;

import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.Users;
import com.example.demo.dao.GoodsDao;
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
    private GoodsDao goodsDao; // 注入传统原生 JDBC DAO

    @Autowired
    private UsersMapper usersMapper; // 注入 MyBatis Mapper

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("goodsList", goodsDao.findAllGoods());
        model.addAttribute("usersList", usersMapper.findAllUsers());
        return "index";
    }

    @PostMapping("/addGoods")
    public String addGoods(GoodsInfo goods) {
        goodsDao.addGoods(goods);
        return "redirect:/";
    }

    @PostMapping("/addUser")
    public String addUser(Users user) {
        user.setCreateTime(new Date());
        usersMapper.insertUser(user);
        return "redirect:/";
    }
}