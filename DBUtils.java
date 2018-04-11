package hivescheduler.utils;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import hivescheduler.beans.employee;
import hivescheduler.beans.availability;
import hivescheduler.beans.schedule;
import hivescheduler.beans.login;
import hivescheduler.beans.timeoff;


public class DBUtils {

    // This function searches the database for an employee and returns an employee object with his information.

    public static employee findEmployee(Connection conn, String email, String pswd) throws SQLException, IOException{

        //  sql queries are written in the format below:

        // 1) First create a string to hold the query (the values of attributes are put as question marks).

        String sql = "Select e.SSN, e.firstname, e.lastname, e.jid, e.wage, e.phone, e.email " +
                "from employees e, login lg " +
                "where e.email = lg.email and  lg.email = ? and lg.password = ?";


        // 2) Creating a PreparedStatement object of the above string.
        // This allows one to parse in values to the appropriate parameters.

        PreparedStatement pstm = conn.prepareStatement(sql);


        // 3) Assigning the values of the placeholders created with the setString method.
        // Parameter indexing is used.

        pstm.setString(1, email);
        pstm.setString(2, pswd);


        // This creates a result set object to hold the result of the query.
        // The query is executed using .executeQuery

        ResultSet rs = pstm.executeQuery();


        // Going through query results.
        if(rs.next()){

            // Getting the contents of the returned row and assigning them to variables.

            int SSN = rs.getInt("SSN");
            String fname = rs.getString("firstname");
            String lname = rs.getString("lastname");
            int jid = rs.getInt("jid");
            int wage = rs.getInt("wage");
            String phone = rs.getString("phone");
            String mail = rs.getString("email");


            employee emp = new employee(); // Creating a new employee object to hold the result of the query.


            // Setting the values of the employee.
            emp.setEmployeeSSN(SSN);
            emp.setEmployeeFname(fname);
            emp.setEmployeeLname(lname);
            emp.setEmployeeJid(jid);
            emp.setEmployeeWage(wage);
            emp.setEmployeePhone(phone);
            emp.setEmployeeEmail(mail);


            return emp;
        }

        return null;
    }


    public static login findLog(Connection conn, String email) throws SQLException, IOException{

        String sql = "select l.email, l.password, l.jid " +
                "from login l " +
                "where l.email = ?";


        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, email);


        ResultSet rs = pstm.executeQuery();


        while(rs.next()){

            login log = new login();

            log.setEmail(rs.getString("email"));

            log.setJid(rs.getInt("jid"));

            log.setPassword(rs.getString("password"));


            return log;
        }

        return null;
    }


    // This method gets all the availability for a particular employee.

    public static List<availability> getAvailability(Connection conn, int soc) throws SQLException{

        String sql = "select a.eid, a.day, a.stime, a.etime " +
                "from employees e, availability a " +
                "where e.SSN = ? and a.eid = e.SSN";


        PreparedStatement pstm = conn.prepareStatement(sql);


        pstm.setInt(1,soc);


        ResultSet rs = pstm.executeQuery();


        List<availability> availabilityList  = new ArrayList<availability>(); // Specifying a list to hold all the availabilities of employee.


        while (rs.next()){

            availability av = new availability(); // Creating a new availability object.


            av.setEID(rs.getInt("eid"));
            av.setDay(rs.getString("day"));
            av.setStime(rs.getTime("stime"));
            av.setEtime(rs.getTime("etime"));


            availabilityList.add(av);
        }

        return availabilityList;
    }


    // This method allows a user to add an availaility in the form of an availability object to his/her schedule

    public static void addAvailability(Connection conn, availability avail) throws SQLException{

        String sql = "insert into availability(eid, day, stime, etime) values(?,?,?,?)";


        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, avail.getEID());
        pstm.setString(2, avail.getDay());
        pstm.setTime(3, avail.getStime());
        pstm.setTime(4, avail.getEtime());


        pstm.executeUpdate();
    }


    // This gets the schedule for the logged in employee.

    public static List<schedule> getSchedule (Connection conn, int SSN) throws SQLException{

        String sql = "select s.eid, s.day, s.stime, s.etime " +
                "from employees e, schedule s " +
                "where e.SSN = ? and s.eid = e.SSN";


        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, SSN);


        ResultSet rs = pstm.executeQuery();


        List<schedule> scheduleList = new ArrayList<>();


        while(rs.next()){

            schedule schdul = new schedule();


            schdul.setEID(rs.getInt("eid"));
            schdul.setDay(rs.getString("day"));
            schdul.setStime(rs.getTime("stime"));
            schdul.setEtime(rs.getTime("etime"));


            scheduleList.add(schdul);
        }

        return scheduleList;
    }


    public static List<timeoff> getTimeoff (Connection conn, int SSN) throws SQLException{

        String sql = "select s.eid, t.date, t.stime, t.etime " +
                "from employees e, timeoff t " +
                "where e.SSN = ? and t.eid = e.SSN";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, SSN);


        ResultSet rs = pstm.executeQuery();


        List<timeoff> timeOffList = new ArrayList<>();


        while(rs.next()){

            timeoff vacatn = new timeoff();


            vacatn.setEID(rs.getInt("eid"));
            vacatn.setDate(rs.getDate("date"));
            vacatn.setStime(rs.getTime("stime"));
            vacatn.setEtime(rs.getTime("etime"));


            timeOffList.add(vacatn);
        }

        return timeOffList;
    }


    public static void setTimeoff (Connection conn, timeoff vacatn) throws SQLException{

        String sql = "insert into timeoff(eid, date, stime, etime) values(?,?,?,?)";


        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, vacatn.getEID());
        pstm.setDate(2,vacatn.getDate());
        pstm.setTime(3,vacatn.getStime());
        pstm.setTime(4, vacatn.getEtime());

        pstm.executeUpdate();
    }
}
