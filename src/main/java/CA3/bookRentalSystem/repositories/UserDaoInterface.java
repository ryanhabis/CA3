/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.User;

import java.sql.SQLException;
import java.time.LocalDate;

public interface UserDaoInterface
{
    /**
     * This method logs the user into his account if it's on the database.
     * @param username checks if the username is in the database.
     * @param password checks if the password is in the database.
     * @return a boolean that checks if the user is registered or not.
     *
     * I used this code for reference.
     * @referance https://stackoverflow.com/questions/35083680/check-if-record-exists-in-database-with-resultset
     */
    public boolean login (String username, String password);

    /**
     * This method checks if the user is either an admin or a customer.
     * @param username checks the username of the user
     * @return the type of user the person is
     * @throws DaoException
     */
    public String getUserType(String username);
//    public enum getUserType();

    /**
     * Retrieves a user from the database based on the provided username and password.
     *
     * @param username The username of the user to be retrieved.
     * @param password The password associated with the specified username.
     * @return A User object containing the user information if found, otherwise an empty User object.
     * @throws RuntimeException If there is an issue closing the database resources.
     * @throws DaoException If a SQL or DaoException occurs during the database operations.
     */
    public User findUserByUsernameAndPassword(String username, String password);

    /**
     * Adds a new user to the database
     *
     * @return the new user if there account was made successfully or not
     * @throws DaoException wil throw a run time exception
     * @throws SQLException if the user was not added
     */
    public int signup(String username, String password, String fName, String lName, LocalDate dob, String phoneNumber, String email, String addressLine1, String addressLine2, String city, String county, String eircode);


    /**
     * View the user profile based on the provided username.
     *
     * @param userId The username of the user whose profile is to be viewed.
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
