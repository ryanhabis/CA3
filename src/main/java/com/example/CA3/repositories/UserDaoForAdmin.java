/**
 * @Author Ryan
 */
package com.example.CA3.repositories;

import exceptions.DaoException;
import rental.User;


import java.sql.*;
import java.util.ArrayList;

public class UserDaoForAdmin extends Dao implements UserDaoInterfaceForAdmin
{

    public UserDaoForAdmin(Connection conn) {
        super(conn);
    }

    public UserDaoForAdmin(String databaseName) {
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
                u.setUsersName(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                /* Help form Heidi for the date */
                u.setDob(rs.getDate("dob").toLocalDate());

                u.setPhoneNumber(rs.getInt("phoneNumber"));
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
     * @throws DaoException
     */
    @Override
    public int removeUser(String username) throws DaoException {
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
        }
        finally
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
            }

        }

        return userDelete;
    }


    /**
     * Adds a new user to the database
     *
     * @param all of the new user data must be passed through in order to add a new user
     * @return the new user if there account was made successfully or not
     * @throws DaoException wil throw a run time exception
     * @throws SQLException if the user was not added
     */
    @Override
    public int addUser(String firstName, String lastName, String username , String password , String dob, int  phoneNumber, String email, String addressLine1 , String addressLine2, String city, String county, String eircode) throws DaoException
    {
      Connection conn = null;
        PreparedStatement ps = null;
        int userAdded = 0;


        try {
            conn = getConnection();
            String query = "insert into users (firstName ,lastName ,username ,password ,dob ,phoneNumber ,email ,addressLine1 ,addressLine2 ,city ,county ,eircode, userType) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            //default
            String userType = "customer";

            ps = conn.prepareStatement(query);
            ps.setString(1,firstName);
            ps.setString(2,lastName);
            ps.setString(3,username);
            String hashedPassword = String.valueOf(Math.abs(password.hashCode()%password.length())); //reference: Michelle's notes from 2nd year
            ps.setString(4,hashedPassword);
            ps.setString(5,dob);
            ps.setInt(6,phoneNumber);
            ps.setString(7,email);
            ps.setString(8,addressLine1);
            ps.setString(9,addressLine2);
            ps.setString(10,city);
            ps.setString(11,county);
            ps.setString(12,eircode);
            ps.setString(13,userType);

            userAdded = ps.executeUpdate();

            if (userAdded == 0)
            {
                throw new DaoException("New username was not added");

            }

        }
        catch (SQLException e)
        {
            throw new DaoException("user was not added to the database = " + e.getMessage());
        }
        finally
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
            catch (SQLException | DaoException e)
            {
                throw new RuntimeException(e.getMessage());
            }
        }
        return userAdded;
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
}