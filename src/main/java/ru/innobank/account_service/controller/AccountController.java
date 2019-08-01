package ru.innobank.account_service.controller;

import ru.innobank.account_service.model.Account;
import ru.innobank.account_service.model.Holding;
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
     * Пополняет счет
     * @param newTM
     */
    @PutMapping("/accounts/{accountNumber}/refill")
    public void refill(@RequestBody TransferMoney newTM) {
        accountService.refillAccount(newTM);
    }

     /**
     * Закрывает счет
     * @param accountNumber
     * @return
     */
    @PutMapping("/accounts/{accountNumber}/close")
    public void closeAccount(@PathVariable String accountNumber) {
        accountService.closeAccount(accountNumber);
    }

    /**
     * Возвращает баланс
     * @param accountNumber
     * @return
     */
    @GetMapping("/accounts/{accountNumber}/checkBalance")
    public int checkBalance(@PathVariable String accountNumber) {
        return accountService.checkBalance(accountNumber);
    }

    /**
     *  Возвращает список проведенных операций
      * @param accountNumber
     * @return
     */
    @GetMapping("/accounts/{accountNumber}/operations")
    public List<Operation> getOperations(@PathVariable String accountNumber) {
        return accountService.listOfOperations(accountNumber);
    }

    /**
     * Блокирует сумму на счете
     * @param holding
     */
    @PutMapping("/accounts/{accountNumber}/hold")
    public void hold(@RequestBody Holding holding) {
        accountService.holdMoney(holding);
    }

    /**
     * Списывает заблокированную сумму при успешной транзакции
     * @param id
     */
    @PutMapping("/accounts/withdrawholded/{id}")
    public void unhold(@PathVariable int id) {
        accountService.withdrawHolded(id);
    }

    /**
     * Возвращает заблокированную сумму на счет при неуспешной транзакции
     * @param holding_id
     */
    @PutMapping("/accounts/returnholded/{holding_id}")
    public void returnHolded(@PathVariable int holding_id) {
        accountService.returnHolded(holding_id);
    }

}
