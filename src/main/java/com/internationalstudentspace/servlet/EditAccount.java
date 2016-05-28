package main.java.com.internationalstudentspace.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.internationalstudentspace.bean.Accounts;
import main.java.com.internationalstudentspace.dao.AccountDao;

/**
 * Servlet implementation class EditAccount
 */
@WebServlet("/EditAccount")
public class EditAccount extends HttpServlet {
    /**
     * the serial version id
     */
	private static final long serialVersionUID = 1L;
       
    /**
     * the constructor
     */
    public EditAccount() {
        super();
    }

	/**
	 * Get implementation to update account
	 * 
	 * @param request the Http servlet request
	 * @param response the Http servlet response
	 * @throws Exception in case of any exception
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        out.println("<h1>Edit Account</h1>");  
        String updateRequestId = request.getParameter("id");  
        int updateId = Integer.parseInt(updateRequestId);  
          
        Accounts accounts = AccountDao.getAccountById(updateId);
          
        out.print("<form action='EditAccount' method='post'>");  
        out.print("<table>");  
        out.print("<tr><td></td><td><input type='hidden' name='id' value='"+accounts.getId()+"'/></td></tr>");  
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+accounts.getUserName()+"'/></td></tr>");  
        out.print("<tr><td>Password:</td><td><input type='password' name='password' value='"+accounts.getPassword()+"'/></td></tr>");
        out.print("<tr><td>Phone Number:</td><td><input type='text' name='phoneNumber' value='"+accounts.getPhoneNumber()+"'/></td></tr>");
        out.print("<tr><td>Email:</td><td><input type='email' name='email' value='"+accounts.getEmail()+"'/></td></tr>");  
        out.print("<tr><td>Country:</td><td>");  
        out.print("<select name='country' style='width:150px'>");  
        out.print("<option>India</option>");  
        out.print("<option>Other</option>");  
        out.print("</select>");  
        out.print("</td></tr>");  
        out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
        out.print("</table>");  
        out.print("</form>");  
        out.close();  
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
        PrintWriter out=response.getWriter();  
          
        String requestId = request.getParameter("id");  
        int id = Integer.parseInt(requestId);  
        String userName = request.getParameter("userName");  
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String country = request.getParameter("country");  
          
        Accounts accounts = new Accounts();  
        accounts.setId(id);  
        accounts.setUserName(userName);  
        accounts.setPassword(password);
        accounts.setPhoneNumber(phoneNumber);
        accounts.setEmail(email);  
        accounts.setCountry(country);  
          
        int status = AccountDao.update(accounts);  
        if(status>0){  
            response.sendRedirect("ViewServlet");  
        }else{  
            out.println("Sorry! unable to update record");  
        }  
        out.close();  
    }  
}
