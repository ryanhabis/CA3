package CA3.bookRentalSystem.commands;
/**
 * Command to borrow a book from the database
 * Checks if books available, if user is signed up
 * Creates a new loan if they're eligible
 * @author: Heidi
 * @author: Evan
 * Reference: Michelle's notes
 **/

import CA3.bookRentalSystem.rental.Book;
import CA3.bookRentalSystem.rental.User;
import CA3.bookRentalSystem.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BorrowCommand implements Command{
    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * Constructor for BorrowCommand
     * @param request ServLet Request
     * @param response ServerLet Response
     */
    public BorrowCommand (HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }

    /**
     * Executes borrow command
     * Checks the stock and user account then creates loan
     * Redirects pages based on success or failure
     * @return String indicating next page
     */
    @Override
    public String execute() {
        //set the string value to be returned
        String continueTo = "../successfulBorrow.jsp";
        //create session to execute borrow
        HttpSession session = request.getSession(true);

        //get book from session
       // Book bookToBorrow = (Book) request.getAttribute("book");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookDaoInterface bookdao = new BookDao("bookrentalsystem");
        Book bookToBorrow = bookdao.getBookByBookId(bookId);
     //   int quantityInStock = bookToBorrow.getQuantityInStock();
        //int quantityInStock = Integer.parseInt(request.getParameter("quantityInStock"));
        //if book has sufficient stock
        if (bookToBorrow != null) {
            //make all relevant daos to borrow book
            UserDaoInterfaceAdmin userDao = new UserDao("bookrentalsystem");
            BookDaoAdminInterface bookDao = new BookDao("bookrentalsystem");
            LoanDaoAdminInterface loanDao = new LoanDao("bookrentalsystem");

       //     int bookId = Integer.parseInt(request.getParameter("bookId"));
       //     int bookId = bookToBorrow.getBookId();
       //     User currentUser = (User)request.getAttribute("user");
            int userId = Integer.parseInt(request.getParameter("userI   d"));
      //      int userId = currentUser.getUserId();
            if (bookId == -1) { //if no matching user is found
                continueTo = "../error.jsp"; //go to error page
                String error = "Incorrect credentials supplied. Please <a href=\"login.jsp\">try again.</a>";
                session.setAttribute("errorMessage", error);
            } else {
                continueTo = "../successfulBorrow.jsp"; //otherwise, continue to the loginSuccessful page
                //get book object for ID
            //    Book bookToBorrow = bookDao.getBookByBookId(bookId);
                //borrow book
                if (bookDao.reserveCopy(bookToBorrow) && userDao.checkAccountEnabled(userId) && loanDao.getActiveLoanByBookIdAndUserId(userId, bookId) == null) {
                    //then create loan and decrease stock in books
                    bookDao.updateStock(bookId, bookToBorrow.getQuantityInStock()-1);
                }

//
            }
        } else {
            continueTo = "../error.jsp"; //go to error page
            String error = "No username and/or password supplied. Please <a href=\"../login.jsp\">try again.</a>";
            session.setAttribute("errorMessage", error);
        }

        return continueTo;
    }
}
