package com.example.testingproject.models;

public class Customer {
    private int id;
    private String name;
    private String age;
    private String gender;
    private String email;
    private String phone;
    private String subs;
    private String chosen;
    public int getId() {
        return id;
    }

    public String getSubs() {
        return subs;
    }

    public void setSubs(String subs) {
        this.subs = subs;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Customer(String name, String age, String gender, String email, String phone, String subs) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.subs = subs;
    }

    public Customer() {
    }

    public String getChosen() {
        return chosen;
    }

    public void setChosen(String chosen) {
        this.chosen = chosen;
    }

    public Customer(int id, String name, String age, String gender, String email, String phone, String subs, String chosen) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.subs = subs;
        this.chosen = chosen;
    }

    public Customer(String name, String age, String gender, String email, String phone, String subs, String chosen) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.subs = subs;
        this.chosen = chosen;
    }

    public Customer(int id, String name, String age, String gender, String email, String phone, String subs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.subs = subs;
    }

}
