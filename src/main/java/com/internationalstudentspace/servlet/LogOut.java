package main.java.com.internationalstudentspace.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet {

    /**
     * the serial version id
     */
	private static final long serialVersionUID = 1L;

    /**
     * the constructor
     */
    public LogOut() {
        super();
    }

	/**
	 * Get implementation for Log Out
	 * 
	 * @param request the Http servlet request
	 * @param response the Http servlet response
	 * @throws Exception in case of any exception
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session=request.getSession();  
	    session.invalidate();
	    response.sendRedirect("HomePage");
	}
}
