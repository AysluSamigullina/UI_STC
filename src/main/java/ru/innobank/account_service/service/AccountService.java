package ru.innobank.account_service.service;

import ru.innobank.account_service.model.Account;
import ru.innobank.account_service.model.Operation;
import ru.innobank.account_service.model.TransferMoney;
import ru.innobank.account_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getUserAccounts(int userID) {
        return accountRepository.getUserAccounts(userID);
    }

    public void createAccount(int userID) {
        String score = generateScore();
        accountRepository.addAccount(score, userID);
    }

    public boolean closeAccount(String scoreId) {

        if (accountRepository.getBalance(scoreId) == 0 & findAccountByScore(scoreId).getClosedAt() == null) {
            accountRepository.closeAccount(scoreId);
            return true;
        } else {
            return false;
        }
    }

    public int checkBalance(String scoreId) {
        return accountRepository.getBalance(scoreId);
    }

    public  List<Operation> listOfOperations (String scoreId) {
        return accountRepository.listOfOperations(scoreId);
    }

    public void refillAccount(TransferMoney transferMoney) {   // пополнение или списание
        Account account = findAccountByScore(transferMoney.getAccountScore());
        if (account.getClosedAt() != null ) {
            return;
        }
        int oldsum = account.getAmount();
        int newsum;
        if (transferMoney.isTypeOfOperation() == true) {
            newsum = oldsum + transferMoney.getSum();
        }
        else {
            newsum = oldsum - transferMoney.getSum();
        }

        accountRepository.refillAccount(transferMoney.getAccountScore(), newsum);
        if (transferMoney.isTypeOfOperation() == true) {
            accountRepository.writeRefill(account.getScoreId(), account.getUserID(), transferMoney.getSum());
        } else
            accountRepository.writewithdraw(account.getScoreId(), account.getUserID(), transferMoney.getSum());
    }

    private Account findAccountByScore(String scoreId) {

        return accountRepository.findAccountByScore(scoreId);
    }

    private String generateScore() {
        String score = "" + 4081781 + System.currentTimeMillis();
        return score;
    }

    public void deleteAccount(String score) {
        accountRepository.deleteAccount(score);
    }

    public void holdMoney(TransferMoney transferMoney) {
        accountRepository.holdMoney(transferMoney.getAccountScore(), transferMoney.getSum());
    }
}
