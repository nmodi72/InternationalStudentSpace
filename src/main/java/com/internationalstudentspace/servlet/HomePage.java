package main.java.com.internationalstudentspace.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet for HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
    /**
     * serial version id
     */
    private static final long serialVersionUID = 1031422249396784970L;
 
    /**
     * Get implementation to Home Page Servlet
     * 
     * @param request the Http servlet request
     * @param response the Http servlet response
     * @throws Exception in case of any exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/HomePage.html").forward(request, response);
    }
}
