package main.java.com.internationalstudentspace.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanNotificationEmail {
    /**
     * The recipient name
     */
    private String recipientName;

    /**
     * The user name
     */
    private String userName;

    /**
     * The user email
     */
    private String userEmail;

    /**
     * The test name
     */
    private String testName;

    /**
     * The zip code
     */
    private String zipCode;

    /**
     * The semester
     */
    private String semester;

    /**
     * The year
     */
    private String year;

    /**
     * The time stamp
     */
    private String timeStamp;
}