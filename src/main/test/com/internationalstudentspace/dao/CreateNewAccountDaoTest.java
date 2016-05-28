package main.test.com.internationalstudentspace.dao;

import org.testng.annotations.Test;

import main.java.com.internationalstudentspace.bean.Account;
import main.java.com.internationalstudentspace.bean.Email;
import main.java.com.internationalstudentspace.bean.Telephone;
import main.java.com.internationalstudentspace.bean.UserDetail;
import main.java.com.internationalstudentspace.dao.CreateNewAccountDao;

public class CreateNewAccountDaoTest {

    @Test
    public void testCreateNewAccount(){
        CreateNewAccountDao.createAccount(createTestAccount());
    }

    private static Account createTestAccount() {

        Telephone telephone = new Telephone();
        telephone.setTelephone("12121212121");
        telephone.setIsValid(false);

        Email email = new Email();
        email.setEmailId("test@1121.com");
        email.setIsValid(false);

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName("firstName");
        userDetail.setMiddleName("middleName");
        userDetail.setLastName("lastName");
        userDetail.setAddressLine1("addressLN1");
        userDetail.setAddressLine2("addressLN2");
        userDetail.setZipCode("21020");
        userDetail.setCountry("Country");
        userDetail.setTelephone(telephone);
        userDetail.setEmail(email);
        
        Account account = new Account();
        account.setUserName("testUserName1");
        account.setPassword("testPassword1");
        account.setUserdetail(userDetail);

        return account;
    }
}

