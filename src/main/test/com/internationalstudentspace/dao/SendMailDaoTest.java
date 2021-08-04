package main.test.com.internationalstudentspace.dao;

import java.util.Calendar;

import org.testng.annotations.Test;

import main.java.com.internationalstudentspace.bean.PlanNotificationEmail;
import main.java.com.internationalstudentspace.dao.SendMailDao;

public class SendMailDaoTest {

    /**
     * The email id of the account
     */
    private final static String EMAIL_RECIPIENT = "internationalstudentspace@gmail.com";

    /**
     * The email id of the account
     */
    private final static String EMAIL_BODY = "Hi there, <br/> This is test email. ";

    /**
     * The optional field, attachment path
     */
    private final static String ATTCHMENT = null;

    @Test
    public void testSendMail(){
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        PlanNotificationEmail planNotificationEmail = new PlanNotificationEmail();
        planNotificationEmail.setRecipientName("Saloni-Nirav");
        planNotificationEmail.setUserName("Hiten Gajjar");
        planNotificationEmail.setTestName("GRE");
        planNotificationEmail.setZipCode("21030");
        planNotificationEmail.setSemester("Fall");
        planNotificationEmail.setYear("2018");
        planNotificationEmail.setTimeStamp(currentTimestamp.toString());
        planNotificationEmail.setUserEmail("hiten.gajjar@gmail.com");
        try {
            SendMailDao.sendTestPlanNotificationMail(EMAIL_RECIPIENT, planNotificationEmail, ATTCHMENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSendMail2(){
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        PlanNotificationEmail planNotificationEmail = new PlanNotificationEmail();
        planNotificationEmail.setRecipientName("Saloni-Nirav");
        planNotificationEmail.setUserName("Joey Chapline");
        planNotificationEmail.setTestName("GRE");
        planNotificationEmail.setZipCode("21030");
        planNotificationEmail.setSemester("Fall");
        planNotificationEmail.setYear("2018");
        planNotificationEmail.setTimeStamp(currentTimestamp.toString());
        planNotificationEmail.setUserEmail("hiten.gajjar@gmail.com");
        try {
            SendMailDao.sendTestPlanNotificationMail(EMAIL_RECIPIENT, planNotificationEmail, ATTCHMENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

