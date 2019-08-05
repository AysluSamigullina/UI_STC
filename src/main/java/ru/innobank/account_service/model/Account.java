package ru.innobank.account_service.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.util.Date;


public class Account {

    private int id;
    private String accNumber;
    private long userID;
    private int amount;
    @Min(value = 0, message = "The value must be positive")
    private int holded;
    private Date createdAt;
    private Date closedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public Account() {
    }

    public Account(String accNumber, long userID, int amount, int holded, java.sql.Date openingDate, java.sql.Date closingDate) {
        this.accNumber = accNumber;
        this.userID = userID;
        this.amount = amount;
        this.holded = holded;
        this.createdAt = openingDate;
        this.closedAt = closingDate;
    }

    public int getId() {
        return id;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getUserID() {
        return userID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getHolded() {
        return holded;
    }

    public void setHolded(int holded) {
        this.holded = holded;
    }
}
