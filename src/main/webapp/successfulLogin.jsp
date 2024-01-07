<%@ page import="CA3.bookRentalSystem.rental.User" %>
<%@ page import="CA3.bookRentalSystem.repositories.UserDaoInterfaceAdmin" %>

<%@ page import="CA3.bookRentalSystem.repositories.UserDaoInterface" %>
<%@ page import="CA3.bookRentalSystem.repositories.UserDao" %><%--
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
        UserDaoInterface userDao = new UserDao("bookrentalsystem");
        String username = String.valueOf(request.getAttribute("username"));

       String daoUserType = userDao.getUserType(username);
       String currentUserType = request.getParameter("userType");
     //   String currentUserType = (String) userLoggedIn.getUserType().name();
     //   User.UserType currentUserType = (User.UserType) User.UserType.valueOf(String.valueOf(request.getAttribute("userType")));
       // String currentUserType = request.getAttribute("userType").toString();
        String u = "CUSTOMER";
    //    User.UserType ut = null;

//        userLoggedIn.setUserType(currentUserType);
//    if(userLoggedIn != null && username != null && currentUserType != null && currentUserType.equalsIgnoreCase("Customer")){
//        //then display the basic nav
     %>
            <nav>

                 <a href="borrow.jsp">Borrow Book</a><br/>
                <a href="returnBook.jsp">Return Book</a><br/>
                <a href="viewBooks.jsp">Browse Books</a><br/>

            </nav>
    <%
  //  } else if(userLoggedIn != null && currentUserType != null && currentUserType.equals("Admin")){
%>
<%--    <nav>--%>
<%--        //list all options available to that user type--%>
<%--        <a href="borrow.jsp">Borrow Book</a><br/>--%>
<%--        <a href="returnBook.jsp">Return Book</a><br/>--%>
<%--        <a href="viewBooks.jsp">Browse Books</a><br/>--%>
<%--        REmove User<br/>--%>
<%--        Other Admin stuff...<br/>--%>
<%--    </nav>--%>
<%
    //    }

    %>
</head>
<body>

</body>
</html>
