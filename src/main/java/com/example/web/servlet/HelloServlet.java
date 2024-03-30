package com.example.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/html");
        PrintWriter respWriter = resp.getWriter();
        respWriter.write("<h1>Welcome to the lesson04!</h1>");
        respWriter.write("<h2>This is hello page!</h2>");
        respWriter.write("<h3>Hello, " + name + "!</h3>");
        respWriter.flush();
    }
}
