package com.example.CA3.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public LoginCommand (HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
    }
    @Override
    public String execute() {

        //create session to execute logincommand
        HttpSession session = request.getSession();
        //set the string value to be returned
        String continueTo = "login.jsp";

        return continueTo;
    }
}
