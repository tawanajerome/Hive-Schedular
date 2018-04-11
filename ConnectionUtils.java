package hivescheduler.conn;

import java.sql.Connection;
import java.sql.SQLException;


// This class defines our own methods we will call to get, close and rollback a database connection.

public class ConnectionUtils {

    // This function calls the SQL getConnection() method to get an SQL connection.

    public static Connection getConn() throws ClassNotFoundException, SQLException{

        return MySQLConnUtils.getConnection();
    }


    // This function attempts to close a connection and if it is not possible, does nothing.

    public static void closeConn(Connection conn){

        try{
            conn.close();
        }

        catch (Exception e){
        }
    }


    // This function just provides a way to rollback changes to the database in the event of an error.
    // We will use in our DBUtils to handle errors.

    public static void rollbackConn(Connection conn){

        try{
            conn.rollback();
        }

        catch(Exception e){
        }
    }

}
