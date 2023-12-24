package com.example.CA3.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommandFactory {

    public static Command setCommand (String commandAsString) {

        Command command = null;

        switch (commandAsString) {

            case "login" :

                break;


        }


        return command;
    }

}
