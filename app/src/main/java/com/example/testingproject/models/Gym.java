package com.example.testingproject.models;

import android.content.SharedPreferences;

public class Gym {
    private int id;
    private String name;
    private String address;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gym(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gym(String name) {
        this.name = name;
    }

    public Gym() {
    }

    public Gym(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
