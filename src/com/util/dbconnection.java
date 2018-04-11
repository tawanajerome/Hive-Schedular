package com.util;
import java.sql.*;

public class dbconnection
{
    public static Connection getMySQLConnection() {
        String hostname = "10.226.140.147";
        String username = "root";
        String password = "Romiyo99";
        String DBname = "hivescheduler";


        return getMySQLConnection(hostname, username, password, DBname);
    }


    private static Connection getMySQLConnection(String hname, String uname, String password, String DBname) {
        try {
            Class.forName("com.mysql.jdbc.Driver");     /*establishes the driver connection */
            String url = "jdbc:mysql://" + hname + "/" + DBname;
            Connection conn = DriverManager.getConnection(url, uname, password);
            if (conn != null)
                System.out.println("Connection Established");
            return conn;
        } catch (SQLException | ClassNotFoundException s) {
            s.printStackTrace();
        }

        return null;
    }
}
