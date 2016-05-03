package main.java.com.internationalstudentspace.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Accounts {
    /**
     * Generated id
     */
    private int id;

    /**
     * The user name
     */
    private String userName;

    /**
     * The password
     */
    private String password;

    /**
     * The phone number
     */
    private String phoneNumber;

    /**
     * The email
     */
    private String email;

    /**
     * The country
     */
    private String country;
}