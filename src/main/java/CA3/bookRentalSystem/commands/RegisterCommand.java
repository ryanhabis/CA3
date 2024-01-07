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

import java.time.LocalDate;

public class RegisterCommand implements Command{

    private HttpServletRequest request;
    private HttpServletResponse response;
    public RegisterCommand (HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }

    @Override
    public String execute() {
        //set the string value to be returned
        String continueTo = "../login.jsp";
        //create session to execute registercommand
        HttpSession session = request.getSession();

        //get current user details from current session parameters
        int userId = Integer.parseInt(request.getParameter("userId"));
        String currentUsername = request.getParameter("username");
        String currentPassword = request.getParameter("password");
        String userFirstName = request.getParameter("fName");
        String userLastName = request.getParameter("lName");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
       // int phoneNum = Integer.parseInt(request.getParameter("phone"));
        String phoneNum = request.getParameter("phoneNum");
        String email = request.getParameter("email");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String city = request.getParameter("city");
        String county = request.getParameter("county");
        String eircode = request.getParameter("eircode");
//        String accountStatusString = request.getParameter("accountStatus");
//        String userTypeString = request.getParameter("userType");

//        User.AccountStatus accountStatus = User.AccountStatus.valueOf(accountStatusString);
//        User.UserType userType = User.UserType.valueOf(userTypeString);
        User.AccountStatus accountStatus = User.AccountStatus.enabled;
       // User.UserType userType = User.UserType.CUSTOMER;
String userType = "Customer";

        if (currentUsername != null && currentPassword != null && !currentUsername.isEmpty() && !currentPassword.isEmpty() && userFirstName != null && !userFirstName.isEmpty() && userLastName != null && !userLastName.isEmpty()) {
            UserDaoInterfaceAdmin userDao = new UserDao("bookrentalsystem");

            int id = userDao.signup(currentUsername, currentPassword, userFirstName, userLastName,  dob, phoneNum, email, addressLine1, addressLine2, city, county, eircode);
            if (id == -1) {
                continueTo = " "; //set our destination to an empty string
                String error = "This user could not be added. Please <a href=\"register.jsp\">try again.</a>";
                session.setAttribute("errorMessage", error); //set error message
            }
            else {
                continueTo = "../login.jsp"; //redirect to login page
                session.setAttribute("username", currentUsername); //set username in session
                User newUser = new User(userFirstName, userLastName, currentUsername, currentPassword, dob, phoneNum, email, addressLine1, addressLine2, city, county, eircode, accountStatus, userType);
                session.setAttribute("user", newUser);
                String regSuccess = "Congratulations! You have successfully registered!";
                session.setAttribute("msg", regSuccess);
            }
        } else {
            continueTo = "../error.jsp";
            String error = "Some information was not supplied. Please <a href=\"register.jsp\">try again.</a>";
            session.setAttribute("errorMessage", error);
        }

        return continueTo;
    }
}