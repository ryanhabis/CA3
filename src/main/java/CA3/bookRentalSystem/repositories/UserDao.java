/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;



import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao implements UserDaoInterface {


    public UserDao(Connection conn) {
        super(conn);
    }

    public UserDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method logs the user into his account if it's on the database.
     * @param username checks if the username is in the database.
     * @param password checks if the password is in the database.
     * @return a boolean that checks if the user is registered or not.
     *
     * I used this code for reference.
     * @referance https://stackoverflow.com/questions/35083680/check-if-record-exists-in-database-with-resultset
     */
    @Override
    public boolean login(String username, String password)
    {
        if (username == null || password == null)
        {
            throw new IllegalArgumentException("You have to type in username and password it cannot be left blank.");
        }

        Connection conn;
        try {
            conn = getConnection();
            String query = "select * from users where username = ? and password = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();


            if (rs.next())
            {
                String databaseUsername = rs.getString("username");
                String databasePassword = rs.getString("password");
                if (username.equals(databaseUsername) && password.equals(databasePassword)) {
                    System.out.println("Username and password found - logging you in...");
                    return true;
                }
                else
                {
                    System.out.println("user account is not registered yet");
                }
            }
            else
            {
                System.out.println("you need to register.");
            }

        }
        catch (SQLException | DaoException e)
        {
            throw new RuntimeException(e.getMessage());
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Exception message: " + e.getMessage());
                System.out.println("Problem occured when closing connection statement.");
            }
        }
        return false;
    }

    /**
     * This method checks if the user is either an admin or a customer.
     * @param username checks the username of the user
     * @return the type of user the person is
     * @throws DaoException
     */
        @Override
        public String getUserType(String username)  {
            Connection conn = null;
            String status = null;
            try
            {
                conn = getConnection();
                String query = "select userType from users where username = ?";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1,username);
                ResultSet rs = ps.executeQuery();

                if (rs.next())
                {
                    status = rs.getString("userType");
                }
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            } catch (DaoException e) {

                throw new RuntimeException(e);
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occured when closing connection statement.");
                }
            }

            return status;
        }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return null;
    }
}
