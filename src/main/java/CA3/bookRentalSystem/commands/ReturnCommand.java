package CA3.bookRentalSystem.commands;
/**
 * Command to return a book
 * Updates the book and loan in database based on user returning book
 * @author: Evan
 * Reference: Michelle's notes
 **/

import CA3.bookRentalSystem.rental.User;
import CA3.bookRentalSystem.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReturnCommand implements Command {
    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * Constructor for ReturnCommand
     * @param request ServLet Request
     * @param response ServerLet Response
     */
    public ReturnCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Executes command to return book
     * Gets information from session
     * Updates the loan and book tables in the database
     * Redirects based on the outcome, login if user is not logged in or there's no loanId
     * @return String going to login page if not sucessful, it goes to returnSuccessful.jsp if its successful
     */
    @Override
    public String execute() {

        String continueTo = "../return.jsp";

        HttpSession session = request.getSession();

        //Getting logged in user from session
        User loggedIn = (User) session.getAttribute("user");
        //Getting loanId from session
        Integer loanId = (Integer) session.getAttribute("loanId");

        //Checking if user is not logged in or if loan doesn't exist
        if (loggedIn == null || loanId == null) {
            return "redirect:login.jsp";
        }

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int userId = loggedIn.getUserId();

        LoanDao loanDao = new LoanDao("bookrentalsystem");
        BookDao bookDao = new BookDao("bookrentalsystem");

            //Returning book to bookDao
            bookDao.returnBook(bookId);
            //Returning book to loanDao
            loanDao.returnBook(userId, loanId);

            //Removing loanId from the session
            session.removeAttribute("loanId");

            //
            return "redirect:successfulReturn.jsp";

    }
}
