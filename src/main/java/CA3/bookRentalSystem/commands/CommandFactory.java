package CA3.bookRentalSystem.commands;
/**
 * Creates command objects based on given commands
 * Class returns a specific command that corresponds to different actions
 * @author: Heidi
 * @author: Evan
 * @author: Ryan
 * Reference: Michelle's notes
 **/

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommandFactory {

    /**
     * Creates and returns command object based on the given commands
     * Acts as a factory, connects jsps and Commands
     * @param commandAsString String version of the command
     * @param request Provides the ServLet Request
     * @param response Provides the ServerLet Response
     * @return Command The command corresponding with the action, Returns error with errorMessage if unsuccessful
     */
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
                    command = new ReturnCommand(request, response);
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
