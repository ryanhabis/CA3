<%@ page import="CA3.bookRentalSystem.rental.Book" %>
<%@ page import="CA3.bookRentalSystem.repositories.BookDaoInterface" %>
<%@ page import="CA3.bookRentalSystem.repositories.BookDao" %>
<%@ page import="CA3.bookRentalSystem.repositories.BookDaoAdminInterface" %>
<%--
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
        BookDaoAdminInterface bookChosenDao = new BookDao("bookrentalsystem");
        Book bookSelected = bookChosenDao.getBookByBookId(bookIdSelected);
        //Check if the selected book was found
        if (bookSelected != null) {
            //Set book to the session
            session.setAttribute("book", bookSelected);
            session.setAttribute("action", "borrow");
        }
%>
<form action="servlet/Controller" method="post">
<table>
    <tr><th>Book</th><th>Blurb</th><th>Author</th><th>Price €</th></tr>

    <tr>
        <td><%=bookSelected.getTitle()%></td>
        <td><%=bookSelected.getDescription()%></td>
        <td><%=bookSelected.getAuthor()%></td>
        <td><%=bookSelected.getBookPrice()%></td>
    </tr>

</table>

    <input type="hidden" name ="bookId" value="<%=bookIdSelected%>" />

    <input type="submit" value="borrow" />

    <!-- Include a hidden field to identify what the user wants to do -->
    <input type="hidden" name ="action" value="borrow" />
</form>

<a href="servlet/Controller?action=borrow">Borrow</a>
<button name="b">borrow</button><%
}else{
%>
<div>
    Error - No books found
    Please <a href="index.jsp">return to the home page</a> and try again.
</div>
<%
}
%>
    </body>
</html>
