package CA3.bookRentalSystem.repositories;
/**
 * author: Heidi
 */
import CA3.bookRentalSystem.rental.Loan;

import java.util.ArrayList;

public interface LoanDaoInterface {
    public int createLoan (int userId, int bookId);
    public ArrayList<Loan> getMyLoans(int userId);

    public Loan getActiveLoanByBookId (int bookId, int userId); //find existing loans for the book in question

    public Boolean checkOverdueFeePaid (int loanId, int userId); // get overdue loan by ID ->if returns not null, check if fee == 0?

    public int returnBook (int loanId, int userId); //get loan by ID then set returnDate to the current date -> Loan Terminated
    //user must use getAllActiveLoansByUser method to get the loan ID first
}
