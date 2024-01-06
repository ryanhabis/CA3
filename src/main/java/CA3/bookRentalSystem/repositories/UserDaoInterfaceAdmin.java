/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.User;

import java.time.LocalDate;
import java.util.ArrayList;

public interface UserDaoInterfaceAdmin extends UserDaoInterface
{
    // Admin privileges
    public ArrayList<User> getALLUsers();

    public String getUserType(String username);

    public boolean checkAccountEnabled(String username);
    public int removeUser(String username) throws DaoException;

}
