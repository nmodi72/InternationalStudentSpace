package main.java.com.internationalstudentspace.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet for HelloWorld
 */
public class HelloWorld extends HttpServlet {
    /**
     * serial version id
     */
    private static final long serialVersionUID = 1031422249396784970L;
 
    /**
     * Get method for Hello world
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
         
        resp.setContentType("text/html");
         
        PrintWriter out = resp.getWriter();
        out.print("Hello World. !!");
        out.print("<a href='CreateAccount'>Create Account</a>");  
        out.flush();
        out.close();
    }
}
