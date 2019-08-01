package ru.innobank.account_service.service;

import ru.innobank.account_service.model.Account;
import ru.innobank.account_service.model.Holding;
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

    /**
     * Возвращает список всех счетов пользователя
     * @param userID
     * @return
     */
    public List<Account> getUserAccounts(int userID) {
        return accountRepository.getUserAccounts(userID);
    }

    /**
     * Создает новый счет для пользователя
     * @param userID
     */
    public void createAccount(int userID) {
        String score = generateScore();
        accountRepository.addAccount(score, userID);
    }

    /**
     * Закрывает счет, проверяя условия на то, чтобы счет был пустой и не закрытый до этого
     * @param scoreId
     */
    public void closeAccount(String scoreId) {

        if (accountRepository.getBalance(scoreId) != 0) {
            return;
        }
        if (findAccountByScore(scoreId).getClosedAt() != null) {
            return;
        }
        if (accountRepository.getHoldedBalance(scoreId) != 0) {
            return;
        }
        accountRepository.closeAccount(scoreId);

    }

    /**
     * Возвращает остаток на счете
     * @param scoreId
     * @return
     */
    public int checkBalance(String scoreId) {
        return accountRepository.getBalance(scoreId);
    }

    /**
     * Возвращает список операций, проведенных над счетом
     * @param scoreId
     * @return
     */
    public  List<Operation> listOfOperations (String scoreId) {
        return accountRepository.listOfOperations(scoreId);
    }

    /**
     * Пополняет счет на заданную сумму
     * @param transferMoney
     */
    public void refillAccount(TransferMoney transferMoney) {
        Account account = findAccountByScore(transferMoney.getAccountNumber());
        if (account.getClosedAt() != null ) {
            return;
        }
        int newsum =account.getAmount() + transferMoney.getSum();
        accountRepository.refillAccount(transferMoney.getAccountNumber(), newsum);
    }

    /**
     * Переводит сумму с остатка в поле holded
     * @param score
     * @param sum
     */
    private void withdrawFromMain(String score, int sum) {
        Account account = findAccountByScore(score);
        int newsum =account.getAmount() - sum;
        accountRepository.withdrawForHolding(score, account.getUserID(), newsum, sum);
    }

    /**
     * Находит счет по номеру
     * @param scoreId
     * @return
     */
    private Account findAccountByScore(String scoreId) {

        return accountRepository.findAccountByScore(scoreId);
    }

    /**
     * Блокирует сумму на счете
     * @param holding
     */
    public void holdMoney(Holding holding) {
        //проверка
        if (checkBalance(holding.getAccountNumber()) < holding.getHolded()) {
            return;
        }
        if (findAccountByScore(holding.getAccountNumber()).getClosedAt() != null) {
            return;
        }
        withdrawFromMain(holding.getAccountNumber(), holding.getHolded());
        int firsHoldedSum = accountRepository.getHoldedBalance(holding.getAccountNumber());
        int secondHoldedSum = firsHoldedSum + holding.getHolded();
        accountRepository.refillToHolded(holding.getAccountNumber(), secondHoldedSum, holding.getHolded(), holding.getId());  //записывает в поле holded

    }

    /**
     * Списание заблокированной суммы при успешной оплате
     * @param holding_id
     */
    public void withdrawHolded(int holding_id) {
        accountRepository.withdrawHolded(holding_id);
    }

    /**
     * Возврат заблокированной суммы при неуспешной транзакции
     * @param holding_id
     */
    public void returnHolded(int holding_id) {
        accountRepository.returnHolded(holding_id);
    }


    /**
     * Генерирует номер счета
     * @return
     */
    private String generateScore() {
        String score = "" + 4081781 + System.currentTimeMillis();
        return score;
    }
}
