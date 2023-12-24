package com.example.CA3.servlet;

import java.io.*;

import com.example.CA3.commands.Command;
import com.example.CA3.commands.CommandFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
// new project
@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String continueTo = "index.jsp";
        //get the requested action
        String action = request.getParameter("action");
        //turn it into a command
        Command actionCommand = CommandFactory.setAsCommand(action, request, response);
        //execute the command
        continueTo = actionCommand.execute();
        //redirect to the page specified
        response.sendRedirect(continueTo);
    }



}