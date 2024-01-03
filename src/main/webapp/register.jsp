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
    <title>Sign Up Page</title>
</head>
<body>
<%--The following code was taken from Michelle's Github and should be changed--%>
<p>Registration form</p>
<form action="servlet/Controller" method="post">
    <table>
        <tr>
            <td> Username : </td><td> <input name="username" size=30 type="text" required /> </td>
        </tr>
        <tr>
            <td> Password : </td><td> <input name="password" size=30 type="password" required /> </td>
        </tr>
        <tr>
            <td> First name : </td><td> <input name="fName" size=30 type="text" required /> </td>
        </tr>
        <tr>
            <td> Last name : </td><td> <input name="lName" size=30 type="text" required /> </td>
        </tr>
    </table>
    <input type="submit" value="register" />
    <!-- Include a hidden field to identify what the user wants to do -->
    <input type="hidden" name ="action" value="register" />
</form>
</body>
</html>
