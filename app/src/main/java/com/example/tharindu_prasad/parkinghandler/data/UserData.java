package com.example.tharindu_prasad.parkinghandler.data;

/**
 * Created by nipunu on 1/17/2018.
 */

public class UserData {
    private String name;
    private String email;
    private static UserData userData;

    private UserData(){

    }

    public static UserData getSharedInstance(){
        if(userData == null){
            userData = new UserData();
        }
        return userData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
