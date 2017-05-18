package com.example.sslab.samplegroupapplication.data;

import java.util.Date;

/**
 * Created by SSLAB on 2017-05-11.
 */

public class Person {

    public enum Sex{
        MALE,FEMALE
    }

    String name;
    Date birthDay;
    Sex gender;
    String emailAddress;
    int age;


    public Person(String name, Date birthDay, Sex gender, String emailAddress, int age) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
