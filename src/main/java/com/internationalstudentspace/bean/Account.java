package main.java.com.internationalstudentspace.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

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
     * The user detail
     */
    private UserDetail userdetail;
}