package com.marketplace.servlet;

import com.marketplace.model.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "QuantityIncDecServlet", value = "/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter();) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));

             ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

             if (action != null && id >=1) {
                 if (action.equals("inc")) {
                     for (Cart c:cart_list) {
                         if (c.getId() == id) {
                             int quantity = c.getQuantity();
                             quantity++;
                             c.setQuantity(quantity);
                             response.sendRedirect("cart.jsp");
                         }
                     }
                 }
             }
        }
    }
}
