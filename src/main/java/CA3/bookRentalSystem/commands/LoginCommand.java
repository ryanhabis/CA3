/**
 * @Author Heidi
 */
package CA3.bookRentalSystem.commands;

import CA3.bookRentalSystem.rental.User;
import CA3.bookRentalSystem.repositories.UserDao;
import CA3.bookRentalSystem.repositories.UserDaoInterfaceAdmin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * Constructor for LoginCommand
     * @param request ServLet Request
     * @param response ServerLet Response
     */
    public LoginCommand (HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }

    /**
     * validates the username and password, if they are valid redirect them to the ../successfulLogin.jsp other wise send them to the ../error.jsp
     *
     * @return The URL redirect to either the ../successfulLogin.jsp or the ../error.jsp
     */
    @Override
    public String execute() {

         //set the string value to be returned
        String continueTo = "../successfulLogin.jsp";
        //create session to execute logincommand
        HttpSession session = request.getSession(true);

        //taken from Michelle's solution
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //if fields entered are not empty and are not null
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            //make a new userdao using the regular database
            UserDaoInterfaceAdmin userDao = new UserDao("bookrentalsystem");
            //retrieve User matching details entered
            User userFound = userDao.findUserByUsernameAndPassword(username, password);
           // User.UserType userType = User.UserType.valueOf(userDao.getUserType(username));
            String userType = userDao.getUserType(username);
            if (userFound == null || userType.equals(null)) { //if no matching user is found
                continueTo = "../error.jsp"; //go to error page
                String error = "Incorrect credentials supplied. Please <a href=\"../login.jsp\">try again.</a>";
                session.setAttribute("errorMessage", error);
            } else {
                continueTo = "../successfulLogin.jsp"; //otherwise, continue to the loginSuccessful page
                session.setAttribute("username", username); //set the username variable to the current session
                session.setAttribute("userType", userType); //set the userType variable to the current session

                session.setAttribute("user", userFound); //set the user object to the current session
            }
        } else {
            continueTo = "../error.jsp"; //go to error page
            String error = "No username and/or password supplied. Please <a href=\"../login.jsp\">try again.</a>";
            session.setAttribute("errorMessage", error);
        }

        return continueTo;
    }
}
