/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.repositories;

public interface UserDaoInterface
{
    public boolean logIn(String username, String password);

    public String getUserType(String username);


//    public enum getUserType();
}
