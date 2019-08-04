package ru.innobank.account_service.model;

import java.util.Date;

public class Operation {
    private Date date;
    private String score;
    private long userID;
    private String description;
    private int sum;
    private String transactionID;

    public Operation(Date date, String score, long userID, String description, int sum) {
        this.date = date;
        this.score = score;
        this.userID = userID;
        this.description = description;
        this.sum = sum;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Operation(Date date, String score, long userID, String description, int sum, String transactionID) {
        this.date = date;
        this.score = score;
        this.userID = this.userID;
        this.description = description;
        this.sum = sum;
        this.transactionID = transactionID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
