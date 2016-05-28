package main.java.com.internationalstudentspace.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.internationalstudentspace.bean.Account;
import main.java.com.internationalstudentspace.bean.Accounts;
import main.java.com.internationalstudentspace.bean.Email;
import main.java.com.internationalstudentspace.bean.Telephone;
import main.java.com.internationalstudentspace.bean.UserDetail;
import main.java.com.internationalstudentspace.dao.AccountDao;
import main.java.com.internationalstudentspace.dao.CreateNewAccountDao;

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
        String userName = request.getParameter("userName");  
        String password = request.getParameter("password");
        
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String zipCode = request.getParameter("zipCode");
        String country = request.getParameter("country");
        String telephoneNumber = request.getParameter("telephone");
        String emailId = request.getParameter("email");  

        Telephone telephone = new Telephone();
        telephone.setTelephone(telephoneNumber);
        telephone.setIsValid(Boolean.FALSE);

        Email email = new Email();
        email.setEmailId(emailId);
        email.setIsValid(Boolean.FALSE);

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(firstName);
        userDetail.setMiddleName(middleName);
        userDetail.setLastName(lastName);
        userDetail.setAddressLine1(addressLine1);
        userDetail.setAddressLine2(addressLine2);
        userDetail.setZipCode(zipCode);
        userDetail.setCountry(country);
        userDetail.setTelephone(telephone);
        userDetail.setEmail(email);

        Account account = new Account();
        account.setUserName(userName);
        account.setPassword(password);
        account.setUserdetail(userDetail);

        int status = CreateNewAccountDao.createAccount(account); 
        
        if (status > 0) {  
            response.sendRedirect("HomePage");  
        } else {  
            response.sendRedirect("CreateAccount");  
        }  
    }  
}
