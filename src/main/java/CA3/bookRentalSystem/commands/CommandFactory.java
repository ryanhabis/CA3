package CA3.bookRentalSystem.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommandFactory {

    public static Command setAsCommand(String commandAsString, HttpServletRequest request, HttpServletResponse response) {

        Command command = null;

        if (commandAsString != null) {

            switch (commandAsString) {

                case "view":
                    command = new ViewBooksCommand(request, response);
                    break;

                case "search":
                    command = new SearchCommand(request, response);
                    break;

                case "borrow":
                    command = new BorrowCommand(request, response);
                    break;

                case "return":
                    //command = new ReturnCommand(request, response);
                    break;

                case "login":
                    command = new LoginCommand(request, response);
                    break;

                case "register" :
                    command = new RegisterCommand(request, response);
                    break;

                case "editProfile":
                    command = new EditProfileCommand(request, response);
                    break;
                case "viewUserProfile":
                    command = new ViewUserProfileCommand(request, response);
                    break;
                default:
                    String errorMessage = "No action provided by this controller. ";
                    command = new ErrorCommand(request, response, errorMessage);

            }
        } else {
            String errorMessage = "No action provided. ";
            command = new ErrorCommand(request, response, errorMessage);
        }

        return command;
    }

}
