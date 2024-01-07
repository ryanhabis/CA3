package CA3.bookRentalSystem.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanDaoTest {

    LoanDaoAdminInterface dao = new LoanDao("bookrentalsystem");
    
    @Test
    void createLoan() {
        int result = dao.createLoan(1, 1);
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    void getMyLoans() {

    }

    @Test
    void getActiveLoanByBookId() {
    }

    @Test
    void checkOverdueFeePaid() {
    }

    @Test
    void returnBook() {
    }

    @Test
    void getActiveLoanByBookIdAndUserId() {
    }

    @Test
    void getAllLoans() {
    }

    @Test
    void getAllActiveLoans() {
    }

    @Test
    void getAllTerminatedLoans() {
    }

    @Test
    void getLoansByUser() {
    }

    @Test
    void getActiveLoansByUser() {
    }

    @Test
    void getAllOverdueLoans() {
    }
}