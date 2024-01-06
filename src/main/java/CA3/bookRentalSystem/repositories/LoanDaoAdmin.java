package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.Loan;

import java.sql.*;
import java.time.LocalDate;

public class LoanDaoAdmin extends Dao implements LoanDaoAdminInterface{
    public LoanDaoAdmin(Connection conn) {
        super(conn);
    }

    public LoanDaoAdmin(String databaseName) {
        super(databaseName);
    }

    @Override
    public Loan getActiveLoanByBookIdAndUserId(int userId, int bookId) {
        //find the currently active loan for that user and book
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loanForBook = new Loan();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans where userId = ? and bookId = ? and dateReturned is null";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names

                loanForBook.setLoanId(rs.getInt("loanId"));
                loanForBook.setBookId(rs.getInt("bookId"));
                loanForBook.setUserId(rs.getInt("userId"));
                loanForBook.setLoanStartDate(rs.getDate("loanStartDate"));
                loanForBook.setLoanDueDate(rs.getDate("loanDueDate"));
                loanForBook.setOverdueFee(rs.getDouble("overdueFee"));
                loanForBook.setDateReturned(rs.getDate("dateReturned"));

            }

        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
        } catch (DaoException e) {
            System.out.println("Dao exception: " +e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occured when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occured when closing prepared statement.");
                }
            }

            if (conn != null) {
                try {
                    freeConnection(conn);
                } catch (DaoException e) {
                    System.out.println("Dao exception caught: " + e.getMessage());
                }
            }
        }
        return loanForBook;
    }

    @Override
    public int createLoan(int userId, int bookId) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rowsAdded = 0;

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "insert into loans values (?, ?, ?, ?, ?, ?, ?)";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setInt(2, bookId);
            ps.setInt(3, userId);
            //get today's date
            LocalDate currentDate = LocalDate.now();
            LocalDate dueDate = currentDate.plusDays(4);
            ps.setDate(4, Date.valueOf(currentDate));
            ps.setDate(5, Date.valueOf(dueDate));
            ps.setDouble(6, 0);
            ps.setDate(7, null);
            rowsAdded = ps.executeUpdate();


        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
        } catch (DaoException e) {
            System.out.println("Dao exception: " +e.getMessage());
        }

        //close connections
        finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occured when closing prepared statement.");
                }
            }

            if (conn != null) {
                try {
                    freeConnection(conn);
                } catch (DaoException e) {
                    System.out.println("Dao exception caught: " + e.getMessage());
                }
            }
        }
        return rowsAdded;
    }
}

