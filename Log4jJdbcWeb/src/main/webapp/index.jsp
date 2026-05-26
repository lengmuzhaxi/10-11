<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>JDBC & Log4j 综合实战控制台</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="dashboard-card">
        <img src="https://cdn-icons-png.flaticon.com/512/2885/2885412.png" alt="Database" class="avatar-icon">
        <h2>🚀 JDBC 数据中心与 Log4j 监控系统</h2>
        
        <div class="success-banner">
            ✅ <strong>执行状态：</strong> 后端已成功完成建表、插入、查询操作。<br>
            📝 <strong>日志状态：</strong> 所有运行细节和模拟抛出的异常，均已被 Log4j 成功捕获并写入到 <code>console.log</code> 配置文件中！
        </div>

        <table>
            <thead>
                <tr>
                    <th>ID (编号)</th>
                    <th>Name (姓名)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${records}">
                    <tr>
                        <td>${record.id}</td>
                        <td><strong>${record.name}</strong></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="footer">
            <span class="badge">SYSTEM ADMIN</span> 
            <span>👨‍🎓 姓名：<strong>党子立</strong></span> | 
            <span>🆔 学号：<strong>220162402009</strong></span>
        </div>
    </div>
</body>
</html>
