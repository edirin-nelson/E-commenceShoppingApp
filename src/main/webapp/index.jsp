<!doctype html>
<html lang="en">
<head>
    <title>Welcome to shopping cart</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
    <a class="navbar-brand" href="index.jsp">Market Place</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">Home </a>
            </li>
            <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart</a></li>
            <li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
            <li class="nav-item"><a class="nav-link" href="#">Logout</a></li>
            <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
        </ul>
    </div>
    </div>
</nav>
    <%@include file="includes/footer.jsp"%>
</body>
</html>