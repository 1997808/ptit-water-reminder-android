package com.example.ptit_water_reminder.models;

import java.util.Date;

public class WaterLog {
    private int waterLogId;
    private Integer amount;
    private Date createAt;

    public int getWaterLogId() {
        return waterLogId;
    }

    public void setWaterLogId(int waterLogId) {
        this.waterLogId = waterLogId;
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
