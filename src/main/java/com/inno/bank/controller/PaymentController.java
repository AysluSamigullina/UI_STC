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
public class PaymentController {

    private final AccountService accountService;

    @Autowired
    public PaymentController(AccountService accountService) {
           this.accountService = accountService;
    }

    @GetMapping("accounts/all")
    public List<Account> getAccountInfoAll() {
        return accountService.getAccountInfoAll();
    }

    //@GetMapping("accounts/add")
    @PutMapping("accounts/add/{user_id}")
    public void addAccount(@PathVariable int user_id) {
        accountService.createAccount(user_id);
        getAccountInfoAll();
    }

    @PostMapping("/accounts/refill") // пополнение счета
    public void refill(@RequestBody TransferMoney newTM) {
        accountService.refillAccount(newTM);
    }


}
