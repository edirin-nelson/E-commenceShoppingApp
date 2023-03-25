package com.marketplace.servlet;

import com.marketplace.connection.DbCon;
import com.marketplace.dao.OrderDao;
import com.marketplace.model.Cart;
import com.marketplace.model.Order;
import com.marketplace.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "CheckOutServlet", value = "/cart-check-out")
public class CheckOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            //retrieve all cart products
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            //user authentication
            User auth = (User) request.getSession().getAttribute("auth");

            //check auth and cart list
            if(cart_list != null && auth!=null) {
                for(Cart c:cart_list) {
                    //prepare the order object
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUid(auth.getId());
                    order.setQuantity(c.getQuantity());
                    order.setDate(formatter.format(date));

                    //instantiate the Dao class
                    OrderDao oDao = new OrderDao(DbCon.getConnection());
                    //calling the insert method
                    boolean result = oDao.insertOrder(order);
                     if(!result) break;
                }
                cart_list.clear();
                response.sendRedirect("orders.jsp");

            }else {
                if(auth==null) response.sendRedirect("login.jsp");
//                response.sendRedirect("cart.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
