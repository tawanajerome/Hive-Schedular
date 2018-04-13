<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import= "man.manager" %>
<%@ page import ="com.util.*" %>
<%@ page import ="java.util.*"%>
<%@ page import ="java.sql.*"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%! ArrayList<schedule> list = new ArrayList();%>
<html>
  <head>
    <title><b></b></title>
  </head>
  <body>
          <%
            try {
              Connection conn = dbconnection.getMySQLConnection();
              PreparedStatement stmt = null;
              ResultSet rs;
              String sql;
              manager m1 = new manager();
              Date date;
              Time time;
              String stime;

              sql = "select s.eid from employee as e, schedule as s where e.eid=s.eid and e.firstname=? and e.lastname=?";
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, request.getParameter("fname"));     //GETS first name of the employese and queris the database
              stmt.setString(2, request.getParameter("lname"));     // to get the empoyees EID
              rs = stmt.executeQuery(sql);

              schedule s = new schedule();
              s.setEid(rs.getInt(1));                   //once the eid is found, a schuedule object is created to pass to the
                                                        //update schedule function in the manager class
              stime = request.getParameter("stime");
              try {
                date = new SimpleDateFormat("hh:mm").parse(stime);      /// the request object returns strings --> must convert to
                time = new Time(date.getTime());                        /// time since stime is of type time

                s.setStime(time);
                stime = request.getParameter("etime");

                date = new SimpleDateFormat("hh:mm").parse(stime);
                time = new Time(date.getTime());

                s.setDay(request.getParameter("day"));
                s.setEtime(time);
              }
              catch(ParseException e){                      // throws an exception for the time conversion
                e.printStackTrace();
              }
              list = m1.UpdateSchedule(s);      //CALLS the updateschedule function to add the employee to the current schedule

              String x;
              sql = "select e.firstname, e.lastname, s.stime, s.etime, s.day from schedule as s, employees as e where e.SSN = s.eid";
              rs = stmt.executeQuery(sql);
              while(rs.next())
              {
                  x = rs.getString(5);
                  switch(x.toLowerCase()) {
                      case "sunday":
                          out.print("<tr><td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3) +" - " + rs.getTime(4)+"</td>");
                          break;
                      case "monday":
                          out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                          break;
                      case "tuesday":
                          out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                          break;
                      case "wednesday":
                          out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                          break;
                      case "thursday":
                          out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                          break;
                      case "friday":
                          out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                          break;
                      case "saturday":
                          out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                  }
              }
              stmt.close();

              }

            catch(SQLException s)
            {s.printStackTrace();}



          %>
        </table>
  </body>
</html>