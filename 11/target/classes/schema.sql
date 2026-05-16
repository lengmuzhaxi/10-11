-- 创建商品表 (用于传统 DAO 测试)
CREATE TABLE IF NOT EXISTS tb_goods (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    type VARCHAR(50) NOT NULL
);

-- 创建用户表 (用于 MyBatis 映射测试)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(50) NOT NULL,
    userPwd VARCHAR(50) NOT NULL,
    userName VARCHAR(50) NOT NULL,
    userAge INT,
    userSex VARCHAR(10),
    userTel VARCHAR(50),
    createTime TIMESTAMP
);

-- 预置教材基础初始数据
INSERT INTO tb_goods (name, price, type) VALUES ('方便面', 1.5, '食品');
INSERT INTO tb_goods (name, price, type) VALUES ('面包', 2.5, '食品');
INSERT INTO users (userId, userPwd, userName, userAge, userSex, userTel, createTime) 
VALUES ('mrkj', '123', '明日科技', 20, '男', '13812345678', CURRENT_TIMESTAMP);