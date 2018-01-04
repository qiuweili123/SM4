package com.java.beans;

public class User {

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private int age;

    private String name;

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

    public int compute(int n) {
        return 10 * n;
    }

    public String say(int n, String str) {
        return str + ":" + n;
    }

}  