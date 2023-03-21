<%@ page import="com.marketplace.connection.DbCon" %>
<%@ page import="com.marketplace.model.User" %>
<% User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null)
        request.setAttribute("auth", auth);
%>
<!doctype html>
<html lang="en">
<head>
    <title>Welcome to MarketPlace</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
    <%@include file="includes/navbar.jsp"%>

    <% out.print(DbCon.getConnection()); %>

    <%@include file="includes/footer.jsp"%>
</body>
</html>