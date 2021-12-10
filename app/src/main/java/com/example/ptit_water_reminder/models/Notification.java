package com.example.ptit_water_reminder.models;

import java.util.Date;

public class Notification {
    private int notificationId;
    private int userId;
    private String note;
    private Date time;

    // constructors
    public Notification() {

    }

    public Notification(Date time) {
        this.time = time;
    }

    public Notification(String note, Date time) {
        this.note = note;
        this.time = time;
    }

    public Notification(int notificationId, String note, Date time) {
        this.notificationId = notificationId;
        this.note = note;
        this.time = time;
    }
    // constructors

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
