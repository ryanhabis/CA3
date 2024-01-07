package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.rental.Loan;
/**
 * author: Heidi
 */
import java.util.ArrayList;

public interface LoanDaoAdminInterface extends LoanDaoInterface {

    public Loan getActiveLoanByBookIdAndUserId (int userId, int bookId);

    public ArrayList<Loan> getAllLoans();
    public ArrayList <Loan> getAllActiveLoans();
    public ArrayList <Loan> getAllTerminatedLoans(); //optional, not needed

    public ArrayList<Loan> getLoansByUser(int userId);
    public ArrayList<Loan> getActiveLoansByUser(int userId); // and returnDate == null


    //Individual function
    public ArrayList<Loan> getAllOverdueLoans ();

}
