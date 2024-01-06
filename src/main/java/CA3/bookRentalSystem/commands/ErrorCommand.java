package CA3.bookRentalSystem.commands;

public class ErrorCommand implements Command {
    @Override
    public String execute() {
        //set the string value to be returned
        String continueTo = "../error.jsp";

        return continueTo;
    }
}
