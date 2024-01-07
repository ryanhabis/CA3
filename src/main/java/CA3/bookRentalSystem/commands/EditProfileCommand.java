/**
 * @Author Ryan
 */

package CA3.bookRentalSystem.commands;

import CA3.bookRentalSystem.rental.User;
import CA3.bookRentalSystem.repositories.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

public class EditProfileCommand implements Command
{
    private HttpServletRequest request;
    private HttpServletResponse response;
    public EditProfileCommand (HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }

    /**
     * This method executes the logic for the user to be able to update there profile details
     * @return the URL once the method is executed by default it will direct the user to the ../edit.jsp but
     * if there is an issue it will redirct the user the ../error.jsp
     */
    @Override
    public String execute() {

        String continueTo = "../edit.jsp";

        HttpSession session = request.getSession(true);

        try {
            // userId, firstName, username, lastName, password, dob, phoneNumber, email, addressLine1, addressLine2, city, county, userType
            //Retrieves all the users details
            int userId = Integer.parseInt(request.getParameter("userId"));
            String firstName = request.getParameter("firstName");
            String username = request.getParameter("username");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String dob = request.getParameter("dob");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String addressLine1 = request.getParameter("addressLine1");
            String addressLine2 = request.getParameter("addressLine2");
            String city = request.getParameter("city");
            String county = request.getParameter("county");
            String userType = request.getParameter("userType");

            // updates the users information
            UserDao userDao = new UserDao("library");

            User updatedUser = new User();
            updatedUser.setUserId(userId);
            updatedUser.setFirstName(firstName);
            updatedUser.setUsername(username);
            updatedUser.setLastName(lastName);
            updatedUser.setPassword(password);
            updatedUser.setDob(LocalDate.parse(dob));
            updatedUser.setPhoneNumber(phoneNumber);
            updatedUser.setEmail(email);
            updatedUser.setAddressLine1(addressLine1);
            updatedUser.setAddressLine2(addressLine2);
            updatedUser.setCity(city);
            updatedUser.setCounty(county);
            updatedUser.setUserType(userType);

            // calling the editUserProfile method
            int rowUpdated = userDao.editUserProfile(updatedUser);

        }catch (Exception e)
        {
            String error = "Error executing the editUserProfile method";
            session.setAttribute("errorMessage", error);
            continueTo = "../error.jsp";
        }
        return continueTo;
    }
}
