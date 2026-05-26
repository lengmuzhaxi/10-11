package com.mingrisoft.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

// 拦截根路径，这样你直接访问 localhost:8080 就能看到页面
@WebServlet(urlPatterns = {"", "/"})
public class CountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置响应格式，防止中文乱码
        response.setContentType("text/html;charset=UTF-8");

        // 2. 获取全局共享的 ServletContext 对象
        ServletContext context = getServletContext();
        int count = 0;

        // 3. 加锁保证高并发下的线程安全
        synchronized (context) {
            Object countObj = context.getAttribute("visitCount");
            if (countObj != null) {
                count = (Integer) countObj;
            }
            count++; // 计数器累加
            context.setAttribute("visitCount", count); // 存回上下文
        }

        // 获取当前访问时间
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // 4. 动态输出富文本 HTML 页面
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='zh-CN'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>网站访问统计大屏</title>");
        out.println("    <style>");
        // 丰富的页面样式：渐变背景、居中卡片、阴影效果
        out.println("        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; display: flex; justify-content: center; align-items: center; margin: 0; padding: 20px; box-sizing: border-box; }");
        out.println("        .card { background: rgba(255, 255, 255, 0.95); width: 100%; max-width: 500px; border-radius: 20px; box-shadow: 0 20px 40px rgba(0,0,0,0.2); overflow: hidden; text-align: center; position: relative; }");
        // 顶部横幅图片
        out.println("        .banner-img { width: 100%; height: 160px; object-fit: cover; }");
        out.println("        .content { padding: 40px 30px; }");
        out.println("        h1 { color: #2d3748; font-size: 24px; margin-top: 0; margin-bottom: 10px; }");
        out.println("        .desc { color: #718096; font-size: 15px; margin-bottom: 30px; }");
        // 计数器高亮显示
        out.println("        .counter-box { background: #ebf4ff; border: 2px dashed #4299e1; border-radius: 15px; padding: 20px; margin-bottom: 30px; transition: transform 0.3s; }");
        out.println("        .counter-box:hover { transform: scale(1.05); }");
        out.println("        .number { font-size: 64px; font-weight: 900; color: #e53e3e; text-shadow: 2px 2px 4px rgba(229, 62, 62, 0.3); font-family: 'Courier New', monospace; line-height: 1; margin: 15px 0; }");
        // 按钮样式
        out.println("        .btn { display: inline-block; background: linear-gradient(to right, #4fd1c5, #319795); color: white; padding: 12px 30px; border-radius: 30px; text-decoration: none; font-weight: bold; box-shadow: 0 4px 15px rgba(49, 151, 149, 0.4); transition: all 0.3s; }");
        out.println("        .btn:hover { box-shadow: 0 6px 20px rgba(49, 151, 149, 0.6); transform: translateY(-2px); }");
        // 底部个人标识区域
        out.println("        .footer { background: #2d3748; color: white; padding: 20px; font-size: 14px; margin-top: 20px; }");
        out.println("        .badge { display: inline-block; background: #ed8936; color: white; padding: 4px 12px; border-radius: 12px; font-size: 12px; font-weight: bold; margin-bottom: 10px; }");
        out.println("        .time-info { color: #a0aec0; font-size: 12px; margin-top: 10px; }");
        out.println("    </style>");
        out.println("</head>");
        
        out.println("<body>");
        out.println("    <div class='card'>");
        // 插入一张科技感的网络统计图片作为 Header
        out.println("        <img src='https://images.unsplash.com/photo-1551288049-bebda4e38f71?auto=format&fit=crop&w=800&q=80' alt='Data Analytics' class='banner-img'>");
        
        out.println("        <div class='content'>");
        out.println("            <h1>🌐 全局访问量统计</h1>");
        out.println("            <div class='desc'>基于 ServletContext 接口实现的共享计数器</div>");
        
        out.println("            <div class='counter-box'>");
        out.println("                <div style='color: #4a5568; font-size: 18px;'>您好，您是本站的第</div>");
        out.println("                <div class='number'>" + count + "</div>");
        out.println("                <div style='color: #4a5568; font-size: 18px;'>位访客！</div>");
        out.println("            </div>");
        
        out.println("            <a href='javascript:location.reload();' class='btn'>🔄 模拟下一次访问</a>");
        out.println("        </div>");
        
        // ==========================================
        // 你的专属个人标识
        // ==========================================
        out.println("        <div class='footer'>");
        out.println("            <div class='badge'>STUDENT INFO</div>");
        out.println("            <div>👨‍🎓 姓名：<strong>党子立</strong> &nbsp;|&nbsp; 🆔 学号：<strong>220162402009</strong></div>");
        out.println("            <div class='time-info'>本次访问时间：" + currentTime + "</div>");
        out.println("        </div>");
        
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
        
        out.flush();
        out.close();
    }
}
