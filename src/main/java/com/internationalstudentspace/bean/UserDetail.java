package main.java.com.internationalstudentspace.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetail {

    /**
     * Generated id
     */
    private int id;

    /**
     * The first name
     */
    private String firstName;

    /**
     * The middle name
     */
    private String middleName;

    /**
     * The last name
     */
    private String lastName;

    /**
     * The address line 1
     */
    private String addressLine1;

    /**
     * The address line 2
     */
    private String addressLine2;

    /**
     * The zip code
     */
    private String zipCode;

    /**
     * The country
     */
    private String country;

    /**
     * The phone number
     */
    private Telephone telephone;

    /**
     * The email
     */
    private Email email;

}