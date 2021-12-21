package com.example.ptit_water_reminder.models;

public class Cup {
    private int cupId;
    //    private int userId;
    private Integer cupAmount;

    // constructors
    public Cup() {

    }

    public Cup(Integer cupAmount) {
        this.cupAmount = cupAmount;
    }

    public Cup(int cupId, Integer cupAmount) {
        this.cupId = cupId;
        this.cupAmount = cupAmount;
    }
    // constructors

    public int getCupId() {
        return cupId;
    }

    public void setCupId(int cupId) {
        this.cupId = cupId;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public Integer getCupAmount() {
        return cupAmount;
    }

    public void setCupAmount(Integer cupAmount) {
        this.cupAmount = cupAmount;
    }
}
