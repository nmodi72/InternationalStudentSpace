package main.java.com.internationalstudentspace.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {

    /**
     * Generated id
     */
    private int id;

    /**
     * The email id
     */
    private String emailId;

    /**
     * The flag, whether the email is validated
     */
    private Boolean isValid;
}