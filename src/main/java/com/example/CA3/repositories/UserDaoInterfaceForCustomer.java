/**
 * @Author Ryan
 */
package com.example.CA3.repositories;
import exceptions.DaoException;
import library.User;

public interface UserDaoInterfaceForCustomer
{
    public boolean logIn(String username, String password);

    public String getUserType(String username);


//    public enum getUserType();
}
