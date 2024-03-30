package com.example.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String) req.getSession().getAttribute("user");
        if (user == null) user =  "Guest";
        resp.setContentType("text/html");
        resp.setHeader("X-Powered-By", "Java EE Servlet");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Welcome, " + user + "</h1>");
        if (user.equals("Guest")) {
            // 未登录，显示登录链接:
            pw.write("<p><a href=\"/signIn\">Sign In</a> / <span>Sign Out</span></p>");
        } else {
            // 已登录，显示登出链接:
            pw.write("<p><span>Sign In</span> / <a href=\"/signOut\">Sign Out</a></p>");
        }
        pw.flush();
    }

    private String parseLanguageFromCookie(HttpServletRequest req) {
        // 获取请求附带的所有Cookie:
        Cookie[] cookies = req.getCookies();
        // 如果获取到Cookie:
        if (cookies != null) {
            // 循环每个Cookie:
            for (Cookie cookie : cookies) {
                // 如果Cookie名称为lang:
                if (cookie.getName().equals("lang")) {
                    // 返回Cookie的值:
                    return cookie.getValue();
                }
            }
        }
        // 返回默认值:
        return "en";
    }
}
