/**
 * @Author Ryan
 */
package CA3.bookRentalSystem.commands;

import CA3.bookRentalSystem.rental.User;
import CA3.bookRentalSystem.repositories.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewUserProfileCommand implements Command
{
    private HttpServletRequest request;
    private HttpServletResponse response;
    /**
     * Constructor for ViewUserProfileCommand
     * @param request ServLet Request
     * @param response ServerLet Response
     */
    public ViewUserProfileCommand(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }

    /**
     * This method executes the logic for the user to be able to view there profile details
     * @return the URL once the method is executed by default it will direct the user to the ../viewUserProfile.jsp but
     * if there is an issue it will redirct the user the the ../error.jsp
     */
    @Override
    public String execute() {
        String continueTo = "../viewUserProfile.jsp";

        HttpSession session = request.getSession(true);
        try{
            int userId = (int) session.getAttribute("userId");

            UserDao userDao = new UserDao("library");
            User user = userDao.viewUserProfile(userId);

            session.setAttribute("userProfile", user);
        }catch (Exception e)
        {
            String error = "Error with viewUserProfile method";
            session.setAttribute("errorMessage", error);
            continueTo = "../error.jsp";
        }
        return continueTo;
    }
}
