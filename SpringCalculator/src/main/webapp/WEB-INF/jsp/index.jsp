<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>SpringMVC 计算平台</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="card">
        <img src="https://cdn-icons-png.flaticon.com/512/1077/1077114.png" alt="User Icon" class="avatar-icon">
        <h2>🛠️ SpringMVC 计算平台</h2>
        
        <form action="${pageContext.request.contextPath}/doCalculate" method="post">
            <div class="input-group">
                <label>第一个数：</label>
                <input type="number" step="any" name="num1" required placeholder="例：144"/>
            </div>
            
            <div class="input-group">
                <label>运算符号：</label>
                <select name="operator">
                    <option value="+">加法 (+)</option>
                    <option value="-">减法 (-)</option>
                    <option value="*">乘法 (*)</option>
                    <option value="/" selected>除法 (/)</option>
                </select>
            </div>
            
            <div class="input-group">
                <label>第二个数：</label>
                <input type="number" step="any" name="num2" required placeholder="例：12"/>
            </div>
            
            <button type="submit" class="btn-submit">🚀 模拟计算</button>
        </form>

        <div class="footer">
            <span class="badge">DEVELOPER</span> 
            👨‍🎓 姓名：<strong>党子立</strong> &nbsp;|&nbsp; 🆔 学号：<strong>220162402009</strong>
        </div>
    </div>
</body>
</html>
