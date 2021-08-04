package main.java.com.internationalstudentspace.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.internationalstudentspace.bean.Account;
import main.java.com.internationalstudentspace.bean.Email;
import main.java.com.internationalstudentspace.bean.Telephone;
import main.java.com.internationalstudentspace.bean.UserDetail;  

public class CreateNewAccountDao {

    /**
     * the sql query to select highest auto incremented number from table 
     */
    private static final String SELECT_AUTO_INCREMENT_VALUE_QUERY = "SELECT Auto_increment FROM information_schema.tables WHERE table_name=? AND table_schema=?"; 

    /**
     * the sql query to insert the telephone number value in telephone table
     */
    private static final String INSERT_TELEPHONE_NUMBER_QUERY = "insert into T_ISS_USR_TPH_TBL_ID(TPH_NUM,IS_VLD) values (?,?)"; 

    /**
     * the sql query to insert the email value in email table
     */
    private static final String INSERT_EMAIL_QUERY = "insert into T_ISS_USR_EMAIL_TBL_ID(EMAIL_ID,IS_VLD) values (?,?)"; 

    /**
     * the sql query to insert the user detail 
     */
    private static final String INSERT_USER_DETAIL_QUERY = "insert into T_ISS_ACCT_USR_DTL(FRST_NM,MDL_NM,LST_NM,ADDR_LN_ONE,ADDR_LN_TWO,ZIP_CD,CNTRY,USR_TPH_TBL_ID,USR_EMAIL_TBL_ID) values (?,?,?,?,?,?,?,?,?)"; 

    /**
     * the sql query to insert the account user detail 
     */
    private static final String T_ISS_ACCT_USR_DTL = "insert into T_ISS_ACCT_USR(USR_NM,PWD,ACCT_USR_DTL_ID) values (?,?,?)"; 

    /**
     * helper method to save the account object in DB
     * 
     * @return the status code
     */
    public static int createAccount(Account account) {
        Connection connection = null;
        int status=0;
        try{
            connection = ConnectionDao.getConnection();
            status = insertAccountData(connection, account);
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
        return status;
    }

    /**
     * helper method to insert the telephone data
     * 
     * @throws SQLException 
     */
    private static int insertTelephoneData(Connection connection, Telephone telephone) throws SQLException {
        int status = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TELEPHONE_NUMBER_QUERY);  
        preparedStatement.setString(1, telephone.getTelephone());
        preparedStatement.setBoolean(2, telephone.getIsValid());
        status = preparedStatement.executeUpdate();  
        preparedStatement.close();
        return status;
    }

    /**
     * helper method to insert the telephone data
     * 
     * @throws SQLException 
     */
    private static int insertEmailData(Connection connection, Email email) throws SQLException {
        int status = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMAIL_QUERY);  
        preparedStatement.setString(1, email.getEmailId());
        preparedStatement.setBoolean(2, email.getIsValid());
        status = preparedStatement.executeUpdate();  
        preparedStatement.close(); 
        return status;
    }

    /**
     * helper method to insert the user detail data
     * 
     * @throws SQLException 
     */
    private static int insertUserDetailData(Connection connection, UserDetail userDetail) throws SQLException {
        int status = 0;
        int telephoneDataUpdateStatus = insertTelephoneData(connection, userDetail.getTelephone());
        int emailDataUpdateStatus = insertEmailData(connection, userDetail.getEmail());
        if (telephoneDataUpdateStatus != 0 && emailDataUpdateStatus != 0) {
            int latestValueForTphTbl = getLatestAutogeneratedValue("DOM_ISS", "T_ISS_USR_TPH_TBL_ID");
            int latestValueForEmailTbl = getLatestAutogeneratedValue("DOM_ISS", "T_ISS_USR_EMAIL_TBL_ID");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_DETAIL_QUERY);  
            preparedStatement.setString(1, userDetail.getFirstName());
            preparedStatement.setString(2, userDetail.getMiddleName());
            preparedStatement.setString(3, userDetail.getLastName());
            preparedStatement.setString(4, userDetail.getAddressLine1());
            preparedStatement.setString(5, userDetail.getAddressLine2());
            preparedStatement.setString(6, userDetail.getZipCode());
            preparedStatement.setString(7, userDetail.getCountry());
            preparedStatement.setInt(8, latestValueForTphTbl);
            preparedStatement.setInt(9, latestValueForEmailTbl);
            status = preparedStatement.executeUpdate();  
            preparedStatement.close();
        }
        return status;
    }

    /**
     * helper method to insert the account data
     * 
     * @throws SQLException 
     */
    private static int insertAccountData(Connection connection, Account account) throws SQLException {
        int status = 0;
        int userDetailUpdateStatus = insertUserDetailData(connection, account.getUserdetail());
        if (userDetailUpdateStatus != 0) {
            int latestValueForUsrDtl = getLatestAutogeneratedValue("DOM_ISS", "T_ISS_ACCT_USR_DTL");

            PreparedStatement preparedStatement = connection.prepareStatement(T_ISS_ACCT_USR_DTL);  
            preparedStatement.setString(1, account.getUserName());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setInt(3, latestValueForUsrDtl);
            status = preparedStatement.executeUpdate();  
            preparedStatement.close();
        }
        return status;
    }

    /**
     * helper method to get the latest auto generated id in table
     * 
     * @param schemaName the schema name
     * @param tableName the table name
     * @return the autoGeneratedValue
     */
    private static int getLatestAutogeneratedValue(String schemaName, String tableName) {
        int autoGeneratedValue = 0;  
        try{
            Connection connection = ConnectionDao.getConnection();  
            PreparedStatement ps = connection.prepareStatement(SELECT_AUTO_INCREMENT_VALUE_QUERY);  
            ps.setString(1,tableName); 
            ps.setString(2,schemaName); 
            ResultSet resultSet = ps.executeQuery();  
            if(resultSet.next()){
                //nextGenerated value - 1
                autoGeneratedValue = resultSet.getInt(1) - 1;
             }
            connection.close();  
        } catch(Exception e) {
            e.printStackTrace();
        }
        return autoGeneratedValue;  
    }
