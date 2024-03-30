package com.example.web.mvc.service;

import com.example.web.mvc.model.School;
import com.example.web.mvc.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 假装从数据库读取:
        School school = new School("No.1 Middle School", "101 South Street");
        User user = new User(123, "Alice", school);
        // 放入 Request 中:
        req.setAttribute("user", user);
        // forward 给 /WEB-INF/user.jsp 处理， url 地址仍然是 /user
        req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    }
}
