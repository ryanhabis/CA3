<%@ page import="CA3.bookRentalSystem.rental.Book" %>
<%@ page import="CA3.bookRentalSystem.repositories.BookDaoInterface" %>
<%@ page import="CA3.bookRentalSystem.repositories.BookDao" %>
<%@ page import="CA3.bookRentalSystem.repositories.BookDaoAdminInterface" %>
<%@ page import="CA3.bookRentalSystem.repositories.BookDaoAdmin" %><%--
  Created by IntelliJ IDEA.
  User: Heidi
  Date: 27/12/2023
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Borrow Book</title>
</head>
<body>
Hello there, yes you would like to borrow a book eh?
<%   int bookIdSelected = Integer.parseInt(request.getParameter("bookId"));

     //get info for that book and display
    if(bookIdSelected != -1){
        //then get the whole book object
        //create bookdao
        BookDaoAdminInterface bookChosenDao = new BookDaoAdmin("bookrentalsystem");
        Book bookSelected = bookChosenDao.getBookByBookId(bookIdSelected);
%>
<table>
    <tr><th>Book</th><th>Blurb</th><th>Author</th><th>Price €</th></tr>

    <tr>
        <td><%=bookSelected.getTitle()%></td>
        <td><%=bookSelected.getDescription()%></td>
        <td><%=bookSelected.getAuthor()%></td>
        <td><%=bookSelected.getBookPrice()%></td>
    </tr>

</table>
<%--Set book to the session--%>
<%session.setAttribute("book", bookSelected);%>

<input type="button" value="Borrow Book" />
<!-- Include a hidden field to identify what the user wants to do -->
<input type="hidden" name ="action" value="borrow" />
<button>Borrow Book</button>

<%
}else{
%>
<div>
    Error - No books found
    Please <a href="index.jsp">return to the home page</a> and try again.
</div>
%>
    </body>
</html>
