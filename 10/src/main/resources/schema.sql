-- 创建商品表 (对应书本图11-18)
CREATE TABLE IF NOT EXISTS tb_goods (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE,
    type VARCHAR(50)
);

-- 创建用户表 (对应书本第240页)
CREATE TABLE IF NOT EXISTS users (
    userId VARCHAR(50) PRIMARY KEY,
    userPwd VARCHAR(50),
    userAge INT,
    userName VARCHAR(50),
    userTel VARCHAR(20),
    userSex VARCHAR(10),
    createTime TIMESTAMP
);

-- 预置一些初始数据
INSERT INTO tb_goods (name, price, type) VALUES ('玫瑰花', 15.0, '植物');
INSERT INTO tb_goods (name, price, type) VALUES ('发财树', 88.0, '植物');
INSERT INTO users (userId, userPwd, userAge, userName, userTel, userSex, createTime) 
VALUES ('mrkj', '123', 20, '明日科技', '13812345678', '男', CURRENT_TIMESTAMP);