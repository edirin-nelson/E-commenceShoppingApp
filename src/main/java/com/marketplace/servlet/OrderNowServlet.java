package com.marketplace.servlet;

import com.marketplace.connection.DbCon;
import com.marketplace.dao.OrderDao;
import com.marketplace.model.Order;
import com.marketplace.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "OrderNowServlet", value = "/order-now")
public class OrderNowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()){

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

            Date date = new Date();

            User auth = (User) request.getSession().getAttribute("auth");
            if (auth != null) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <=0) {
                    productQuantity =1;
                }

                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setUid(auth.getId());
                orderModel.setQuantity(productQuantity);
                orderModel.setDate(formatter.format(date));

                OrderDao orderDao = new OrderDao(DbCon.getConnection());
                boolean result = orderDao.insertOrder(orderModel);

                if (result) {
                    response.sendRedirect("orders.jsp");
                }else {
                    out.print("order failed");
                }

            }else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