//
//    /**
//     * helper method to update the account object in DB
//     * 
//     * @return the status code
//     */
//    public static int update(Accounts accounts) {  
//        int status=0;  
//        try{  
//            Connection connection = ConnectionDao.getConnection();  
//            PreparedStatement preparedStatement = connection.prepareStatement(  
//                         "update T_ISS_ACCT set USR_NM=?,PWD=?,TPH_NUM=?,EMAIL=?,CNTRY=? where ISS_ACCT_ID=?");  
//            preparedStatement.setString(1, accounts.getUserName());  
//            preparedStatement.setString(2, accounts.getPassword());  
//            preparedStatement.setString(3, accounts.getPhoneNumber()); 
//            preparedStatement.setString(4, accounts.getEmail()); 
//            preparedStatement.setString(5, accounts.getCountry());
//            preparedStatement.setInt(6, accounts.getId());
//            
//            status = preparedStatement.executeUpdate();  
//              
//            connection.close();  
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return status;  
//    }
//
//    /**
//     * helper method to delete the account object in DB
//     * 
//     * @return the status code
//     */
//    public static int delete(int id){  
//        int status=0;  
//        try{  
//            Connection connection = ConnectionDao.getConnection();  
//            PreparedStatement preparedStatement = connection.prepareStatement("delete from T_ISS_ACCT where ISS_ACCT_ID=?");  
//            preparedStatement.setInt(1,id);  
//            status = preparedStatement.executeUpdate();  
//            connection.close();  
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return status;  
//    }
//
//    /**
//     * helper method to validate credential
//     * 
//     * @param userName the user name
//     * @param password the password
//     * @return true in case if the credential is valid, false otherwise.
//     */
//    public static boolean validateCredential(String userName, String password){  
//        List<Accounts> accountList = getAllAccounts();
//        for (Accounts account : accountList) {
//            if (account.getUserName().equals(userName) && account.getPassword().equals(password)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * helper method to get the account object from DB
//     * 
//     * @return the retrieved account object
//     */
//    public static Accounts getAccountById(int id){  
//        Accounts accounts = new Accounts();  
//        try{
//            Connection connection = ConnectionDao.getConnection();  
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from T_ISS_ACCT where ISS_ACCT_ID=?");  
//            preparedStatement.setInt(1,id);  
//            ResultSet resultSet = preparedStatement.executeQuery();  
//            if(resultSet.next()){  
//                accounts.setId(resultSet.getInt(1));
//                accounts.setUserName(resultSet.getString(2));
//                accounts.setPassword(resultSet.getString(3));
//                accounts.setPhoneNumber(resultSet.getString(4));
//                accounts.setEmail(resultSet.getString(5));
//                accounts.setCountry(resultSet.getString(6));
//            }  
//            connection.close();  
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return accounts;  
//    }
//
//    /**
//     * helper method to retrieve all the accounts list
//     * 
//     * @return the list of account object
//     */
//    public static List<Accounts> getAllAccounts(){  
//        List<Accounts> list = new ArrayList<Accounts>();  
//          
//        try{  
//            Connection connection = ConnectionDao.getConnection();  
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from T_ISS_ACCT");  
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){  
//                Accounts accounts = new Accounts();
//                accounts.setId(resultSet.getInt(1));
//                accounts.setUserName(resultSet.getString(2));
//                accounts.setPassword(resultSet.getString(3));
//                accounts.setPhoneNumber(resultSet.getString(4));
//                accounts.setEmail(resultSet.getString(5));
//                accounts.setCountry(resultSet.getString(6));
//                list.add(accounts);  
//            }  
//            connection.close();  
//        } catch(Exception e) {
//            e.printStackTrace();
//        }  
//        return list;  
//    }
}  