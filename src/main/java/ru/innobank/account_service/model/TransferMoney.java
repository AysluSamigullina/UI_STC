package ru.innobank.account_service.model;

public class TransferMoney {

    private String accountNumber;
    private int sum;

    public TransferMoney() {}

    public TransferMoney(String accountNumber, int sum) {
        this.accountNumber = accountNumber;
        this.sum = sum;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

}
