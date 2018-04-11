import com.util.*;
//import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;

public class manager{

    //// ADDS a new EMPLOYEE TO THE employee table
    public ArrayList<employee> addEmployee(employee e)
    {
        ArrayList<employee> list = new ArrayList<employee>();
        employee emp = new employee();
        try {
            Connection conn = dbconnection.getMySQLConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO employees VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, e.getSSN());
            pstm.setString(2, e.getFirstname());
            pstm.setString(3, e.getLastname());
            pstm.setInt(4, e.getJid());
            pstm.setInt(5, e.getWage());
            pstm.setString(6, e.getPhone());
            pstm.setString(7, e.getEmail());
            pstm.setInt(8, e.getLid());

            pstm.executeUpdate();

            sql = "Select SSN, firstname, lastname, jid, wage, phone, email, lid from employees";     //RETURNS FULL EMPLOYEE TABLE
            ResultSet rs = stmt.executeQuery(sql);      ///rs points to the result set and the loop parses through every row
            while(rs.next())
            {
                emp.setSSN(rs.getInt(1));
                emp.setFirstname(rs.getString(2));
                emp.setLastname(rs.getString(3));
                emp.setJid(rs.getInt(4));
                emp.setWage(rs.getInt(5));
                emp.setPhone(rs.getString(6));
                emp.setEmail(rs.getString(7));
                emp.setLid(rs.getInt(8));

                list.add(emp);
            }
            pstm.close();
            stmt.close();
        }
          catch (SQLException x) {
            x.printStackTrace();}
            return list;
    }

    public ArrayList<schedule> UpdateSchedule(schedule s)
    {
        ArrayList<schedule> list = new ArrayList<schedule>();
        schedule newsh = new schedule();
        try {
             Connection conn = dbconnection.getMySQLConnection();
            Statement stmt = conn.createStatement();
            PreparedStatement pstm = null;
            ResultSet rs;
            String sql;

            /////// Check the availabilty of that employee before setting the schedule

            sql = "SELECT S.eid, S.day, S.stime, S.etime FROM schedule as S, availability as A where A.eid = ? and " +
                    "A.day = ? and A.stime <= ? and A.etime >= ?";

            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, s.getEid());
            pstm.setString(2, s.getDay());
            pstm.setTime(3, s.getStime());
            pstm.setTime(4, s.getEtime());

            if(pstm.execute(sql))      //if this employee is avaialble, the query will return true otherwise they wont be added
            {
                sql = "UPDATE schedule SET day = ?, stime = ?, etime = ? WHERE eid = ?";

                pstm = conn.prepareStatement(sql);
                pstm.setString(1, s.getDay());
                pstm.setTime(2, s.getStime());
                pstm.setTime(3, s.getEtime());
                pstm.setInt(4, s.getEid());

                pstm.executeUpdate();
            }
             else
                System.out.println("Employee is not available to work at this time");

            sql = "Select eid, day, stime, etime from schedule";
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                    newsh.setEid(rs.getInt(1));
                    newsh.setDay(rs.getString(2));
                    newsh.setEtime(rs.getTime(4));
                    newsh.setStime(rs.getTime(3));
                    list.add(newsh);
            }
            pstm.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();}

            return list;
    }

  //  public void makeSchedule{  //<--- //think about parameters because manager should not have to enter the employee eid each time just name

       ///Have this function call the updateschedule function .... what if this took care of each day of the week
        ////////research front-end stuff and buttons/tables work in javaFX
        ///what if we use the update schedule function to fill the schedule table and from there have another table



            //// what if we just output each day as table in itself and manualy have the add button for each day

    ////// ORRRRR Have choice/check boxes for every employee at the location and have check boxes for each seperate day
    /////  get a running (data structure) of all the selected employees, make sure their time fits and add them to the
    /////  schedule and outoput
    }






