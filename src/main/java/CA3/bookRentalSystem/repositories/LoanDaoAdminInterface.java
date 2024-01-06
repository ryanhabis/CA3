package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.rental.Loan;

public interface LoanDaoAdminInterface extends LoanDaoInterface {

    public Loan getActiveLoanByBookIdAndUserId (int userId, int bookId);

}
