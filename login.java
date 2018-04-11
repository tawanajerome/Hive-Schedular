package hivescheduler.beans;

import java.lang.String;

public class login {

    private String email;
    private String password;
    private int jid;

    public login(){}

    public String getEmail(){

        return email;
    }

    public void setEmail(String email){

        this.email = email;
    }


    public String getPassword(){

        return password;
    }

    public void setPassword(String password){

        this.password = password;
    }

    public int getJid(){

        return jid;
    }

    public void setJid(int jid){

        this.jid = jid;
    }
}
