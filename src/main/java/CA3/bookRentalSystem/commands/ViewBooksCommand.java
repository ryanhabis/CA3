package CA3.bookRentalSystem.commands;

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

    public ViewBooksCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String execute() {

        String continueTo = "../viewBooks.jsp";

        HttpSession session = request.getSession(true);

        BookDaoInterface bookDao = new BookDao("bookrentalsystem");
        ArrayList<Book> books = bookDao.getAllBooks();

        if(books != null && !books.isEmpty()) {
            session.setAttribute("books",books);
        } else {
            continueTo = "../error.jsp"; //go to error page
            String error = "No Book or Books found. ";
            session.setAttribute("errorMessage", error);
        }

        return continueTo;
    }
}
