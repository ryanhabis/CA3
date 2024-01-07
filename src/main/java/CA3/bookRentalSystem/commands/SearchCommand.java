package CA3.bookRentalSystem.commands;
/**
 * @author: Evan
 * Reference: Michelle's notes
 **/

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

    /**
     * Constructor for SearchCommand
     * @param request ServLet Request
     * @param response ServerLet Response
     */
    public SearchCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Executes Search command
     * Processes the search and queries the database to find the appropriate title or ID
     * @return String with the viewBooks.jsp if successful or the error.jsp if not
     */
    @Override
    public String execute() {

        String continueTo = "../viewBooks.jsp";

        HttpSession session = request.getSession(true);

        BookDaoInterface bookDao = new BookDao("bookrentalsystem");
        //Getting the parameters from the jsp
        String type = request.getParameter("type");
        String query = request.getParameter("query");
        List<Book> result = null;

        //Checking if the type is title
        if("title".equals(type)) {
            //Getting books by title
            result = bookDao.getBooksByTitle(type);
            //Checking if the type is id
        } else if("id".equals(query)) {
            int bookId = Integer.parseInt(query);
            //Getting book ID
            Book book = bookDao.getBookByBookId(bookId);

            //Checking if book is not null
            if(book != null) {
                //Returning list of books
                result = List.of(book);
            } else {
                //Returning empty list
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
