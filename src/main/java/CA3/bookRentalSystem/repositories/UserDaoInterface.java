/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.rental.User;

public interface UserDaoInterface
{
    public boolean login (String username, String password);

    public String getUserType(String username);
//    public enum getUserType();
    public User findUserByUsernameAndPassword(String username, String password);
}
