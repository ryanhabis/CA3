<%@ page import="CA3.bookRentalSystem.rental.User" %>

<%--<%@ page import="com.example.CA3.rental.User" %>--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Rental Home</title>
</head>
<body>
<h1><%= "Book Rental System" %>
</h1>
<br/>

<%
    //check if user has been saved to the session (has been logged in)
    User userLoggedIn = (User) session.getAttribute("user");
    if(userLoggedIn == null){
%>
<a href="login.jsp">Login</a><br/>
<a href="register.jsp">Register</a>
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