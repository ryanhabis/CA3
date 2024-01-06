package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.rental.Loan;

public interface LoanDaoAdminInterface extends LoanDaoInterface {

    public Loan getActiveLoanByBookIdAndUsername (String username, int bookId);

}
