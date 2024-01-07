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

public class ReturnCommand {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ReturnCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String execute(){
        return null;
    }
}
