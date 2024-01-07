/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;



import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserDao extends Dao implements UserDaoInterface ,UserDaoInterfaceAdmin{


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
            PreparedStatement ps = null;
            ResultSet rs = null;

            String status = null;
            try
            {
                conn = getConnection();
                String query = "select userType from users where username = ?";

                ps = conn.prepareStatement(query);
                ps.setString(1,username);
                rs = ps.executeQuery();

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

    /**
     * Retrieves a user from the database based on the provided username and password.
     *
     * @param username The username of the user to be retrieved.
     * @param password The password associated with the specified username.
     * @return A User object containing the user information if found, otherwise an empty User object.
     * @throws RuntimeException If there is an issue closing the database resources.
     * @throws DaoException If a SQL or DaoException occurs during the database operations.
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User userFound = new User();


        try {
            //Getting connection
            conn = getConnection();
            //Creating query
            String query = "select * from users where username = ? and password = ?";
            //Prepare & execute query
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();


            while(rs.next()) {

                //Setting values to correct columns
                userFound.setUsername(rs.getString("username"));
                userFound.setPassword(rs.getString("password"));

            }

        } catch (SQLException | DaoException e) {
            System.out.println("SQL exception: " +e.getMessage());
        }

        //Closing Connection
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Issue when closing result set: ");
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Issue when closing prepared statement: ");
                }
            }
            if(conn != null) {
                try {
                    freeConnection(conn);
                } catch (DaoException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return userFound;
    }

    /**
     * Adds a new user to the database
     *

     * @return the new user if there account was made successfully or not
     * @throws DaoException wil throw a run time exception
     * @throws SQLException if the user was not added
     */
    @Override
    public int signup(String username, String password, String fName, String lName, LocalDate dob, String phoneNumber, String email, String addressLine1, String addressLine2, String city, String county, String eircode) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rowsAdded = -1;

        try {
            //get connection
            conn = getConnection();
            //make query
            String query = "insert into users values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            //prepare statement
            ps = conn.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, fName);
            ps.setString(5, lName);
            ps.setDate(6, Date.valueOf(dob));
            ps.setString(7, phoneNumber);
            ps.setString(8, email);
            ps.setString(9, addressLine1);
            ps.setString(10, addressLine2);
            ps.setString(11, city);
            ps.setString(12, county);
            ps.setString(13, eircode);
            ps.setString(14, "enabled");
            ps.setString(15, "customer");
            rowsAdded = ps.executeUpdate();


        } catch (SQLException e) {
            System.out.println("SQL Exception: "  + e.getMessage());
        }catch (DaoException e) {
            System.out.println("Dao exception: " + e.getMessage());
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

    /**
     * This method goes through all the users that are in the database and displays them
     * @return all the users on the database
     */
    @Override
    public ArrayList<User> getALLUsers()
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //make arraylist
        ArrayList<User> users = new ArrayList<>();

        try {
            conn = getConnection();
            String query = "select * from users";

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User();

                u.setUserId(rs.getInt("userId"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                /* Help form Heidi for the date */
                u.setDob(rs.getDate("dob").toLocalDate());

                u.setPhoneNumber(rs.getString("phoneNumber"));
                u.setEmail(rs.getString("email"));
                u.setAddressLine1(rs.getString("addressLine1"));
                u.setAddressLine2(rs.getString("addressLine2"));
                u.setCity(rs.getString("city"));
                u.setCounty(rs.getString("county"));
                u.setEircode(rs.getString("eircode"));
                // problem
//                u.setUserType(rs.getString("userType"));

                users.add(u);
            }

            /* Taken from Heidi's code */
        }  catch (SQLException e) {
            throw new RuntimeException("SQL runtime exception:- " + e.getMessage());
        }
        catch (DaoException e) {
            throw new RuntimeException("DAO runtime exception:- " + e.getMessage());
        }finally {
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
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Problem occured when closing connection statement.");
                }
            }
        }
        return users;
    }

    /**
     * This method removes a user from the databasse when the username is provided.
     * @param username checks to see if the user is located on the database
     * @return the user that was deleted or not
     */
    @Override
    public int removeUser(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        int userDelete = 0;

        try {
            conn = getConnection();
            String query = "delete from users where username = ?";

            ps = conn.prepareStatement(query);
            ps.setString(1, username);

            userDelete = ps.executeUpdate();

            if (userDelete == 0)
            {
                throw new DaoException("user " + username + " not found");
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error removing user = " + e.getMessage());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                if (ps != null)
                {
                    ps.close();
                }
                if (conn != null)
                {
                    freeConnection(conn);
                }
            }
            catch (SQLException e)
            {
                throw new RuntimeException("Error removing user = " + e.getMessage());
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }

        }

        return userDelete;
    }



    @Override
    public boolean checkAccountEnabled(int userId)
    {
        return true;
    }
}
