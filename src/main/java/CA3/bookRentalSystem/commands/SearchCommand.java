package CA3.bookRentalSystem.commands;

import CA3.bookRentalSystem.rental.Book;
import CA3.bookRentalSystem.repositories.BookDaoInterface;
import CA3.bookRentalSystem.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
public class SearchCommand implements Command {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public SearchCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String execute() {

        String continueTo = "../viewBooks.jsp";

        HttpSession session = request.getSession(true);

        BookDaoInterface bookDao = new BookDao("bookrentalsystem");
        //Getting the parameters from the jsp
        String type = request.getParameter("type");
        String query = request.getParameter("query");
        List<Book> result = null;

        if("title".equals(type)) {
            result = bookDao.getBooksByTitle(type);
        } else if("id".equals(query)) {
            int bookId = Integer.parseInt(query);
            //Getting book ID
            Book book = bookDao.getBookByBookId(bookId);

            if(book != null) {
                result = List.of(book);
            } else {
                result = List.of();
            }

            if(result == null ) {
                continueTo = "../error.jsp"; //go to error page
                String error = "No Book or Books found. ";
                session.setAttribute("errorMessage", error);
            } else {
                session.setAttribute("search",result);
            }
        }
        return continueTo;
    }
}