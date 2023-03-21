<%@ page import="com.marketplace.model.User" %>
<%@ page import="com.marketplace.model.Cart" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 3/18/23
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null)
        response.sendRedirect("index.jsp");

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!doctype html>
<html lang="en">
<head>
    <title>Shopping Cart login</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
    <%@include file="includes/navbar.jsp"%>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Login</div>
        <div class="card-body">
            <form action="LoginServlet" method="post">

                <div class="form-group">
                    <label>Email</label>
                    <input type="email" class="form-control" name="login-email" placeholder="Enter your email" required>
                </div>

                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="login-password" placeholder="*******" required>
                </div>

                <div class="text-center my-3">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>

            </form>
        </div>
    </div>
</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>
