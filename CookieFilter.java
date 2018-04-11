package hivescheduler.filters;

import hivescheduler.beans.employee;
import hivescheduler.beans.login;
import hivescheduler.utils.DBUtils;
import hivescheduler.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;


@WebFilter(filterName = "cookieFilter", urlPatterns = ("/*"))
public class CookieFilter implements Filter{

    public void CookieFilter(){

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException{

        System.out.println("Cookie Filter initialization");
    }

    @Override
    public void destroy(){

        System.out.println("Destroying Filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException{

        HttpServletRequest req = (HttpServletRequest) request;


        // 1) below returns a HTTP Session object that is associated with this request.
        // A session is basically a record - using a unique identifier - created when a user first accesses our website.
        // The user is bound to the session using the "storeLoginedEmp" method in "MyUtils" class.
        // In the "MyUtils" class, we bound the user's login information (a row in "login" table) to the session using attribute "loginedEmp".

        // 2) below gets the login information from the session.
        // We do this by calling "getLoginedEmp" method that we created in "MyUtils" class.
        // This function looks at the "loginedEmp" attribute associated with the current session and returns the login information it holds.


        // 1)
        HttpSession session = req.getSession();

        // 2)
        login empInSession = MyUtils.getLoginedEmp(session);


        // This checks "if" the session had the login information of the logged in employee bound to it.
        // Remember that this would have been returned by 2) above.
        if(empInSession != null){


            // Creates an attribute "COOKIE_CHECKED" to indicate whether or not we checked cookie.
            // Note that even if we used the session's "loginedEmp attribute to get login info, we still mark cookie as checked.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");

            chain.doFilter(request, response);

            return;
        }


        // Note that this filter comes after the "JDBCFilter" (we implement ordering in the "web.xml" file).
        // This means that we have already checked that this request needs a connection to the database.
        // Since "JDBCFilter created connection for the request and stored it in the "ATT_NAME_CONNECTION" attribute of the request using "storeConn" method in "MyUtils" class:
        // We can just call MyUtils.getStoredConn(request) method we declared to get the connection.

        Connection conn = MyUtils.getStoredConn(req);


        // This checks if we have assigned anything to this "COOKIE_CHECKED" attribute of this session.

        String checked = (String) session.getAttribute("COOKIE_CHECKED");


        // This "if" condition checks to see if the filter was able to get the user from the session.
        // It goes off of the fact that:
        // 1) if we have already set "COOKIE_CHECKED" attribute, then we must have got the user's login information from the session
        // 2) if we don't have a connection, this means that the JDBCFilter had ascertained that this was a non-special page (HTML, CSS, JavaScript, etc.).
        //    This would mean that this filter is irrelevant for this request as the request should not have cookies and such.

        if(checked == null && conn != null){


            // Retrieving the email the employee used to login.
            // This is got from the cookies.

            String employeeMail = MyUtils.getEmpMailInCookie(req);


            // Try to see if this person exists in the "login" DataBase relation.

            try{

                login log = DBUtils.findLog(conn, employeeMail);

                MyUtils.storeLoginedEmp(session, log);
            }

            catch (Exception e){

                e.printStackTrace();
            }

            session.setAttribute("cookies_checked", "CHECKED");
        }

        chain.doFilter(request, response);
    }

}
