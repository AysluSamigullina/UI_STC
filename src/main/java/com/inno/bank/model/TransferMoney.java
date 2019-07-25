package com.inno.bank.model;

public class TransferMoney {

    private String accountScore;
    private double sum;
    private boolean typeOfOperation;

    public String getAccountScore() {
        return accountScore;
    }

    public void setAccountScore(String accountScore) {
        this.accountScore = accountScore;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isTypeOfOperation() {
        return typeOfOperation;
    }

    public void setTypeOfOperation(boolean typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }
}
