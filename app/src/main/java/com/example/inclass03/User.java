package com.example.inclass03;

import java.io.Serializable;

public class User implements Serializable {

    String gender;
    String firstName;
    String lastName;

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    User(String gender, String firstName, String lastName){

        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;

    }
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
