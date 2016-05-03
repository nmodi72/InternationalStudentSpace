package main.java.com.internationalstudentspace.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.java.com.internationalstudentspace.bean.Accounts;  

public class AccountDao {

    /**
     * The database connection driver : Oracle DB
     */
    private final static String JDBC_ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    /**
     * The database connection driver : MySQL DB
     */
    private final static String JDBC_MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * The database connection name : Oracle DB
     */
    private final static String ORACLE_DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";

    /**
     * The database connection name : MySQL DB
     */
    private final static String MYSQL_DB_CONNECTION = "jdbc:mysql://localhost/DOM_ISS";

    /**
     * The connection user name
     */
    private final static String USER = "root";

    /**
     * The connection password
     */
    private final static String PASSWORD = "root";
    
    /**
     * helper method to get the connection
     * 
     * @return the sql connection
     */
    public static Connection getConnection(){  
        Connection connection = null;  
        try{  
            Class.forName(JDBC_MYSQL_DRIVER);
            connection = DriverManager.getConnection(MYSQL_DB_CONNECTION, USER, PASSWORD);  
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;  
    }

    /**
     * helper method to save the account object in DB
     * 
     * @return the status code
     */
    public static int save(Accounts accounts) {  
        int status=0;  
        try{  
            Connection connection = AccountDao.getConnection();  
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
            Connection connection = AccountDao.getConnection();  
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
            Connection connection = AccountDao.getConnection();  
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
     * helper method to get the account object from DB
     * 
     * @return the retrieved account object
     */
    public static Accounts getAccountById(int id){  
        Accounts accounts = new Accounts();  
        try{
            Connection connection = AccountDao.getConnection();  
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
            Connection connection = AccountDao.getConnection();  
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