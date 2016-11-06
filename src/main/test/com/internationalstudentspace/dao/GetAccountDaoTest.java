package main.test.com.internationalstudentspace.dao;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import main.java.com.internationalstudentspace.bean.Account;
import main.java.com.internationalstudentspace.dao.GetAccountDao;

public class GetAccountDaoTest {

    @Test
    public void testGetAccount() {
        Account account = GetAccountDao.getAccount("testUserName");

        Assert.assertEquals(account.getUserName(), "testUserName");
        Assert.assertEquals(account.getPassword(), "testPassword");
        Assert.assertEquals(account.getUserdetail().getFirstName(), "firstName");
        Assert.assertEquals(account.getUserdetail().getMiddleName(), "middleName");
        Assert.assertEquals(account.getUserdetail().getLastName(), "lastName");
        Assert.assertEquals(account.getUserdetail().getAddressLine1(), "addressLN1");
        Assert.assertEquals(account.getUserdetail().getAddressLine2(), "addressLN2");
        Assert.assertEquals(account.getUserdetail().getZipCode(), "21020");
        Assert.assertEquals(account.getUserdetail().getCountry(), "Country");
        Assert.assertEquals(account.getUserdetail().getTelephone().getTelephone(), "12121212121");
        Assert.assertEquals(account.getUserdetail().getTelephone().getIsValid(), Boolean.FALSE);
        Assert.assertEquals(account.getUserdetail().getEmail().getEmailId(), "test@1121.com");
        Assert.assertEquals(account.getUserdetail().getEmail().getIsValid(), Boolean.FALSE);
    }

    @Test
    public void testGetAllAccount() {
        List<Account> accounts = GetAccountDao.getAllAccounts();

        for (Account account : accounts) {
            Assert.assertNotNull(account.getUserName());
            Assert.assertNotNull(account.getPassword());
            Assert.assertNotNull(account.getUserdetail().getFirstName());
            Assert.assertNotNull(account.getUserdetail().getMiddleName());
            Assert.assertNotNull(account.getUserdetail().getLastName());
            Assert.assertNotNull(account.getUserdetail().getAddressLine1());
            Assert.assertNotNull(account.getUserdetail().getAddressLine2());
            Assert.assertNotNull(account.getUserdetail().getZipCode());
            Assert.assertNotNull(account.getUserdetail().getCountry());
            Assert.assertNotNull(account.getUserdetail().getTelephone().getTelephone());
            Assert.assertNotNull(account.getUserdetail().getTelephone().getIsValid());
            Assert.assertNotNull(account.getUserdetail().getEmail().getEmailId());
            Assert.assertNotNull(account.getUserdetail().getEmail().getIsValid());
        }
    }
}
