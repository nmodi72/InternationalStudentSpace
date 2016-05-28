package main.java.com.internationalstudentspace.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.java.com.internationalstudentspace.bean.Accounts;  

public class ConnectionDao {

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
    private final static String DB_USERNAME = "root";

    /**
     * The connection password
     */
    private final static String DB_PASSWORD = "root";

    /**
     * helper method to get the connection
     * 
     * @return the sql connection
     */
    public static Connection getConnection(){  
        Connection connection = null;  
        try{  
            Class.forName(JDBC_MYSQL_DRIVER);
            connection = DriverManager.getConnection(MYSQL_DB_CONNECTION, DB_USERNAME, DB_PASSWORD);  
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;  
    }
}  