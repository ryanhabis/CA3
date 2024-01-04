package CA3.bookRentalSystem.commands;

import CA3.bookRentalSystem.rental.User;
import CA3.bookRentalSystem.repositories.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public LoginCommand (HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }
    @Override
    public String execute() {

        //create session to execute logincommand
        HttpSession session = request.getSession(true);
        //set the string value to be returned
        String continueTo = "../successfulLogin.jsp";

        //taken from Michelle's solution
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            UserDao userDao = new UserDao("user_database");
            User u = userDao.findUserByUsernamePassword(username, password);
            if (u == null) {
             //   continueTo = "error.jsp";
                String error = "Incorrect credentials supplied. Please <a href=\"login.jsp\">try again.</a>";
                session.setAttribute("errorMessage", error);
            } else {
                continueTo = "loginSuccessful.jsp";
                session.setAttribute("username", username);
                session.setAttribute("user", u);
            }
        } else {
            continueTo = "error.jsp";
            String error = "No username and/or password supplied. Please <a href=\"login.jsp\">try again.</a>";
            session.setAttribute("errorMessage", error);
        }

        return continueTo;
    }
}
