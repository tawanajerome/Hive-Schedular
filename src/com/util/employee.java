package com.util;

public class employee {

    int SSN;
    String firstname;
    String lastname;
    int jid;
    int wage;
    String phone;
    String email;
    int lid;

    public void setFirstname(String x)
    {this.firstname = x;}

    public void setLastname(String x)
    {this.lastname = x;}

    public void setJid(int x)
    {this.jid= x;}

    public void setSSN(int SSN) {
        this.SSN = SSN; }
    public void setWage(int wage)
    {this.wage = wage;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getSSN() {
        return SSN;
    }

    public int getJid() {
        return jid;
    }

    public int getLid() {
        return lid;
    }
    public int getWage() {
        return wage;
    }
     public String getLastname()
     {return lastname;}
     public String getPhone()
     {return phone;}

}
