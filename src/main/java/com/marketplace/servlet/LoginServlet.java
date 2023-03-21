package com.marketplace.servlet;

import com.marketplace.connection.DbCon;
import com.marketplace.dao.UserDao;
import com.marketplace.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            try {
                UserDao udao = new UserDao(DbCon.getConnection());
                User user = udao.userLogin(email, password);

                if (user != null){
                    request.getSession().setAttribute("auth", user);
                    response.sendRedirect("index.jsp");
                }
                else out.print("user login failed");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
