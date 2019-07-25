package com.inno.bank.model;

public class TransferMoney {

    private String accountScore;
    private float sum;
    private boolean typeOfOperation;

    public String getAccountScore() {
        return accountScore;
    }

    public void setAccountScore(String accountScore) {
        this.accountScore = accountScore;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public boolean isTypeOfOperation() {
        return typeOfOperation;
    }

    public void setTypeOfOperation(boolean typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }
}
