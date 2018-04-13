
<%@ page import="java.sql.Connection" %>
<%@ page import="com.util.dbconnection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Tawana
  Date: 4/11/2018
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<b> Employees</b> <br>
<table border = "1" cellpadding = "5" cellspacing = "5">
    <tr>
        <th> Name</th>
        <th> Wage </th>
    </tr>
<%
     ///////////OUTPUTS a table of all the employees and their wages
     try
     {
         Connection conn = dbconnection.getMySQLConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs;
         String sql;
         sql = "Select e.firstname,e.lastname,e.wage from schedule as s, employees as e where s.eid = e.SSN";
         rs = stmt.executeQuery(sql);
         while(rs.next())
         { out.print("<tr><td>" + rs.getString(1) + " " + rs.getString(2) + "</td><td>"+ rs.getInt(3) + "</td></tr>");
         }
         stmt.close();
     }
     catch (SQLException e) {
         e.printStackTrace();}
 %>


       <% //OUTPUT THE SCHEDULE AND HAVE IT SO THAT THE MANAGER CAN ADD AN EMPLOYEE %>

    <br><b>Current Schedule</b>
    <br>
    <table border = "1" cellpadding = "5" cellspacing = "5">
        <tr>
            <th>Sunday</th>
            <th> Monday </th>
            <th>Tuesday</th>
            <th>Wednesday</th>
            <th>Thursday</th>
            <th>Friday</th>
            <th>Saturday</th>
        </tr>

    <%
        try{
            String x;
            Connection conn = dbconnection.getMySQLConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql;
            sql = "select e.firstname, e.lastname, s.stime, s.etime, s.day from schedule as s, employees as e where e.SSN = s.eid";
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                x = rs.getString(5);
                switch(x) {
                    case "Sunday":
                        out.print("<tr><td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3) +" - " + rs.getTime(4)+"</td>");
                        break;
                    case "Monday":
                          out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                           break;
                            case "Tuesday":
                                out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                                break;
                            case "Wednesday":
                                out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                                break;
                            case "Thursday":
                                out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                                break;
                            case "Friday":
                                out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                                break;
                            case "Saturday":
                                out.print("<td>" + rs.getString(1) + " " + rs.getString(2) + "<br>" + rs.getTime(3)+ " - " + rs.getTime(4)+"</td>");
                        }
                    }
                    stmt.close();
                }
                catch(SQLException s){
                    s.printStackTrace();
                }
    %>

        <br> <button onclick = "location.href= 'schedule-form.jsp';" > Update Schedule </button>
    </table>

</body>
</html>
