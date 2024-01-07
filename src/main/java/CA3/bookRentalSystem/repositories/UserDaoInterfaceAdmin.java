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


    /**
     * View the user profile based on the provided userId.
     *
     * @param userId The userId of the user whose profile is to be viewed.
     * @return A User object containing the user information if found, otherwise an empty User object.
     * @throws RuntimeException If there is an issue closing the database resources.
     * @throws DaoException If a SQL or DaoException occurs during the database operations.
     */
    public User viewUserProfile(int userId);

    /**
     * Edit the user profile based on the provided User object.
     *
     * @param updatedUser The User object containing the updated user information.
     * @return The number of rows affected in the database.
     * @throws RuntimeException If there is an error updating the user profile or closing resources.
     * @throws DaoException If a SQL or DaoException occurs during the database operations.
     */
    public int editUserProfile(User updatedUser);
}
