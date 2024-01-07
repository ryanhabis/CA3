<%@ page import="CA3.bookRentalSystem.rental.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Evan Mc Donnell
  Date: 07/01/2024
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css">
    <title>Return a Book</title>
</head>
<body>
<%
    //check if user has been saved to the session (has been logged in)
    User userLoggedIn = (User) session.getAttribute("user");
    if (userLoggedIn != null) {
%>
<nav>
    <a href="index.jsp">Home</a>
    <a href="login.jsp">Login</a>
    <a href="register.jsp">Register</a>
</nav>

<main>
    <h1>Return a Book:</h1>

    <form action="servlet/Controller" method="post">
        <label for="bookId">Book ID:</label>
        <input type="text" id="bookId" name="bookId" required>

        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required>

        <input type="submit" value="Submit">
        <input type="hidden" name="action" value="return">
    </form>
</main>

<footer>
    &copy; 2024 Book Rental. All rights reserved.
</footer>
<%
} else {
    response.sendRedirect("successfulLogin.jsp");
%>
<div>
    //redirect to login successful page
</div>
<%
    }
%>
</body>
</html>