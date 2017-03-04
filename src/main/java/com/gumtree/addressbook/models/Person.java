package com.gumtree.addressbook.models;

import java.time.LocalDate;

public class Person {

    private String name;

    private Gender gender;

    private LocalDate dob;

    public Person(String name, Gender gender, LocalDate dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                '}';
    }

}
