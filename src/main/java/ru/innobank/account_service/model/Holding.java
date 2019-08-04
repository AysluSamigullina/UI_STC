package ru.innobank.account_service.model;

public class Holding {
    private String accountNumber;
    private String transactionId;
    private int holded;

    public Holding() {}

    public Holding(String accountNumber, String id, int holded) {
        this.accountNumber = accountNumber;
        this.transactionId = id;
        this.holded = holded;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getHolded() {
        return holded;
    }

    public void setHolded(int holded) {
        this.holded = holded;
    }


}
