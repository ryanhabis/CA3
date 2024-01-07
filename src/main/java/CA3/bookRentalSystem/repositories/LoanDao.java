package CA3.bookRentalSystem.repositories;

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
        return loansForUser;
    }

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
        return l;
    }

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

        return loan.getLoanId() != 0;
    }

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
        return rowsUpdated;
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
        return loans;
    }

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
        return activeLoans;
    }

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
        return terminatedLoans;
    }

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
        return loansForUser;
    }

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
        return activeLoansByUser;
    }




}
