package com.example.tharindu_prasad.parkinghandler;

/**
 * Created by Angel on 2017-12-22.
 */

public class Contact {
    private String Name,Email;


    public Contact(String Name,String Email){
        this.setEmail(Email);
        this.setName(Name);

    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
