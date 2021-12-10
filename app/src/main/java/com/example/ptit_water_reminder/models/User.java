package com.example.ptit_water_reminder.models;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private Integer waterTarget;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getWaterTarget() {
        return waterTarget;
    }

    public void setWaterTarget(Integer waterTarget) {
        this.waterTarget = waterTarget;
    }
}
