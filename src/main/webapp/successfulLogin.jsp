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
        User.UserType currentUserType = User.UserType.valueOf(request.getParameter("userType"));

    if(userLoggedIn != null && currentUserType.equals(User.UserType.Customer)){
        //then display the basic nav
     %>
            <nav>
                //list all options available to that user type
                 <a href="borrowBook.jsp">Borrow Book</a><br/>
                <a href="returnBook.jsp">Return Book</a><br/>
                <a href="browseBooks.jsp">Browse Books</a><br/>

            </nav>
    <%
    } else if(userLoggedIn != null && currentUserType.equals(User.UserType.Admin)){
%>
    <nav>
        //list all options available to that user type
        <a href="borrowBook.jsp">Borrow Book</a><br/>
        <a href="returnBook.jsp">Return Book</a><br/>
        <a href="browseBooks.jsp">Browse Books</a><br/>
        REmove User<br/>
        Other Admin stuff...<br/>
    </nav>
<%
        }
    %>
</head>
<body>

</body>
</html>
