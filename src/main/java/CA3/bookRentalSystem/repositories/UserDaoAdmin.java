/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;


import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.Book;
import CA3.bookRentalSystem.rental.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserDaoAdmin extends Dao implements UserDaoInterfaceAdmin
{

    public UserDaoAdmin(Connection conn) {
        super(conn);
    }

    public UserDaoAdmin(String databaseName) {
        super(databaseName);
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
        } catch (DaoException e) {
            throw new RuntimeException("DAO runtime exception:- " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("SQL runtime exception:- " + e.getMessage());
        } finally {
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
    public boolean login(String username, String password) {
        return false;
    }

    /**
     * This method will check if the user is either an admin or a customer.
     * @param username of what tyoe of user they are
     * @return the user type or null if the user was not found.
     * @throws RuntimeException
     */
    @Override
    public String getUserType(String username){
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


    @Override
    public boolean checkAccountEnabled(int userId) {
        return true;
    }

}