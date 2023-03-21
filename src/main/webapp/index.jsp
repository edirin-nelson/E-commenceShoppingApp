<%@ page import="com.marketplace.connection.DbCon" %>
<%@ page import="com.marketplace.model.User" %>
<%@ page import="com.marketplace.dao.ProductDao" %>
<%@ page import="com.marketplace.model.Product" %>
<%@ page import="java.util.List" %>
<% User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null)
        request.setAttribute("auth", auth);

    ProductDao pd = new ProductDao(DbCon.getConnection());
    List<Product> products = pd.getAllProduct();
%>
<!doctype html>
<html lang="en">
<head>
    <title>Welcome to MarketPlace</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
    <%@include file="includes/navbar.jsp"%>

    <div class="container">
        <div class="card-header my-3">All Products</div>
        <div class="row">
            <%
            if (!products.isEmpty()) {
                for (Product p : products) { %>
                    <div class="col-md-3 my-3">
                        <div class="card w-100" style="width: 18rem;">
                            <img src="product-image/<%= p.getImage()%>" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title"><%=p.getName()%></h5>
                                <h6 class="price">Price : $<%= p.getPrice()%></h6>
                                <h6 class="category">Category: <%= p.getCategory() %></h6>
                                <div class="mt-3 d-flex justify-content-between">
                                    <a href="#" class="btn btn-dark">Add to Cart</a>
                                    <a href="#" class="btn btn-primary">Buy Now</a>
                                </div>

                            </div>
                         </div>
                    </div>
                <%}
            }
            %>

        </div>
    </div>

    <% out.print(DbCon.getConnection()); %>

    <%@include file="includes/footer.jsp"%>
</body>
</html>