package com.inno.bank.service;

import com.inno.bank.model.Account;
import com.inno.bank.model.TransferMoney;
import com.inno.bank.repository.db.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by Pavel Borodin on 2019-07-17
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccountInfoAll() {
        return accountRepository.getAccountInfoAll();
    }

    public void createAccount(int userID) {
        String score = generateScore();
        accountRepository.addAccount(score, userID);
    }

    public static void closeAccount(int scoreId, int userID) {
        // сначала надо найти счет
        // проверить на нулевой баланс
        // закрыть счет
    }

    public static void listOfOperations (int scoreId, int userID) {
        // сначала найдо найти счет из таблицы accounts
        // выбрать из таблицы Operations операции
    }

    public void refillAccount(TransferMoney transferMoney) {   // пополнение или списание
        Account account = findAccountByScore(transferMoney.getAccountScore());
        float oldsum = account.getAmount();
        float newsum;
        if (transferMoney.isTypeOfOperation() == true) {
            newsum = oldsum + transferMoney.getSum();
        }
        else {
            newsum = oldsum - transferMoney.getSum();
        }
        accountRepository.refillAccount(transferMoney.getAccountScore(), newsum);
    }

    private Account findAccountByScore(String scoreId) {

        return accountRepository.findAccountByScore(scoreId);
    }

    private void findAccountByUserId(int userID) {

    }

    private String generateScore() {
        String score = "" + 4081781 + System.currentTimeMillis();
        return score;
    }
}
