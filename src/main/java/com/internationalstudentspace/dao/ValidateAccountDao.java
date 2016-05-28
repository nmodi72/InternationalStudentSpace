package main.java.com.internationalstudentspace.dao;
import java.util.List;

import main.java.com.internationalstudentspace.bean.Account;  

public class ValidateAccountDao {
    /**
     * helper method to validate credential
     * 
     * @param userName the user name
     * @param password the password
     * @return true in case if the credential is valid, false otherwise.
     */
    public static boolean validateCredential(String userName, String password){  
        List<Account> accountList = GetAccountDao.getAllAccounts();
        for (Account account : accountList) {
            if (account.getUserName().equals(userName) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}  