package com.inno.bank.controller;

import com.inno.bank.model.Account;
import com.inno.bank.model.TransferMoney;
import com.inno.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Pavel Borodin on 2019-07-17
 */
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
           this.accountService = accountService;
    }

    @GetMapping("accounts/{user}/all")
    public List<Account> getUserAccounts(@PathVariable int user) {
        return accountService.getUserAccounts(user);
    }

    @PostMapping("/accounts/{user}/add")     //accounts/ user_id
    public void createAccount(@PathVariable int user) {
        accountService.createAccount(user);

    }

    @PutMapping("/refill") // пополнение счета
    public void refill(@RequestBody TransferMoney newTM) {
        accountService.refillAccount(newTM);
    }

    @DeleteMapping("/accounts/{score}/delete")
    public void deleteAccount(@PathVariable String score) {
        accountService.deleteAccount(score);
    }

    @PutMapping("/accounts/{score}/close")
    public boolean closeAccount(@PathVariable String score) {
        if (accountService.closeAccount(score)) {
            return true;
        }
        else
            return false;
    }

    @GetMapping("/accounts/{score}/checkBalance")
    public int checkBalance(@PathVariable String score) {
         return accountService.checkBalance(score);
    }


}
