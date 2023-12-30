package CA3.bookRentalSystem.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommandFactory {

    public static Command setAsCommand(String commandAsString, HttpServletRequest request, HttpServletResponse response) {

        Command command = null;

        if (commandAsString != null) {

            switch (commandAsString) {

                case "login":
                    command = new LoginCommand(request, response);
                    break;

                case "browseBooks":
                 //   command = new BrowseBooksCommand(request, response);
                break;

            }
        }

        return command;
    }

}
