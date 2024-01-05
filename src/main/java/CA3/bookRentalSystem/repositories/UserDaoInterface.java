/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.rental.User;

import java.time.LocalDate;

public interface UserDaoInterface
{
    public boolean login (String username, String password);

    public String getUserType(String username);
//    public enum getUserType();
    public User findUserByUsernameAndPassword(String username, String password);
    public int signup(String username, String password, String fName, String lName, LocalDate dob, String phoneNumber, String email, String addressLine1, String addressLine2, String city, String county, String eircode);
}
