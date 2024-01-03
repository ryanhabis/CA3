<%@ page import="CA3.bookRentalSystem.rental.User" %><%--
  Created by IntelliJ IDEA.
  User: Heidi
  Date: 26/12/2023
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <p>You have reached the successful login page</p>

    <%
      User userLoggedIn = (User)session.getAttribute("user");

    if(userLoggedIn != null){
        //then display the basic nav
     %>
            <nav>
                //list all options available to that user type
                 <a href="borrowBook.jsp">Borrow Book</a><br/>
                <a href="returnBook.jsp">Return Book</a><br/>
                <a href="browseBooks.jsp">Browse Books</a><br/>

            </nav>
    <%
    }
%>

</head>
<body>

</body>
</html>
