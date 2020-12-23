package com.example.testingproject.models;

public class Trainer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String assigned;

    public Trainer(String name, String email, String phone, String assigned) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.assigned = assigned;
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

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public Trainer() {
    }

    public Trainer(int id, String name, String email, String phone, String assigned) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.assigned = assigned;
    }
}
