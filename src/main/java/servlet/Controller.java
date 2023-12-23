package servlet;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
// new project
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Controller extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

}