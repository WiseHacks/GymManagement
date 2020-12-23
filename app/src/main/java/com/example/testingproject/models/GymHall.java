package com.example.testingproject.models;

public class GymHall {
    private int id;
    private String name;
    private String hrs;

    public int getId() {
        return id;
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

    public String getHrs() {
        return hrs;
    }

    public void setHrs(String hrs) {
        this.hrs = hrs;
    }

    public GymHall() {
    }

    public GymHall(String name, String hrs) {
        this.name = name;
        this.hrs = hrs;
    }

    public GymHall(int id, String name, String hrs) {
        this.id = id;
        this.name = name;
        this.hrs = hrs;
    }

    public GymHall(int id) {
        this.id = id;
    }
}
