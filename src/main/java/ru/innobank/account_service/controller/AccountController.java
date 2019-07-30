package ru.innobank.account_service.controller;

import ru.innobank.account_service.model.Account;
import ru.innobank.account_service.model.Operation;
import ru.innobank.account_service.model.TransferMoney;
import ru.innobank.account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Выводит всех счетов пользователя
     * @param user
     * @return
     */
    @GetMapping("accounts/{user}/all")
    public List<Account> getUserAccounts(@PathVariable int user) {
        return accountService.getUserAccounts(user);
    }

    /**
     * Добавляет пользователю новый счет
     * @param user
     */
    @PostMapping("/accounts/{user}/add")
    public void createAccount(@PathVariable int user) {
        accountService.createAccount(user);
    }

    /**
     * Пополняет или списывает со счета
     * @param newTM
     */
    @PutMapping("/refill")
    public void refill(@RequestBody TransferMoney newTM) {
        accountService.refillAccount(newTM);
    }

    /**
     * Удаляет счет
     * @param score
     */
    @DeleteMapping("/accounts/{score}/delete")
    public void deleteAccount(@PathVariable String score) {
        accountService.deleteAccount(score);
    }

    /**
     * Закрывает счет
     * @param score
     * @return
     */
    @PutMapping("/accounts/{score}/close")
    public boolean closeAccount(@PathVariable String score) {
        if (accountService.closeAccount(score)) {
            return true;
        } else
            return false;
    }

    /**
     * Возвращает баланс
     * @param score
     * @return
     */
    @GetMapping("/accounts/{score}/checkBalance")
    public int checkBalance(@PathVariable String score) {
        return accountService.checkBalance(score);
    }

    /**
     *  Возвращает список проведенных операций
      * @param score
     * @return
     */
    @GetMapping("/accounts/{score}/operations")
    public List<Operation> getOperations(@PathVariable String score) {
        return accountService.listOfOperations(score);
    }

    @PutMapping("/accounts/{score}/hold")
    public void holdMoney(@RequestBody TransferMoney transferMoney) {
        accountService.holdMoney(transferMoney);
    }


}
