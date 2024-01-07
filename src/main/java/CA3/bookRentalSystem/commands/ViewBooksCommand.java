package CA3.bookRentalSystem.commands;
/**
 * The command to view all books in the database.
 * Retrieves the books from the database and stores them in the session
 * Goes to error page if none are found
 * @author: Heidi
 * @author: Evan
 * Reference: Michelle's notes
 **/

import CA3.bookRentalSystem.rental.Book;
import CA3.bookRentalSystem.repositories.BookDaoInterface;
import CA3.bookRentalSystem.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

public class ViewBooksCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * Constructor for ViewBooksCommand
     * @param request ServLet Request
     * @param response ServerLet Response
     */
    public ViewBooksCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Executes view books command
     * Gets list of books from database and stores them in session
     * Goes to error page if none found
     * @return String indicating next page
     */
    @Override
    public String execute() {

        String continueTo = "../viewBooks.jsp";

        HttpSession session = request.getSession(true);

        BookDaoInterface bookDao = new BookDao("bookrentalsystem");
        ArrayList<Book> books = bookDao.getAllBooks();

        //Checking if books not null and not empty
        if(books != null && !books.isEmpty()) {
            //Storing the books
            session.setAttribute("books",books);
        } else {
            continueTo = "../error.jsp"; //go to error page
            String error = "No Book or Books found. ";
            session.setAttribute("errorMessage", error);
        }

        return continueTo;
    }
}
