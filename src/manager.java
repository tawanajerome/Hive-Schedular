import com.util.dbconnection;

import javax.xml.transform.Result;
import java.sql.*;

public class manager{

    //// ADDS a new EMPLOYEE TO THE employee table
    public ArrayList<employee> addEmployeeString(Connection conn, employee e)
    {
        try {
            //Connection conn = DBconnection.getMySQLConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO employees VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, e.getSSN());
            pstm.setString(2, e.getfname());
            pstm.setString(3, e.getlname());
            pstm.setString(4, e.getJID());
            pstm.setInt(5, e.getwage());
            pstm.setString(6, e.getphone());
            pstm.setString(7, e.getemail());
            pstm.setString(8, e.getlid());

            pstm.executeUpdate();   // ADDS a new row (employee) into the employee table

            //////store values into an array list and return the list instead of nothing, to make life much easier

            sql = "Select SSN, firstname, lastname, jid, wage, phone, email, lid from employees";     //RETURNS FULL EMPLOYEE TABLE
            employee emp;
            ResultSet rs = stmt.executeQuery(sql);      ///rs points to the result set and the loop parses through every row
            while(rs.next())
            {
                emp.setSSN(rs.getString(1));
                emp.setfirstname(rs.getString(2));
                emp.setlastname(rs.getString(3));
                emp.setjid(rs.getString(4));
                emp.setwage(rs.getInt(5));
                emp.setphone(rs.getString(6));
                emp.setemail(rs.getString(7));
                emp.setlid(rs.getString(8));

                list.add(emp);
            }

            pstm.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();}
    }

    public ArrayList<schedule> UpdateSchedule(Connection conn, schedule s)
    {
        try {
            //// Connection conn = DBconnection.getMySQLConnection();
            Statement stmt = conn.createStatement();
            ArrayList<schedule> list = new ArrayList<schedule>();
            schedule newsched;
            PreparedStatement pstm = null;
            ResultSet rs;
            String sql;

            /////// Check the availabilty of that employee before setting the schedule

            sql = "SELECT S.eid, S.day, S.time, S.etime FROM schedula as S, availability as A where A.eid = ? and " +
                    "A.day = ? and A.stime <= ? and A.etime >= ?";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, s.geteid());
            pstm.setString(2, s.getday());
            pstm.setString(3, s.getstime());
            pstm.setString(4, s.getetime());

            if(pstm.execute(sql))      //if this employee is avaialble, the query will return true otherwise they wont be added
            {
                sql = "UPDATE schedule SET day = ?, stime = ?, etime = ? WHERE eid = ?";

                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1, s.getday());
                pstm.setString(2, s.getstime());
                pstm.setString(3, s.getetime());
                pstm.setString(4, s.geteid());

                pstm.executeUpdate();
            }
             else
                System.out.println("Employee is not available to work at this time");

            sql = "Select eid, day, stime, etime from schedule";
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                    newsched.seteid(rs.getString(1));
                    newsched.setday(rs.getString(2));
                    newsched.setetime(rs.getTime(4));
                    newsched.setstime(rs.getTime(3));
                    list.add(newsched);
            }
            pstm.close();
            stmt.close();
            return list;
        }
        catch (SQLException e) {
            e.printStackTrace();}
    }

    public void makeSchedule(void) <--- //think about parameters because manager should not have to enter the employee eid each time just name

       ///Have this function call the updateschedule function .... what if this took care of each day of the week
        ////////research front-end stuff and buttons/tables work in javaFX
        ///what if we use the update schedule function to fill the schedule table and from there have another table



            //// what if we just output each day as table in itself and manualy have the add button for each day

    ////// ORRRRR Have choice/check boxes for every employee at the location and have check boxes for each seperate day
    /////  get a running (data structure) of all the selected employees, make sure their time fits and add them to the
    /////  schedule and outoput
    }






