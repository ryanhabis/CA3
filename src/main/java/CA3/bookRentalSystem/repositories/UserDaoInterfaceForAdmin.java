/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.User;
import java.util.ArrayList;

public interface UserDaoInterfaceForAdmin extends UserDaoInterface
{
    // Admin privileges
    public ArrayList<User> getALLUsers();

    public String getUserType(String username);

    public int addUser(String firstName, String lastName, String username , String password , String dob, int  phoneNumber, String email, String addressLine1 , String addressLine2, String city, String county, String eircode) throws DaoException;

    public int removeUser(String username) throws DaoException;
}
