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
    public ViewUserProfileCommand(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }

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
