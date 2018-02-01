package com.ifunyoung.model;

import java.util.Date;

public class Person {
    private int age;
    private String name;
    private Date birthDay;

    public Person() {
    }

    public Person(int age, String name, Date birthDay) {
        this.age = age;
        this.name = name;
        this.birthDay = birthDay;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
