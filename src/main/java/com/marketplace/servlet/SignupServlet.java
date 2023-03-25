package com.marketplace.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "SignupServlet", value = "/user-signup")
public class SignupServlet extends HttpServlet {
//    private Connection con;
//    private RequestDispatcher dispatcher;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String u_name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass ");
        String phone_number = request.getParameter("contact");

        RequestDispatcher dispatcher = null;
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MarketPlaceDB", "root", "Bongo@501");
            PreparedStatement pst = con.prepareStatement("INSERT INTO users (u_name,email,password,phone_number) values (?,?,?,?)");
            pst.setString(1, u_name);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, phone_number);

           int rowCount = pst.executeUpdate();
           dispatcher = request.getRequestDispatcher("signup.jsp");
            if (rowCount >0) {
               request.setAttribute("status", "success");
           }else {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
       }catch (Exception e){
           e.printStackTrace();
       }finally {
            try {
                con.close();
            } catch (SQLException e) {
                //TODO Auto -generated catch block
                e.printStackTrace();
            }
        }
    }
}
