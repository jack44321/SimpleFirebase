package au.edu.anu.cecs.simpleRregistrationAndLogin;

import java.io.Serializable;

public class User implements Serializable {
    String email;
    String username;

    public User(String username, String email) {
        this.email = email;
        this.username = username;
    }

    public User(){

    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String userEmail){
        email = userEmail;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String userName){
        username = userName;
    }
}
