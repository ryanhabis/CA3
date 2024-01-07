
<%@ page import="java.util.List" %>
<%@ page import="CA3.bookRentalSystem.repositories.BookDao" %>
<%@ page import="CA3.bookRentalSystem.rental.Book" %>
<%--
  Created by IntelliJ IDEA.
  User: Heidi
  Date: 26/12/2023
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Browse Books</title>
</head>
<body>
<!-- Make facility for searching and viewing all books
    Call bookdao method for browsing all books automatically? -->
<%--<form action="controller" method="post"> <!--post this action to the controller -->--%>

<%--   <!----%>
<%--   make inputs (checkboxes/radio/button) for applying filters e.g. for books in stock, containing keyword, sorting books by genre--%>
<%--   Include a hidden field to identify what the user wants to do -->--%>
<%--<input type="hidden" name ="action" value="browseAll" /> <!--browse all by default -->--%>
<%--</form>--%>


<%
    //create the book dao
    BookDao bookDao = new BookDao("bookrentalsystem");
    List<Book> allBooks = bookDao.getAllBooks();

    if(!allBooks.isEmpty()){
    %>
    <table>
        <tr><th>Book</th><th>Blurb</th><th>Price €</th></tr>
        <%
            for(Book b: allBooks){
        %>
        <tr>
            <td><a href="borrow.jsp?bookId=<%=b.getBookId()%>"><%=b.getTitle()%></a></td> <!-- Set the book Id as a parameter -->
            <td><%=b.getDescription()%></td>
            <td><%=b.getBookPrice()%></td>
        </tr>
        <%
            }
        %>
    </table>
        <%
      }else{
        %>
    <div>
        Error - No books found
        Please <a href="../index.jsp">return to the home page</a> and try again.
    </div>
       <%
           }
       %>
</body>
</html>