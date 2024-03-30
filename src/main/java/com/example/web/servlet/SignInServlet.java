package com.example.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {
    // 模拟一个数据库:
    private Map<String, String> users;

    public SignInServlet() {
        super();
        users = new HashMap<>();
        users.put("alice", "alice123");
        users.put("bob", "bob123");
        users.put("cat", "cat123");
        users.put("tom", "tomcat");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Sign In</h1>");
        pw.write("<form action=\"/signIn\" method=\"post\">");
        pw.write("  <p>Username: <input name=\"username\"></p>");
        pw.write("  <p>Password: <input name=\"password\" type=\"password\"  autocomplete ></p>");
        pw.write("  <p><button type=\"submit\">Sign In</button> <button><a href=\"/index\">Cancel</a></button></p>");
        pw.write("</form>");
        pw.flush();
    }

    // POST请求时处理用户登录:
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("用户登录:" + name + ":" + password);
        String expectedPassword = users.get(name.toLowerCase());
        if (expectedPassword != null && expectedPassword.equals(password)) {
            // 登录成功:
            req.getSession().setAttribute("user", name);
            resp.sendRedirect("/index");
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
