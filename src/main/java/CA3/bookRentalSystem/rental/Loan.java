/*
 * @author: Heidi
 * Reference: Michelle's class notes
 */

package CA3.bookRentalSystem.rental;

import java.util.Date;
import java.util.Objects;

public class Loan {

    private int loanId;
    private int bookId;
    private String customerUsername;
    private Date loanStartDate;
    private Date loanDueDate;
    private double overdueFee;
    private Date dateReturned;

    public Loan() {
    }

    public Loan(int loanId, int bookId, String customerUsername, Date loanStartDate, Date loanDueDate, double overdueFee, Date dateReturned) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.customerUsername = customerUsername;
        this.loanStartDate = loanStartDate;
        this.loanDueDate = loanDueDate;
        this.overdueFee = overdueFee;
        this.dateReturned = dateReturned;
    }

    public int getLoanId() {
        return loanId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public Date getLoanStartDate() {
        return loanStartDate;
    }

    public Date getLoanDueDate() {
        return loanDueDate;
    }

    public double getOverdueFee() {
        return overdueFee;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public void setLoanDueDate(Date loanDueDate) {
        this.loanDueDate = loanDueDate;
    }

    public void setOverdueFee(double overdueFee) {
        this.overdueFee = overdueFee;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", bookId=" + bookId +
                ", customerUsername='" + customerUsername + '\'' +
                ", loanStartDate=" + loanStartDate +
                ", loanDueDate=" + loanDueDate +
                ", overdueFee=" + overdueFee +
                ", dateReturned=" + dateReturned +
                '}';
    }
}
