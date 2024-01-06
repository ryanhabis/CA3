package CA3.bookRentalSystem.commands;

import CA3.bookRentalSystem.rental.Book;
import CA3.bookRentalSystem.rental.User;
import CA3.bookRentalSystem.repositories.BookDaoAdmin;
import CA3.bookRentalSystem.repositories.BookDaoAdminInterface;
import CA3.bookRentalSystem.repositories.UserDaoAdmin;
import CA3.bookRentalSystem.repositories.UserDaoInterfaceAdmin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BorrowCommand implements Command{
    private HttpServletRequest request;
    private HttpServletResponse response;

    public BorrowCommand (HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }
    @Override
    public String execute() {
        //set the string value to be returned
        String continueTo = "../successfulBorrow.jsp";
        //create session to execute borrow
        HttpSession session = request.getSession(true);

        int quantityInStock = Integer.parseInt(request.getParameter("quantityInStock"));
        //if book has sufficient stock
        if (quantityInStock > 0) {
            //make all relevant daos to borrow book
            UserDaoInterfaceAdmin userDao = new UserDaoAdmin("bookrentalsystem");
            BookDaoAdminInterface bookDao = new BookDaoAdmin("bookrentalsystem");
            //get book
//            Book bookToBorrow = request.getParameter("book");
//
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            //get book object for ID

            if (bookId == -1) { //if no matching user is found
                continueTo = "../error.jsp"; //go to error page
                String error = "Incorrect credentials supplied. Please <a href=\"login.jsp\">try again.</a>";
                session.setAttribute("errorMessage", error);
            } else {
                continueTo = "../successfulBorrow.jsp"; //otherwise, continue to the loginSuccessful page
                //get book object for ID
                Book bookToBorrow = bookDao.getBookByBookId(bookId);
                //borrow book
                bookDao.reserveCopy(bookToBorrow); //reserve copy

//                session.setAttribute("username", username); //set the username variable to the current session
//                session.setAttribute("user", userFound); //set the user object to the current session
            }
        } else {
            continueTo = "../error.jsp"; //go to error page
            String error = "No username and/or password supplied. Please <a href=\"../login.jsp\">try again.</a>";
            session.setAttribute("errorMessage", error);
        }

        return continueTo;
    }
}
