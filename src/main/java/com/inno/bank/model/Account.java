package com.inno.bank.model;

import javax.persistence.*;
import java.util.Date;


public class Account {

    private int id;
    private String scoreId;
    private int userID;
    private float amount;
    private float holded;
    private Date OpeningDate;
    private Date closingDate;

    public Date getOpeningDate() {
        return OpeningDate;
    }

    public void setOpeningDate(Date openingDate) {
        OpeningDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Account() {}

    public Account(String scoreId, int userID, float amount, float holded) {
        this.scoreId = scoreId;
        this.userID = userID;
        this.amount = amount;
        this.holded = holded;
    }

    public int getId() {
        return id;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getHolded() {
        return holded;
    }

    public void setHolded(float holded) {
        this.holded = holded;
    }
}
