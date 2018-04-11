package hivescheduler.beans;

import java.lang.String;

public class employee
{
    // Declaring employee attributes.

    private int ssn;
    private String firstname;
    private String lastname;
    private int jid;
    private int wage;
    private String phone;
    private String email;
    private int lid;


    public employee(){} // Constructor


    public String getEmployeeName(){
        return firstname + lastname;
    }

    public void setEmployeeFname(String name){
        this.firstname = name;
    }

    public void setEmployeeLname(String name){
        this.lastname = name;
    }


    public int getEmployeeJid(){
        return jid;
    }

    public void setEmployeeJid(int loc){
        this.jid= loc;
    }


    public int getEmployeeSSN(){
        return ssn;
    }

    public void setEmployeeSSN(int SSN){
        this.ssn = SSN;
    }


    public int getEmployeeWage(){
        return wage;
    }

    public void setEmployeeWage(int sal){
        this.wage = sal;
    }


    public String getEmployeePhone(){
        return phone;
    }

    public void setEmployeePhone(String num){
        this.phone = num;
    }


    public String getEmployeeEmail(){
        return email;
    }

    public void setEmployeeEmail(String mail){
        this.email = mail;
    }
}
