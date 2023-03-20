<%@ page import="com.marketplace.connection.DbCon" %>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
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