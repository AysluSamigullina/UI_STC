package com.inno.bank.service;

import com.inno.bank.model.Account;
import com.inno.bank.model.TransferMoney;
import com.inno.bank.repository.db.AccountRepository;
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
        // проверить баланс
        if (accountRepository.getBalance(scoreId) == 0) {
            accountRepository.closeAccount(scoreId);
            return true;
        } else {
            return false;
        }
    }

    public int checkBalance(String scoreId) {
        return accountRepository.getBalance(scoreId);
    }
    public static void listOfOperations (int scoreId, int userID) {
        // сначала найдо найти счет из таблицы accounts
        // выбрать из таблицы Operations операции
    }

    public void refillAccount(TransferMoney transferMoney) {   // пополнение или списание
        Account account = findAccountByScore(transferMoney.getAccountScore());
        double oldsum = account.getAmount();
        double newsum = 0.0d;
        if (transferMoney.isTypeOfOperation() == true) {
            newsum = oldsum + transferMoney.getSum();
        }
        else {
            newsum = oldsum - transferMoney.getSum();
        }

        accountRepository.refillAccount(transferMoney.getAccountScore(), newsum);  //double roundOff = Math.round(a * 100.0) / 100.0;
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
}
