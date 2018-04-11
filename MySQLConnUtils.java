package hivescheduler.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// This class provides information about our Database System connection parameters.

public class MySQLConnUtils {

    // This function specifies our database's information and calls getConnection below.
    // It throws an SQLException error in the event that a connection was not able to be made.

    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        // MySQL Database Connection Information Parameters.

        String host = "10.226.140.147";
        String db = "hivescheduler";
        String user = "root";
        String pword = "Romiyo99";

        return getConnection(host, db, user,pword); // Calls getConnection below.
    }


    // This function actually gets the connection from the database.

    public static Connection getConnection(String host, String db, String user, String pword) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver"); // This assigns the name to the JDBC Driver instantiated at runtime (https://www.tutorialspoint.com/java/lang/class_forname_loader.htm)


        String connURL = "jdbc:mysql://" + host + ":3306/" + db; // This specifies the connection URL for MySQL

       return DriverManager.getConnection(connURL, user, pword);
    }

}
