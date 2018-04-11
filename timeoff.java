package hivescheduler.beans;


import java.sql.Date;
import java.sql.Time;


public class timeoff {

    private int eid;
    private Date date;
    private Time stime;
    private Time etime;


    public void setEID(int id){

        this.eid = id;
    }

    public int getEID(){

        return eid;
    }


    public void setDate(Date wkday){

        this.date = wkday;
    }

    public Date getDate(){

        return date;
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
