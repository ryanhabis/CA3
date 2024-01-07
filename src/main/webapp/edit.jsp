
<%--
  Created by IntelliJ IDEA.
  User: ryanh
  Date: 07/01/2024
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="CA3.bookRentalSystem.rental.User" %>
<%@ page import="CA3.bookRentalSystem.repositories.UserDao" %>
<%@ page import="CA3.bookRentalSystem.repositories.UserDaoInterfaceAdmin" %>
<%@ page import="CA3.bookRentalSystem.repositories.UserDaoInterface" %>
<%@ page import="com.sun.net.httpserver.Request" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
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
<%--The following was copied from Michelle's Github and needs to be changed please!--%>
<main>
    <p>Edit user</p>
    <% int userId = Integer.parseInt(request.getParameter("userId")); %>
    <% UserDaoInterface UserDao = new UserDao("library"); %>

    <% User user = UserDao.viewUserProfile(userId); %>
    <form action="servlet/Controller" method="post">
        <!-- set firstName=?, lastName=?, dob=?, phoneNumber=?, email=?, addressLine1=?, addressLine2=?, city=?, county=?, eircode=? -->
        <table>
            <tr>
                <td>Username: </td><td> <input name="username" size=30 type="text" value="<%= user.getUsername() %>"/> </td>
            </tr>
            <tr>
                <td>Password: </td><td> <input name="password" size=30 type="password" value="<%= user.getPassword() %>"/> </td>
            </tr>
            <tr>
                <td>FirstName: </td><td> <input name="firstName" size=30 type="text" value="<%= user.getFirstName() %>"/> </td>
            </tr>
            <tr>
                <td>LastName: </td><td> <input name="lastName" size=30 type="text" value="<%= user.getLastName() %>"/> </td>
            </tr>
            <tr>
                <td>Date of birth: </td><td> <input name="dob" size=30 type="text" value="<%= user.getDob() %>"/> </td>
            </tr>
            <tr>
                <td>PhoneNumber: </td><td> <input name="phoneNumber" size=30 type="text" value="<%= user.getPhoneNumber() %>"/> </td>
            </tr>
            <tr>
                <td>Email: </td><td> <input name="email" size=30 type="text" value="<%= user.getEmail() %>"/> </td>
            </tr>
            <tr>
                <td>AddressLine1: </td><td> <input name="AddressLine1" size=30 type="text" value="<%= user.getAddressLine1() %>"/> </td>
            </tr>
            <tr>
                <td>AddressLine2: </td><td> <input name="addressLine2" size=30 type="text" value="<%= user.getAddressLine2() %>"/> </td>
            </tr>
            <tr>
                <td>City: </td><td> <input name="city" size=30 type="text" value="<%= user.getCity()%>"/> </td>
            </tr>
            <tr>
                <td>eircode: </td><td> <input name="eircode" size=30 type="text" value="<%= user.getEircode()%>"/> </td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
        <!-- Include a hidden field to identify what the user wants to do -->
        <input type="hidden" name ="action" value="editProfile" />
    </form>
</main>

<footer>
    &copy; 2024 Book Rental. All rights reserved.
</footer>
</body>
</html>
