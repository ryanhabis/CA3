package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.exceptions.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class LoanDao extends Dao implements LoanDaoInterface{
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


        } catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
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
