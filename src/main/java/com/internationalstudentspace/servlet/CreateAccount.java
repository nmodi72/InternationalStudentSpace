package main.java.com.internationalstudentspace.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.internationalstudentspace.bean.Accounts;
import main.java.com.internationalstudentspace.dao.AccountDao;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
    /**
     * the serial version id
     */
	private static final long serialVersionUID = 1L;

    /**
     * the constructor
     */
    public CreateAccount() {
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
	    request.getRequestDispatcher("/WEB-INF/jsp/CreateAccount.html").forward(request, response);
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
//		PrintWriter out = response.getWriter();
          
        String userName = request.getParameter("userName");  
        String password = request.getParameter("password");
        String phoneNumber =request.getParameter("phoneNumber");
        String email = request.getParameter("email");  
        String country = request.getParameter("country");  

        Accounts accounts = new Accounts();  
        accounts.setUserName(userName);
        accounts.setPassword(password); 
        accounts.setPhoneNumber(phoneNumber);
        accounts.setEmail(email);  
        accounts.setCountry(country);  

        int status=AccountDao.save(accounts); 
        
        
        if(status>0){  
            request.getRequestDispatcher("/WEB-INF/jsp/DashBoard.html").forward(request, response); 
        }else{  
            response.sendRedirect("/CreateAccount");  
        }  
    }  
}
