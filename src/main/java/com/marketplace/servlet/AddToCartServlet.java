package com.marketplace.servlet;

import com.marketplace.model.Cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AddToCartServlet", value = "/AddToCartServlet")
public class  AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charsetUTF-8");

        try(PrintWriter out = response.getWriter()) {
            ArrayList<Cart> cartList = new ArrayList<>();

            int id = Integer.parseInt(request.getParameter("id"));
            Cart cm = new Cart();
            cm.setId(id);
            cm.setQuantity(1);

            HttpSession session = request.getSession();;
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

            if (cart_list == null) {
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("index.jsp");            }else {
                cartList= cart_list;
                boolean exist = false;

                for (Cart c:cart_list) {
                    if(c.getId() == id) {
                        exist = true;
                        out.println("<h3 style='color:crimson; text-align:center'>Item already exist in Cart. <a href='cart.jsp'>Go to Cart Page</a>");
                    }
                }

                if (!exist) {
                    cartList.add(cm);
                    response.sendRedirect("index.jsp");
                }
            }
        }
    }
}