package ru.innobank.account_service.model;

public class Holding {
    private String accountNumber;
    private String id;
    private int holded;

    public Holding() {}

    public Holding(String accountNumber, String id, int holded) {
        this.accountNumber = accountNumber;
        this.id = id;
        this.holded = holded;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHolded() {
        return holded;
    }

    public void setHolded(int holded) {
        this.holded = holded;
    }
}
