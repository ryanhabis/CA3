/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface UserDaoInterfaceAdmin extends UserDaoInterface
{
    // Admin privileges

    /**
     * This method goes through all the users that are in the database and displays them
     * @throws DaoException wil throw a run time exception
     * @throws SQLException
     * @return all the users on the database
     */
    public ArrayList<User> getALLUsers();


    /**
     * This method checks if the user is either an admin or a customer.
     * @param username checks the username of the user
     * @return the type of user the person is
     * @throws DaoException
     */
    public String getUserType(String username);

    public boolean checkAccountEnabled(int userId);
    /**
     * This method removes a user from the databasse when the username is provided.
     * @param username checks to see if the user is located on the database
     * @return the user that was deleted or not
     */
    public int removeUser(String username) throws DaoException;

}
