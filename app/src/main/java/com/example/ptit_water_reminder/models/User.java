package com.example.ptit_water_reminder.models;

import java.util.Date;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private int waterTarget;

    // constructors
    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, int waterTarget) {
        this.name = name;
        this.waterTarget = waterTarget;
    }

    public User(int userId, String name, int waterTarget) {
        this.userId = userId;
        this.name = name;
        this.waterTarget = waterTarget;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, int waterTarget) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.waterTarget = waterTarget;
    }
    // constructors

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

    public int getWaterTarget() {
        return waterTarget;
    }

    public void setWaterTarget(int waterTarget) {
        this.waterTarget = waterTarget;
    }
}
