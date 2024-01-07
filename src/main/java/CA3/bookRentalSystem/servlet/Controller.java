package CA3.bookRentalSystem.servlet;
/**
 * author: Heidi
 */

import CA3.bookRentalSystem.commands.Command;
import CA3.bookRentalSystem.commands.CommandFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// new project
@WebServlet(name = "Controller", value = "/servlet/Controller")
public class Controller extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the Post request
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * This method will process the incoming request, and it will redirect them to the action
     *
     * @param request This is the HttpServletRequest object
     * @param response This is the HttpServletResponse Object
     * @throws ServletException If an unknown error happens
     * @throws IOException If the input or output error happens it will handle the request
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String continueTo = "index.jsp";
        //get the requested action
        String action = request.getParameter("action");
        if (action != null) {
            //turn it into a command
            Command actionCommand = CommandFactory.setAsCommand(action, request, response);
            //execute the command
            continueTo = actionCommand.execute();
            //redirect to the page specified
            response.sendRedirect(continueTo);
        }
    }



}