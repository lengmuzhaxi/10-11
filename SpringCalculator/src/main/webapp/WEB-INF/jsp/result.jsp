<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>计算结果</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="card">
        <img src="https://cdn-icons-png.flaticon.com/512/190/190411.png" alt="Success Icon" class="avatar-icon">
        <h2>✅ 计算成功</h2>
        
        <div style="color: #34495e; font-size: 16px; margin: 15px 0;">
            运算符号：<strong style="color:#3498db; font-size:20px;">${operator}</strong>
        </div>
        
        <div class="result-box">
            计算结果：<br> 
            <span style="font-size: 40px; display: block; margin-top: 10px;">${result}</span>
        </div>
        
        <button onclick="history.back()" class="btn-submit" style="background: linear-gradient(to right, #a1c4fd 0%, #c2e9fb 100%); color: #2c3e50;">⬅️ 返回重新计算</button>

        <div class="footer">
            <span class="badge" style="background: #3498db;">DEVELOPER</span> 
            👨‍🎓 姓名：<strong>党子立</strong> &nbsp;|&nbsp; 🆔 学号：<strong>220162402009</strong>
        </div>
    </div>
</body>
</html>
