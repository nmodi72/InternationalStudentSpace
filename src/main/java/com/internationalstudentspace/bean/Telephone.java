package main.java.com.internationalstudentspace.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Telephone {

    /**
     * Generated id
     */
    private int id;

    /**
     * The telephone number
     */
    private String telephone;

    /**
     * The flag, whether the phone number is validated
     */
    private Boolean isValid;
}