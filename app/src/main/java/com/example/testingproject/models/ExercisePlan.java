package com.example.testingproject.models;

public class ExercisePlan {
    private int id;
    private String name;
    private String reqEquip;
    private String description;
    private String submitedBy;

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

    public String getReqEquip() {
        return reqEquip;
    }

    public void setReqEquip(String reqEquip) {
        this.reqEquip = reqEquip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubmitedBy() {
        return submitedBy;
    }

    public void setSubmitedBy(String submitedBy) {
        this.submitedBy = submitedBy;
    }

    public ExercisePlan() {
    }

    public ExercisePlan(int id, String name, String reqEquip, String description, String submitedBy) {
        this.id = id;
        this.name = name;
        this.reqEquip = reqEquip;
        this.description = description;
        this.submitedBy = submitedBy;
    }

    public ExercisePlan(String name, String reqEquip, String description, String submitedBy) {
        this.name = name;
        this.reqEquip = reqEquip;
        this.description = description;
        this.submitedBy = submitedBy;
    }
}
