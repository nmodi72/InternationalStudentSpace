package main.java.com.internationalstudentspace.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.java.com.internationalstudentspace.bean.Accounts;  

public class AccountDao {

    /**
     * helper method to save the account object in DB
     * 
     * @return the status code
     */
    public static int save(Accounts accounts) {  
        int status=0;  
        try{  
            Connection connection = ConnectionDao.getConnection();  
            PreparedStatement preparedStatement = connection.prepareStatement(  
                         "insert into T_ISS_ACCT(USR_NM,PWD,TPH_NUM,EMAIL,CNTRY) values (?,?,?,?,?)");  
            preparedStatement.setString(1, accounts.getUserName());  
            preparedStatement.setString(2, accounts.getPassword());  
            preparedStatement.setString(3, accounts.getPhoneNumber());
            preparedStatement.setString(4, accounts.getEmail());  
            preparedStatement.setString(5, accounts.getCountry());  
              
            status=preparedStatement.executeUpdate();  
              
            connection.close();  
        } catch(Exception e) {
            e.printStackTrace();
        }
        return status;  
    }

    /**
     * helper method to update the account object in DB
     * 
     * @return the status code
     */
    public static int update(Accounts accounts) {  
        int status=0;  
        try{  
            Connection connection = ConnectionDao.getConnection();  
            PreparedStatement preparedStatement = connection.prepareStatement(  
                         "update T_ISS_ACCT set USR_NM=?,PWD=?,TPH_NUM=?,EMAIL=?,CNTRY=? where ISS_ACCT_ID=?");  
            preparedStatement.setString(1, accounts.getUserName());  
            preparedStatement.setString(2, accounts.getPassword());  
            preparedStatement.setString(3, accounts.getPhoneNumber()); 
            preparedStatement.setString(4, accounts.getEmail()); 
            preparedStatement.setString(5, accounts.getCountry());
            preparedStatement.setInt(6, accounts.getId());
            
            status = preparedStatement.executeUpdate();  
              
            connection.close();  
        } catch(Exception e) {
            e.printStackTrace();
        }
        return status;  
    }

    /**
     * helper method to delete the account object in DB
     * 
     * @return the status code
     */
    public static int delete(int id){  
        int status=0;  
        try{  
            Connection connection = ConnectionDao.getConnection();  
            PreparedStatement preparedStatement = connection.prepareStatement("delete from T_ISS_ACCT where ISS_ACCT_ID=?");  
            preparedStatement.setInt(1,id);  
            status = preparedStatement.executeUpdate();  
            connection.close();  
        } catch(Exception e) {
            e.printStackTrace();
        }
        return status;  
    }

    /**
     * helper method to validate credential
     * 
     * @param userName the user name
     * @param password the password
     * @return true in case if the credential is valid, false otherwise.
     */
    public static boolean validateCredential(String userName, String password){  
        List<Accounts> accountList = getAllAccounts();
        for (Accounts account : accountList) {
            if (account.getUserName().equals(userName) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * helper method to get the account object from DB
     * 
     * @return the retrieved account object
     */
    public static Accounts getAccountById(int id){  
        Accounts accounts = new Accounts();  
        try{
            Connection connection = ConnectionDao.getConnection();  
            PreparedStatement preparedStatement = connection.prepareStatement("select * from T_ISS_ACCT where ISS_ACCT_ID=?");  
            preparedStatement.setInt(1,id);  
            ResultSet resultSet = preparedStatement.executeQuery();  
            if(resultSet.next()){  
                accounts.setId(resultSet.getInt(1));
                accounts.setUserName(resultSet.getString(2));
                accounts.setPassword(resultSet.getString(3));
                accounts.setPhoneNumber(resultSet.getString(4));
                accounts.setEmail(resultSet.getString(5));
                accounts.setCountry(resultSet.getString(6));
            }  
            connection.close();  
        } catch(Exception e) {
            e.printStackTrace();
        }
        return accounts;  
    }

    /**
     * helper method to retrieve all the accounts list
     * 
     * @return the list of account object
     */
    public static List<Accounts> getAllAccounts(){  
        List<Accounts> list = new ArrayList<Accounts>();  
          
        try{  
            Connection connection = ConnectionDao.getConnection();  
            PreparedStatement preparedStatement = connection.prepareStatement("select * from T_ISS_ACCT");  
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){  
                Accounts accounts = new Accounts();
                accounts.setId(resultSet.getInt(1));
                accounts.setUserName(resultSet.getString(2));
                accounts.setPassword(resultSet.getString(3));
                accounts.setPhoneNumber(resultSet.getString(4));
                accounts.setEmail(resultSet.getString(5));
                accounts.setCountry(resultSet.getString(6));
                list.add(accounts);  
            }  
            connection.close();  
        } catch(Exception e) {
            e.printStackTrace();
        }  
        return list;  
    }
}  