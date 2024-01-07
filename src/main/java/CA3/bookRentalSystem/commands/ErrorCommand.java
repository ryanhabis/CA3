package CA3.bookRentalSystem.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ErrorCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String errorMessage;

    public ErrorCommand(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        this.request = request;
        this.response = response;
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute() {
        //set the string value to be returned
        String continueTo = "../error.jsp";

        return continueTo;
    }
}
