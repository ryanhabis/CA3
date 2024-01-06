<%--
  Created by IntelliJ IDEA.
  User: Heidi
  Date: 30/12/2023
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css">

    <title>Login Page</title>
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
<p>Login Form</p>
<form action="servlet/Controller" method="post">
    <table>
        <tr>
            <td> Username : </td><td> <input name="username" size=30 type="text" required /> </td>
        </tr>
        <tr>
            <td> Password : </td><td> <input name="password" size=30 type="password" required /> </td>
        </tr>
    </table>
    <input type="submit" value="Submit" />
    <!-- Include a hidden field to identify what the user wants to do -->
    <input type="hidden" name ="action" value="login" />
</form>
</main>

<footer>
    &copy; 2024 Book Rental. All rights reserved.
</footer>
</body>
</html>