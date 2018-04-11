package com.util;
import java.sql.*;


public class schedule {
    int eid;
    String day;
    Time stime;
    Time etime;

    public void setDay(String day) {
        this.day = day;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setStime(Time stime) {
        this.stime = stime;
    }

    public void setEtime(Time etime) {
        this.etime = etime;
    }

    public String getDay() {
        return day;
    }

    public int getEid() {
        return eid;
    }

    public Time getEtime() {
        return etime;
    }

    public Time getStime() {
        return stime;
    }
}
