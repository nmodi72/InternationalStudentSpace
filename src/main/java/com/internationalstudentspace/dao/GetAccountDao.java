package main.java.com.internationalstudentspace.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.internationalstudentspace.bean.Account;
import main.java.com.internationalstudentspace.bean.Email;
import main.java.com.internationalstudentspace.bean.Telephone;
import main.java.com.internationalstudentspace.bean.UserDetail;  

public class GetAccountDao {
    /**
     * the sql query to get the account user information
     */
    private static final String GET_ALL_ACCOUNTS = "select * from T_ISS_ACCT_USR"; 

    /**
     * the sql query to get the account user information
     */
    private static final String GET_ACCT_USR_QUERY = "select * from T_ISS_ACCT_USR where USR_NM=?"; 

    /**
     * the sql query to get user detail
     */
    private static final String GET_USR_DTL_QUERY = "select * from T_ISS_ACCT_USR_DTL where ACCT_USR_DTL_ID=?"; 

    /**
     * the sql query to get telephone detail 
     */
    private static final String GET_TELEPHONE_NUMBER_QUERY = "select * from T_ISS_USR_TPH_TBL_ID where USR_TPH_TBL_ID=?"; 

    /**
     * the sql query to get the email detail 
     */
    private static final String GET_EMAIL_QUERY = "select * from T_ISS_USR_EMAIL_TBL_ID where USR_EMAIL_TBL_ID=?"; 

    /**
     * helper method to retrieve all the accounts list
     * 
     * @return the list of account object
     */
    public static List<Account> getAllAccounts(){  
        List<Account> list = new ArrayList<Account>();  
        try{  
            Connection connection = ConnectionDao.getConnection();  
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ACCOUNTS);  
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){  
                Account account = new Account();
                account.setUserName(resultSet.getString(2));
                account.setPassword(resultSet.getString(3));
                int userDetailId = resultSet.getInt(4);
                account.setUserdetail(retrieveUserDetail(connection, userDetailId));
                list.add(account);  
            }  
            connection.close();  
        } catch(Exception e) {
            e.printStackTrace();
        }  
        return list;  
    }

    /**
     * helper method to get the account object in DB
     * 
     * @return the status code
     */
    public static Account getAccount(String userName) {
        Connection connection = null;
        Account account = null;
        try{
            connection = ConnectionDao.getConnection();
            account = retrieveAccount(connection, userName);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                System.out.println("SQLException in rollback"+e.getMessage());
            }
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return account;
    }

    /**
     * helper method to insert the telephone data
     * 
     * @throws SQLException 
     */
    private static Account retrieveAccount(Connection connection, String userName) throws SQLException {
        Account account = new Account();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCT_USR_QUERY);  
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();  
        if(resultSet.next()){
            account.setUserName(resultSet.getString(2));
            account.setPassword(resultSet.getString(3));
            int userDetailId = resultSet.getInt(4);
            account.setUserdetail(retrieveUserDetail(connection, userDetailId));
        }
        preparedStatement.close();
        return account;
    }

    /**
     * helper method to insert the telephone data
     * 
     * @throws SQLException 
     */
    private static UserDetail retrieveUserDetail(Connection connection, Integer userDetailId) throws SQLException {
        UserDetail userDetail = new UserDetail();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USR_DTL_QUERY);  
        preparedStatement.setInt(1, userDetailId);
        ResultSet resultSet = preparedStatement.executeQuery();  
        if(resultSet.next()){
            userDetail.setFirstName(resultSet.getString(2));
            userDetail.setMiddleName(resultSet.getString(3));
            userDetail.setLastName(resultSet.getString(4));
            userDetail.setAddressLine1(resultSet.getString(5));
            userDetail.setAddressLine2(resultSet.getString(6));
            userDetail.setZipCode(resultSet.getString(7));
            userDetail.setCountry(resultSet.getString(8));
            int telephoneId = resultSet.getInt(9);
            int emailId = resultSet.getInt(10);
            userDetail.setTelephone(retrieveTelephoneDetail(connection, telephoneId));
            userDetail.setEmail(retrieveEmailDetail(connection, emailId));
        }
        preparedStatement.close();
        return userDetail;
    }

    /**
     * helper method to insert the telephone data
     * 
     * @throws SQLException 
     */
    private static Telephone retrieveTelephoneDetail(Connection connection, Integer telephoneId) throws SQLException {
        Telephone telephone = new Telephone();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_TELEPHONE_NUMBER_QUERY);  
        preparedStatement.setInt(1, telephoneId);
        ResultSet resultSet = preparedStatement.executeQuery();  
        if(resultSet.next()){
            telephone.setTelephone(resultSet.getString(2));
            telephone.setIsValid(resultSet.getBoolean(3));
        }
        preparedStatement.close();
        return telephone;
    }

    /**
     * helper method to insert the telephone data
     * 
     * @throws SQLException 
     */
    private static Email retrieveEmailDetail(Connection connection, Integer emailId) throws SQLException {
        Email email = new Email();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_EMAIL_QUERY);  
        preparedStatement.setInt(1, emailId);
        ResultSet resultSet = preparedStatement.executeQuery();  
        if(resultSet.next()){
            email.setEmailId(resultSet.getString(2));
            email.setIsValid(resultSet.getBoolean(3));
        }
        preparedStatement.close();
        return email;
    }
}  