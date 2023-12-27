/**
 * @Author Ryan
 */
package com.example.CA3.repositories;

import com.example.CA3.exceptions.DaoException;
import com.example.CA3.rental.User;

import java.util.ArrayList;

public interface UserDaoInterfaceForAdmin
{
    // Admin privileges
    public ArrayList<User> getALLUsers();

    public String getUserType(String username);

    public int addUser(String firstName, String lastName, String username , String password , String dob, int  phoneNumber, String email, String addressLine1 , String addressLine2, String city, String county, String eircode) throws DaoException;

    public int removeUser(String username) throws DaoException;
}
