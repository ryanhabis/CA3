package CA3.bookRentalSystem.repositories;
/**
 * author: Heidi
 */

import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.Book;
import CA3.bookRentalSystem.rental.Loan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoanDao extends Dao implements LoanDaoInterface, LoanDaoAdminInterface{
    public LoanDao(Connection conn) {
        super(conn);
    }

    public LoanDao(String databaseName) {
        super(databaseName);
    }


    /**
     * Method to create a loan using bookId and userId.
     * @param bookId - the bookId of the book for which the loan will be created.
     * @param userId - the userId of the user for which the loan will be created.
     * @return an integer indicating whether or not the loan was made 0 for failure to create and 1 for 1 loan created.
     */
    @Override
    public int createLoan(int bookId, int userId) {
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
                    System.out.println("Problem occurred when closing prepared statement.");
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

    /**
     * Method to get all loans for the user in question.
     * @param userId - this userId is the user whose loans we are retrieving.
     * @return an arraylist of loans for that user.
     */
    @Override
    public ArrayList<Loan> getMyLoans(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //make arraylist
        ArrayList <Loan> loansForUser = new ArrayList<>();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans where userId = ?";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names
                Loan l = new Loan();

                l.setLoanId(rs.getInt("loanId"));
                l.setBookId(rs.getInt("bookId"));
                l.setUserId(rs.getInt("userId"));
                l.setLoanStartDate(rs.getDate("loanStartDate"));
                l.setLoanDueDate(rs.getDate("loanDueDate"));
                l.setOverdueFee(rs.getDouble("overdueFee"));
                l.setDateReturned(rs.getDate("dateReturned"));

                //add to arraylist
                loansForUser.add(l);
            }

        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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
        return loansForUser;
    }

    /**
     * Method for finding the loan record for a particular book the user in question borrowed.
     * @param bookId - the id of the book in question.
     * @param userId - the id of the user who took out the loan.
     * @return the Loan object if found, otherwise return null.
     */
    @Override
    public Loan getActiveLoanByBookId(int bookId, int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Loan l = new Loan();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans where userId = ? and bookId = ?";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names

                l.setLoanId(rs.getInt("loanId"));
                l.setBookId(rs.getInt("bookId"));
                l.setUserId(rs.getInt("userId"));
                l.setLoanStartDate(rs.getDate("loanStartDate"));
                l.setLoanDueDate(rs.getDate("loanDueDate"));
                l.setOverdueFee(rs.getDouble("overdueFee"));
                l.setDateReturned(rs.getDate("dateReturned"));

            }

        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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
        return l;
    }

    /**
     * Method for checking if fees for the loan in question are paid
     * @param loanId - the id of the loan.
     * @param userId - the id of the user who took out the loan.
     * @return true or false depending if the overdue fee field is set to a value of 0.
     */
    @Override
    public Boolean checkOverdueFeePaid(int loanId, int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = new Loan();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans where userId = ? and overdueFee = ? and loanId = ?";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, 0);
            ps.setInt(3, loanId);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names

                loan.setLoanId(rs.getInt("loanId"));
                loan.setBookId(rs.getInt("bookId"));
                loan.setUserId(rs.getInt("userId"));
                loan.setLoanStartDate(rs.getDate("loanStartDate"));
                loan.setLoanDueDate(rs.getDate("loanDueDate"));
                loan.setOverdueFee(rs.getDouble("overdueFee"));
                loan.setDateReturned(rs.getDate("dateReturned"));

            }

        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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

        return loan.getLoanId() != 0;
    }

    /**
     * Method to return a book using loan id and user id.
     * @param loanId - the loan id which should be terminated.
     * @param userId - the user id who created the loan.
     * @return an integer indicating if the book was successfully returned - 1 if returned, 0 if not.
     */
    @Override
    public int returnBook(int loanId, int userId) {
        int rowsUpdated = 0;

        //get active loans by ID
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = new Loan();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans where userId = ? and dateReturned is null and loanId = ?";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, loanId);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names

                loan.setLoanId(rs.getInt("loanId"));
                loan.setBookId(rs.getInt("bookId"));
                loan.setUserId(rs.getInt("userId"));
                loan.setLoanStartDate(rs.getDate("loanStartDate"));
                loan.setLoanDueDate(rs.getDate("loanDueDate"));
                loan.setOverdueFee(rs.getDouble("overdueFee"));
                loan.setDateReturned(rs.getDate("dateReturned"));

            }

            //now that you have the loan, set the return date to the current date and set fees to 0
            if (loan != null) {

                //make an update query
                String updateLoanQuery = "update loans set overdueFee = ?, dateReturned = ? where loanId = ?";
                //prepare statement
                ps = conn.prepareStatement(updateLoanQuery);
                ps.setDouble(1, 0);
                ps.setDate(2, Date.valueOf(LocalDate.now())); //loan should still be active to return book
                ps.setInt(3, loanId);
                rowsUpdated = ps.executeUpdate();

            }
        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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
        return rowsUpdated;
    }

    /**
     * Method to search for a loan by userId and bookId that is also currently active.
     * @param userId - the id of the user who took out the loan.
     * @param bookId - the id of the book that was borrowed (if loan exists that is).
     * @return the Loan itself if found, otherwise return null.
     */
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

    /**
     * Method to get all loans in the loans table.
     * @return an arraylist of all loans.
     */
    @Override
    public ArrayList<Loan> getAllLoans() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //make arraylist
        ArrayList <Loan> loans = new ArrayList<>();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans";
            //prepare statement
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names
                Loan l = new Loan();

                l.setLoanId(rs.getInt("loanId"));
                l.setBookId(rs.getInt("bookId"));
                l.setUserId(rs.getInt("userId"));
                l.setLoanStartDate(rs.getDate("loanStartDate"));
                l.setLoanDueDate(rs.getDate("loanDueDate"));
                l.setOverdueFee(rs.getDouble("overdueFee"));
                l.setDateReturned(rs.getDate("dateReturned"));

                //add to arraylist
                loans.add(l);
            }

        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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
        return loans;
    }

    /**
     * Method to return all active loans.
     * @return an arraylist of loans that are currently active (dateReturned is null).
     */
    @Override
    public ArrayList<Loan> getAllActiveLoans() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //make arraylist
        ArrayList <Loan> activeLoans = new ArrayList<>();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans where dateReturned is null";
            //prepare statement
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names
                Loan l = new Loan();

                l.setLoanId(rs.getInt("loanId"));
                l.setBookId(rs.getInt("bookId"));
                l.setUserId(rs.getInt("userId"));
                l.setLoanStartDate(rs.getDate("loanStartDate"));
                l.setLoanDueDate(rs.getDate("loanDueDate"));
                l.setOverdueFee(rs.getDouble("overdueFee"));
                l.setDateReturned(rs.getDate("dateReturned"));

                //add to arraylist
                activeLoans.add(l);
            }

        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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
        return activeLoans;
    }

    /**
     * Method to get all terminated loans.
     * @return an arraylist of loans that have a dateReturned value.
     */
    @Override
    public ArrayList<Loan> getAllTerminatedLoans() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //make arraylist
        ArrayList <Loan> terminatedLoans = new ArrayList<>();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans where dateReturned is not null";
            //prepare statement
            ps = conn.prepareStatement(query);
           rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names
                Loan l = new Loan();

                l.setLoanId(rs.getInt("loanId"));
                l.setBookId(rs.getInt("bookId"));
                l.setUserId(rs.getInt("userId"));
                l.setLoanStartDate(rs.getDate("loanStartDate"));
                l.setLoanDueDate(rs.getDate("loanDueDate"));
                l.setOverdueFee(rs.getDouble("overdueFee"));
                l.setDateReturned(rs.getDate("dateReturned"));

                //add to arraylist
                terminatedLoans.add(l);
            }

        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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
        return terminatedLoans;
    }

    /**
     * Method to get all loans for a particular user using userId as a filter.
     * @param userId - the id of the user for whom we are retrieving loans for.
     * @return an arraylist of loans for the user in question.
     */
    @Override
    public ArrayList<Loan> getLoansByUser(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //make arraylist
        ArrayList <Loan> loansForUser = new ArrayList<>();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans where userId = ?";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names
                Loan l = new Loan();

                l.setLoanId(rs.getInt("loanId"));
                l.setBookId(rs.getInt("bookId"));
                l.setUserId(rs.getInt("userId"));
                l.setLoanStartDate(rs.getDate("loanStartDate"));
                l.setLoanDueDate(rs.getDate("loanDueDate"));
                l.setOverdueFee(rs.getDouble("overdueFee"));
                l.setDateReturned(rs.getDate("dateReturned"));

                //add to arraylist
                loansForUser.add(l);
            }

        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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
        return loansForUser;
    }

    /**
     * Method for getting all active loans for a particular user using userId as a filter and where dateReturned is null.
     * @param userId - the id of the user who we are retrieving active loans for.
     * @return an arraylist of active loans for that user.
     */
    @Override
    public ArrayList<Loan> getActiveLoansByUser(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //make arraylist
        ArrayList <Loan> activeLoansByUser = new ArrayList<>();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans where userId = ? and dateReturned is ?";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, null);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names
                Loan l = new Loan();

                l.setLoanId(rs.getInt("loanId"));
                l.setBookId(rs.getInt("bookId"));
                l.setUserId(rs.getInt("userId"));
                l.setLoanStartDate(rs.getDate("loanStartDate"));
                l.setLoanDueDate(rs.getDate("loanDueDate"));
                l.setOverdueFee(rs.getDouble("overdueFee"));
                l.setDateReturned(rs.getDate("dateReturned"));

                //add to arraylist
                activeLoansByUser.add(l);
            }

        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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
        return activeLoansByUser;
    }

    /**
     * Method to get all overdue loans
     * @return an arraylist of all overdue loans (loans with fees applied to them for being overdue).
     */
    @Override
    public ArrayList<Loan> getAllOverdueLoans() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = new Loan();

        //make arraylist
        ArrayList <Loan> overdueLoans = new ArrayList<>();

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "select * from loans overdueFee = ? ";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, 0);
            rs = ps.executeQuery();

            while (rs.next()) {
                //allocate the values returned from db to the right column names

                loan.setLoanId(rs.getInt("loanId"));
                loan.setBookId(rs.getInt("bookId"));
                loan.setUserId(rs.getInt("userId"));
                loan.setLoanStartDate(rs.getDate("loanStartDate"));
                loan.setLoanDueDate(rs.getDate("loanDueDate"));
                loan.setOverdueFee(rs.getDouble("overdueFee"));
                loan.setDateReturned(rs.getDate("dateReturned"));

                overdueLoans.add(loan);
            }

        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }

        //close connections
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing result set.");
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occurred when closing prepared statement.");
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

        return overdueLoans;
    }


}
