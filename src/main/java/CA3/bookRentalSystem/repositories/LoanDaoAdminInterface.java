package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.rental.Loan;
/**
 * author: Heidi
 */
import java.util.ArrayList;

public interface LoanDaoAdminInterface extends LoanDaoInterface {

    /**
     * Method to search for a loan by userId and bookId that is also currently active.
     * @param userId - the id of the user who took out the loan.
     * @param bookId - the id of the book that was borrowed (if loan exists that is).
     * @return the Loan itself if found, otherwise return null.
     */
    public Loan getActiveLoanByBookIdAndUserId (int userId, int bookId);
    /**
     * Method to get all loans in the loans table.
     * @return an arraylist of all loans.
     */
    public ArrayList<Loan> getAllLoans();
    /**
     * Method to return all active loans.
     * @return an arraylist of loans that are currently active (dateReturned is null).
     */
    public ArrayList <Loan> getAllActiveLoans();
    /**
     * Method to get all terminated loans.
     * @return an arraylist of loans that have a dateReturned value.
     */
    public ArrayList <Loan> getAllTerminatedLoans(); //optional, not needed

    /**
     * Method to get all loans for a particular user using userId as a filter.
     * @param userId - the id of the user for whom we are retrieving loans for.
     * @return an arraylist of loans for the user in question.
     */
    public ArrayList<Loan> getLoansByUser(int userId);
    /**
     * Method for getting all active loans for a particular user using userId as a filter and where dateReturned is null.
     * @param userId - the id of the user who we are retrieving active loans for.
     * @return an arraylist of active loans for that user.
     */
    public ArrayList<Loan> getActiveLoansByUser(int userId); // and returnDate == null
    /**
     * Method to get all overdue loans
     * @return an arraylist of all overdue loans (loans with fees applied to them for being overdue).
     */
    //Individual function
    public ArrayList<Loan> getAllOverdueLoans ();

}
