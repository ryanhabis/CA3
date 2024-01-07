package CA3.bookRentalSystem.repositories;
/**
 * author: Heidi
 */
import CA3.bookRentalSystem.rental.Loan;

import java.util.ArrayList;

public interface LoanDaoInterface {
    /**
     * Method to create a loan using bookId and userId.
     * @param bookId - the bookId of the book for which the loan will be created.
     * @param userId - the userId of the user for which the loan will be created.
     * @return an integer indicating whether or not the loan was made 0 for failure to create and 1 for 1 loan created.
     */
    public int createLoan (int userId, int bookId);
    /**
     * Method to get all loans for the user in question.
     * @param userId - this userId is the user whose loans we are retrieving.
     * @return an arraylist of loans for that user.
     */
    public ArrayList<Loan> getMyLoans(int userId);
    /**
     * Method for finding the loan record for a particular book the user in question borrowed.
     * @param bookId - the id of the book in question.
     * @param userId - the id of the user who took out the loan.
     * @return the Loan object if found, otherwise return null.
     */
    public Loan getActiveLoanByBookId (int bookId, int userId); //find existing loans for the book in question
    /**
     * Method for checking if fees for the loan in question are paid
     * @param loanId - the id of the loan.
     * @param userId - the id of the user who took out the loan.
     * @return true or false depending if the overdue fee field is set to a value of 0.
     */
    public Boolean checkOverdueFeePaid (int loanId, int userId); // get overdue loan by ID ->if returns not null, check if fee == 0?
    /**
     * Method to return a book using loan id and user id.
     * @param loanId - the loan id which should be terminated.
     * @param userId - the user id who created the loan.
     * @return an integer indicating if the book was successfully returned - 1 if returned, 0 if not.
     */
    public int returnBook (int loanId, int userId); //get loan by ID then set returnDate to the current date -> Loan Terminated
    //user must use getAllActiveLoansByUser method to get the loan ID first
}
