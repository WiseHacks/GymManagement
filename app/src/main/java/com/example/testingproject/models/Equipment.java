package com.example.testingproject.models;

public class Equipment {
    private int id;
    private String name;
    private String units;
    private String location;

    public Equipment() {
    }

    public Equipment(int id, String name, String units, String location) {
        this.id = id;
        this.name = name;
        this.units = units;
        this.location = location;
    }
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

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Equipment(String name, String units, String location) {
        this.name = name;
        this.units = units;
        this.location = location;
    }
}
