package com.example.ptit_water_reminder.models;

import java.util.Date;

public class WaterLog {
    private int waterLogId;
    private int userId;
    private Integer amount;
    private Date createAt;

    // constructors
    public WaterLog() {

    }

    public WaterLog(Date createAt) {
        this.createAt = createAt;
    }

    public WaterLog(Integer amount, Date createAt) {
        this.amount = amount;
        this.createAt = createAt;
    }

    public WaterLog(int waterLogId, Integer amount, Date createAt) {
        this.waterLogId = waterLogId;
        this.amount = amount;
        this.createAt = createAt;
    }
    // constructors

    public int getWaterLogId() {
        return waterLogId;
    }

    public void setWaterLogId(int waterLogId) {
        this.waterLogId = waterLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
