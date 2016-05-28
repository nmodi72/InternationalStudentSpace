package main.java.com.internationalstudentspace.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.internationalstudentspace.dao.ValidateAccountDao;


/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
    /**
     * the serial version id
     */
	private static final long serialVersionUID = 1L;

    /**
     * the constructor
     */
    public LogIn() {
        super();
    }

	/**
	 * Get implementation for create account
	 * 
	 * @param request the Http servlet request
	 * @param response the Http servlet response
	 * @throws Exception in case of any exception
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.getRequestDispatcher("/WEB-INF/jsp/LogIn.html").forward(request, response);
	}

	/**
     * Post implementation for create account
     *
     * @param request the Http servlet request
     * @param response the Http servlet response
     * @throws Exception in case of any exception
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        String userName = request.getParameter("userName");  
        String password = request.getParameter("password");
        
        if (ValidateAccountDao.validateCredential(userName, password)) {
            request.getSession().setAttribute("userName", userName);
            response.sendRedirect("DashBoard");  
        } else {
            doGet(request, response);
        }
    }  
}
