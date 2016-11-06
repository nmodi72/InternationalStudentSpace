package main.java.com.internationalstudentspace.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.internationalstudentspace.bean.Accounts;
import main.java.com.internationalstudentspace.bean.PlanNotificationEmail;
import main.java.com.internationalstudentspace.dao.AccountDao;
import main.java.com.internationalstudentspace.dao.SendMailDao;

/**
 * Servlet implementation class PlanTest
 */
@WebServlet("/PlanTest")
public class PlanTest extends HttpServlet {
    /**
     * the serial version id
     */
	private static final long serialVersionUID = 1L;

    /**
     * the constructor
     */
    public PlanTest() {
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
	    request.getRequestDispatcher("/WEB-INF/jsp/DashBoard.html").forward(request, response);
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
        String testName = request.getParameter("testName");
        String semester = request.getParameter("semester");
        String year = request.getParameter("year");
        String country = request.getParameter("country");  
        String postalCode = request.getParameter("postalCode");
        
        PlanNotificationEmail planNotificationEmail = new PlanNotificationEmail();
        planNotificationEmail.setRecipientName("Saloni-Nirav");
        planNotificationEmail.setUserName("Hiten Gajjar");
        planNotificationEmail.setTestName(testName);
        planNotificationEmail.setZipCode(postalCode);
        planNotificationEmail.setSemester(semester);
        planNotificationEmail.setYear(year);
        planNotificationEmail.setTimeStamp("Just Now");
        planNotificationEmail.setUserEmail("hiten.gajjar@gmail.com");
        ServletContext context = this.getServletContext();
        
        try {
            SendMailDao.sendTestPlanNotificationMail("internationalstudentspace@gmail.com", planNotificationEmail, null, context);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        switch (testName) {
        case "GRE":
            
            System.out.println(testName + " "+  semester+ " "+  year+ " "+  country+ " "+  postalCode);
            break;
        case "TOEFL":
            System.out.println(testName + " "+  semester+ " "+  year+ " "+  country+ " "+  postalCode);
            break;
        case "IELTS":
            System.out.println(testName + " "+  semester+ " "+  year+ " "+  country+ " "+  postalCode);
            break;
        default:
            break;
        }
//        Accounts accounts = new Accounts();  
//        accounts.setUserName(userName);
//        accounts.setPassword(password); 
//        accounts.setPhoneNumber(phoneNumber);
//        accounts.setEmail(email);  
//        accounts.setCountry(country);  

//        int status = AccountDao.save(accounts); 
//        
//        if(status>0){
//            response.sendRedirect("/CreateAccount");
//            request.getRequestDispatcher("/WEB-INF/jsp/DashBoard.html").forward(request, response); 
//        }else{  
//            response.sendRedirect("/CreateAccount");  
//        }  
    }  
}
