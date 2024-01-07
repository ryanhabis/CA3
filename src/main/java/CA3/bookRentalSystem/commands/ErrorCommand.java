/**
 * Command class for error handling
 * Executed when error occurs, brings user to error page
 * @Author Heidi
 */
package CA3.bookRentalSystem.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ErrorCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String errorMessage;

    /**
     * Constructor for error command
     * @param request providing request to ServLet
     * @param response providing response to the request
     * @param errorMessage error message displayed on error page
     */
    public ErrorCommand(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        this.request = request;
        this.response = response;
        this.errorMessage = errorMessage;
    }

    /**
     * Executes error handling command
     * @return String redirecting to error page
     */
    @Override
    public String execute() {
        //set the string value to be returned
        String continueTo = "../error.jsp";

        return continueTo;
    }
}
