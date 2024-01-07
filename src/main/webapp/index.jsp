<%@ page import="CA3.bookRentalSystem.rental.User" %>

<%--<%@ page import="com.example.CA3.rental.User" %>--%>
<%--/**--%>
<%--* author: Heidi--%>
<%--*/--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css">

    <title>Book Rental Home</title>
</head>
<body>
<header>
    <h1><%= "Book Rental System" %>
    </h1>
</header>

<%
    //check if user has been saved to the session (has been logged in)
    User userLoggedIn = (User) session.getAttribute("user");
    if(userLoggedIn == null){
%>
<nav>
    <a href="index.jsp">Home</a>
    <a href="login.jsp">Login</a>
    <a href="register.jsp">Register</a>
</nav>

<main>
    <h2>Welcome to the Book Rental System!</h2>
    <p>
        Explore our vast collection of books and enjoy the convenience of renting them for your reading pleasure.
    </p>
    <p>
        Whether you're a book enthusiast or a casual reader, our system offers a wide range of genres to cater to your literary preferences.
    </p>
</main>

<footer>
    &copy; 2024 Book Rental. All rights reserved.
</footer>
<%}else{
%>
<div>
    //redirect to login successful page
</div>
<%
    }
%>
</body>
</html>