<%--
  Created by IntelliJ IDEA.
  User: Heidi
  Date: 30/12/2023
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css">

    <title>Sign Up Page</title>
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

<p>Sign-up</p>
<form action="servlet/Controller" method="post">
    <table>
        <tr>
            <td> Username : </td><td> <input name="username" size=30 type="text" required /> </td>
        </tr>
        <tr>
            <td> Password : </td><td> <input name="password" size=30 type="password" required /> </td>
        </tr>
        <tr>
            <td> First name : </td><td> <input name="fName" size=12 type="text" required /> </td>
        </tr>
        <tr>
            <td> Last name : </td><td> <input name="lName" size=30 type="text" required /> </td>
        </tr>
        <tr>
            <td> Date of Birth : </td><td> <input name="dob" type="date" required /> </td>
        </tr>
        <tr>
            <td> Contact Number: </td><td> <input name="phoneNum" size=20 type="text" /> </td>
        </tr>
        <tr>
            <td> Email : </td><td> <input name="email" size=30 type="text" required /> </td>
        </tr>
        <tr>
            <td> Address Line 1 : </td><td> <input name="addressLine1" size=30 type="text" required /> </td>
        </tr>
        <tr>
            <td> Address Line 2 : </td><td> <input name="addressLine2" size=30 type="text"  /> </td>
        </tr>
        <tr>
            <td> City : </td><td> <input name="city" size=25 type="text" required /> </td>
        </tr>
        <tr>
            <td> County : </td><td> <input name="county" size=20 type="text"  required/> </td>
        </tr>
        <tr>
            <td> Eircode : </td><td> <input name="eircode" size=10 type="text"  required/> </td>
        </tr>
        <input type="hidden" name ="userId" value="0" />
        <input type="hidden" name ="accountStatus" value="enabled" />
        <input type="hidden" name ="userType" value="customer" />
    </table>
    <input type="submit" value="register" />
    <!-- Include a hidden field to identify what the user wants to do -->
    <input type="hidden" name ="action" value="register" />
</form>
<footer>
    &copy; 2024 Book Rental. All rights reserved.
</footer>
</body>
</html>
