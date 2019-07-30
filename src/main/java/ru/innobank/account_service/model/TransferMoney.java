package ru.innobank.account_service.model;

public class TransferMoney {

    private String accountScore;
    private int sum;
    private boolean typeOfOperation;

    public String getAccountScore() {
        return accountScore;
    }

    public void setAccountScore(String accountScore) {
        this.accountScore = accountScore;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public boolean isTypeOfOperation() {
        return typeOfOperation;
    }

    public void setTypeOfOperation(boolean typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }
}
