package hivescheduler.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;

import hivescheduler.beans.employee;
import hivescheduler.beans.login;


//This class provides methods to remember logged in users and maintain various personalized features using cookies (like availability).

public class MyUtils {

    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_TO_STORE_USER_NAME_IN_COOKIE";


    // This function stores connection in a ServletRequest to store and handle a client's request (in this case, our database connection).
    // It binds that connection to the ATT_NAME_CONNECTION string.

    public static void storeConn(ServletRequest req, Connection conn){

        req.setAttribute(ATT_NAME_CONNECTION, conn);
    }


    //Get the "Connection" object that has been stored in ATT_NAME_CONNECTION.

    public static Connection getStoredConn(ServletRequest req){

        return (Connection) req.getAttribute(ATT_NAME_CONNECTION);
    }


    // Store login information of the user (a 'login' object) after login.
    // This binds the user information to the session so that it persists over multiple connections.

    public static void storeLoginedEmp(HttpSession session, login loginedEmp){


        // This binds an object (in this case a login object - or row in login database) to a particular session.
        // The attribute is given the identifying name "loginedEmp".

        session.setAttribute("loginedEmp", loginedEmp);
    }


    // Store employee information associated with the login object stored in "loginedEmp" after login.
    // This binds the user information to the session so that it persists over multiple connections.

    public static void storeCurrentEmp(HttpSession session, login loginedEmp, HttpServletRequest request) throws IOException, SQLException {


        // This binds an object (in this case an employee object - or row in login database) to a particular session.
        // The attribute is given the identifying name "currentEmp".

        Connection conn = MyUtils.getStoredConn(request);

        employee emp = DBUtils.findEmployee(conn, loginedEmp.getEmail(), loginedEmp.getPassword());

        session.setAttribute("currentEmp", emp);
    }


    //Get the stored login information (a 'login' object) of the logged in user.

    public static login getLoginedEmp(HttpSession session){


        // This accesses and returns the saved login row information stored in "loginedemp" attribute.

        return (login) session.getAttribute("loginedEmp");
    }


    //Get the stored employee information (an 'employee' object) of the logged in user.

    public static employee getCurrentEmp(HttpSession session){


        // This accesses and returns the saved employee row information stored in "currentEmp" attribute.

        return (employee) session.getAttribute("currentEmp");
    }


    // Store user email info in cookie attribute.

    public static void storeEmpCookie(HttpServletResponse response, login log){

        System.out.println("Store User Cookie");

        // This creates a new cookie attribute and sets the name (identifying attribute) of the cookie to ATT_NAME_USER_NAME.
        // It also sets the data stored in the cookie to be the email of the logged in user.

        Cookie cookieEmpMail = new Cookie(ATT_NAME_USER_NAME,log.getEmail());


        //1 day (In Seconds) is set as the max the cookie can exist for.

        cookieEmpMail.setMaxAge(24*60*60);


        response.addCookie(cookieEmpMail);
    }


    // Accessing user email info in cookie.
    // A HTTP Servlet request is used to request access to the user's cookies.

    public static String getEmpMailInCookie(HttpServletRequest req){

        Cookie[] cookies = req.getCookies(); // This gets the cookies from the user's HTTP servlet.


        if(cookies != null){

            // This goes through all the cookies returned (all the cookies in the user's HTTP servlet).

            for(Cookie cook : cookies){

                // If the cookie is the cookie that holds the user's email information, the value of the cookie (email) is returned.

                if (ATT_NAME_USER_NAME.equals(cook.getName())){

                    return cook.getValue();
                }
            }
        }

        return null;
    }


    // Delete Cookie

    public static void deleteEmpCookie(HttpServletResponse response){


        Cookie cookieEmpEmail = new Cookie(ATT_NAME_USER_NAME,null);

        //Set cookie expiration time to 0 seconds.

        cookieEmpEmail.setMaxAge(0);

        response.addCookie(cookieEmpEmail);
    }
}
