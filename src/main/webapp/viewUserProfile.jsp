<%--
  Created by IntelliJ IDEA.
  User: ryanh
  Date: 07/01/2024
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="CA3.bookRentalSystem.rental.User" %>
<%@ page import="CA3.bookRentalSystem.repositories.UserDao" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View account</title>
</head>
<body>

<header>
    <h1>
        <%= "Book Rental System" %>
    </h1>
</header>

<nav>
    <a href="index.jsp">Home</a>
    <a href="login.jsp">Login</a>
    <a href="register.jsp">Register</a>
</nav>

<main>
    <p>View account</p>
    <%
        int userId = (int) session.getAttribute("userId");
        UserDao userDao = new UserDao("library");
        User user = userDao.viewUserProfile(userId);
    %>
        <!--
        Don't show the password for security reasons and haven't showed the user type because if the
        user is an admin they should know otherwise the user is by default a customer
        -->
    <table>
        <tr>
            <td>username: </td>
            <td><%= user.getUsername() %></td>
        </tr>
        <tr>
            <td>First name:</td>
            <td><%= user.getFirstName() %></td>
        </tr>
        <tr>
            <td>Last name: </td>
            <td><%= user.getLastName() %></td>
        </tr>
        <tr>
            <td>Date of birth:</td>
            <td><%= user.getDob() %></td>
        </tr>
        <tr>
            <td>Phone number</td>
            <td><%= user.getPhoneNumber()%></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><%= user.getEmail()%></td>
        </tr>
        <tr>
            <td>Address line 1:</td>
            <td><%= user.getAddressLine1()%></td>
        </tr>
        <tr>
            <td>Address line 2:</td>
            <td><%= user.getAddressLine2()%></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><%= user.getCity()%></td>
        </tr>
        <tr>
            <td>County:</td>
            <td><%= user.getCounty() %></td>
        </tr>
        <tr>
            <td>Eircode:</td>
            <td><%= user.getEircode() %></td>
        </tr>
    </table>
</main>

<footer>
    &copy; 2024 Book Rental. All rights reserved.
</footer>
</body>
</html>
