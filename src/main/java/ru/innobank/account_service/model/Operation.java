package ru.innobank.account_service.model;

import java.util.Date;

public class Operation {
    private Date date;
    private String score;
    private int iserID;
    private String description;
    private int sum;
    private int holding_id;

    public Operation(Date date, String score, int iserID, String description, int sum) {
        this.date = date;
        this.score = score;
        this.iserID = iserID;
        this.description = description;
        this.sum = sum;
    }

    public int getHolding_id() {
        return holding_id;
    }

    public void setHolding_id(int holding_id) {
        this.holding_id = holding_id;
    }

    public Operation(Date date, String score, int iserID, String description, int sum, int holding_id) {
        this.date = date;
        this.score = score;
        this.iserID = iserID;
        this.description = description;
        this.sum = sum;
        this.holding_id = holding_id;
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

    public int getIserID() {
        return iserID;
    }

    public void setIserID(int iserID) {
        this.iserID = iserID;
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
