package main.java.com.internationalstudentspace.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import main.java.com.internationalstudentspace.bean.PlanNotificationEmail;
import main.java.com.internationalstudentspace.utils.Constants;

public class SendMailDao {
    /**
     * The smtp property file path
     */
    private final static String SMTP_PROPERTIES_FILE_PATH = "smtpSettings.properties";
    /**
     * The ftl file name
     */
    private final static String FTL_FILE_NAME = "emailFormat.ftl";

    /**
     * The email id of the account
     */
    private final static String EMAIL_ID = "internationalstudentspace@gmail.com";

    /**
     * The password
     */
    private final static String PASSWORD = "Saloni^1908";

    /**
     * helper method to send the email for test planning.
     * 
     * @param recipientEmailId the recipient email id
     * @param planNotificationEmail the {@link PlanNotificationEmail} object
     * @param attachment the attachment path
     * @throws Exception, In case of exception.
     */
    public static void sendTestPlanNotificationMail(String recipientEmailId, PlanNotificationEmail planNotificationEmail, String attachment, ServletContext context) throws Exception {
        String mailSubject = "Help Request from : ".concat(planNotificationEmail.getUserName());
        Map<String, String> mailContents = new HashMap<String, String>();
        mailContents.put(Constants.RECIPIENT_NAME, planNotificationEmail.getRecipientName());
        mailContents.put(Constants.USER_NAME, planNotificationEmail.getUserName());
        mailContents.put(Constants.TEST_NAME, planNotificationEmail.getTestName());
        mailContents.put(Constants.ZIP_CODE, planNotificationEmail.getZipCode());
        mailContents.put(Constants.SEMESTER, planNotificationEmail.getSemester());
        mailContents.put(Constants.YEAR, planNotificationEmail.getYear());
        mailContents.put(Constants.CURRENT_TIMESTAMP, planNotificationEmail.getTimeStamp());
        mailContents.put(Constants.USER_EMAIL, planNotificationEmail.getUserEmail());

        sendEmail(FTL_FILE_NAME, recipientEmailId, mailSubject, mailContents, attachment, context);
    }

    /**
     * helper method to send the email
     * 
     * @throws Exception, In case of exception.
     */
    private static void sendEmail(String templatePath, String recipientEmailId, String mailSubject, Map<String, String> mailContents, String attachment, ServletContext context) throws Exception {
        Session session = getSmtpSession(context);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_ID));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmailId));
            message.setSubject(mailSubject);
            Configuration configuration = new Configuration();
//            FileTemplateLoader templateLoader = new FileTemplateLoader(new File("WEB-INF/"));
            configuration.setTemplateLoader(new WebappTemplateLoader(context, "WEB-INF/"));
            Template template = configuration.getTemplate(FTL_FILE_NAME);
            Writer out = new StringWriter();
            template.process(mailContents, out);
            BodyPart body = new MimeBodyPart();
            body.setContent(out.toString(), "text/html");

            addMailMultiParts(attachment, message, body);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * helper method to add multiple parts into one mail.
     * 
     * @param attachment The attachment path if exists.
     * @param message The message
     * @param body The mail body part
     * @throws Exception, In case of exception.
     */
    private static void addMailMultiParts(String attachment, Message message, BodyPart body) throws MessagingException {
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(body);
        if (null != attachment) {
            body = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            body.setDataHandler(new DataHandler(source));
            body.setFileName(attachment);
            multipart.addBodyPart(body);
        }

        message.setContent(multipart);
    }

    /**
     * helper method to get the smtp session
     * 
     * @throws Exception, In case of exception.
     */
    private static Session getSmtpSession(ServletContext context) {
        Properties props = new Properties();
        try {
            String propertiesFilePath = context.getRealPath(SMTP_PROPERTIES_FILE_PATH);
            props.load(new FileInputStream(new File(propertiesFilePath)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_ID, PASSWORD);
            }
        });
    }
}