<%--
  Created by IntelliJ IDEA.
  User: Evan
  Date: 07/01/2024
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="CA3.bookRentalSystem.repositories.BookDao" %>
<%@ page import="CA3.bookRentalSystem.rental.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Search Books</title>
</head>
<body>
    <h1>Search for a Book</h1>

    <%
        //Stores which type of radio value is currently entered
        String type = request.getParameter("type");
        //Used to update the radio value
        String query = request.getParameter("query");
        //create the book dao
        BookDao bookDao = new BookDao("bookrentalsystem");
        List<Book> result = null;

        //Checking if the type is title
        if("title".equals(type)) {
            //Setting result
            result = bookDao.getBooksByTitle(query);
            //Checking if the type is ID
        } else if("id".equals(type)) {
            int bookId = Integer.parseInt(query);
            //Getting book ID
            Book book = bookDao.getBookByBookId(bookId);
            //Creating result
            result = new ArrayList<>();
            //Checking for null
            if(book != null) {
                //Adding book to result
                result.add(book);
            }
        }

        //Checking result for null
        if(result != null && !result.isEmpty()) {
    %>

    <form action="servlet/Controller" method="post">
        <table>
            <tr><th>Book</th><th>Blurb</th><th>Price €</th></tr>
            <%
                for(Book b: result){
            %>
            <tr>
                <!-- Set the book Id as a parameter -->
                <td><a href="borrow.jsp?bookId=<%=b.getBookId()%>"><%=b.getTitle()%></a></td>
                <td><%=b.getDescription()%></td>
                <td><%=b.getBookPrice()%></td>
            </tr>
            <%
                }
            %>
        </table>

        <!-- Search by title active initially -->
        <input type="radio" name="type" value="title" checked>Search using Title
        <!-- Can set search by ID to active -->
        <input type="radio" name="type" value="id">Search using bookId
        <!-- Used to change search type -->
        <input type="text" name="query" required>
        <input type="submit" value="search">

    </form>

    <a href="servlet/Controller?action=search">Search</a>
    <button name="s">search</button><%
        } else {
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
