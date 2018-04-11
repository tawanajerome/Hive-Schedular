package hivescheduler.beans;


import java.lang.String;
import java.sql.Time;


public class schedule {

    private int eid;
    private String day;
    private Time stime;
    private Time etime;


    public void setEID(int id){

        this.eid = id;
    }

    public int getEID(){

        return eid;
    }


    public void setDay(String wkday){

        this.day = wkday;
    }

    public String getDay(){

        return day;
    }


    public void setStime(Time start){

        this.stime = start;
    }

    public Time getStime(){

        return stime;
    }


    public void setEtime(Time end){

        this.etime = end;
    }

    public Time getEtime(){

        return etime;
    }
}
