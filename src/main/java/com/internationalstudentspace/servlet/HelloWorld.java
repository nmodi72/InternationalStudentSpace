package main.java.com.internationalstudentspace.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
public class HelloWorld extends HttpServlet {
     
    private static final long serialVersionUID = 1031422249396784970L;
 
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
         
        resp.setContentType("text/html");
         
        PrintWriter out = resp.getWriter();
        out.print("Hello World. !!");
        out.flush();
        out.close();
    }
}
