/**
 * @Author Ryan
 */
package com.example.CA3.repositories;
import com.example.CA3.exceptions.DaoException;
import com.example.CA3.rental.User;

public interface UserDaoInterfaceForCustomer
{
    public boolean logIn(String username, String password);

    public String getUserType(String username);


//    public enum getUserType();
}
