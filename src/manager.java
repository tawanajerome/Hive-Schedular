import com.util.dbconnection;
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

        } catch (SQLException e) {
            e.printStackTrace();}
    }

    public ArrayList<schedule> UpdateSchedule(Connection conn, schedule s)
    {
        try {
            //// Connection conn = DBconnection.getMySQLConnection();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE schedule SET day = ?, stime = ?, etime = ? where eid = ?";

            //////// CHECK TO MAKE SURE THE EMPLOYEE IS AVAILABLE TO WORK AT THAT TIME //////
            /////// MODIFY QUERY

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, s.getday());
            pstm.setString(2, s.getstime());
            pstm.setString(3, s.getetime());
            pstm.setString(4, s.geteid());

            pstm.executeUpdate();

            sql = "Select eid, day, stime, etime from schedule";
            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<schedule> list = new ArrayList<schedule>();
            schedule s;

            while(rs.next()) {
                s.seteid(rs.getString(1));
                s.setday(rs.getString(2));
                s.setetime(rs.getTime(4));
                s.setstime(rs.getTime(3));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();}
    }

    public void makeSchedule
    {

        ////// THINK ABOUT FUNCTIONALITY ///////   -----> LOOK AT HOW MANAGERS MAKE SCHEDULES IN WHENIWORK



    }






