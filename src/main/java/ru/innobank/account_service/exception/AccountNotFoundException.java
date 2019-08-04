package ru.innobank.account_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String accountNumber) {
        super("Such account not found: " + accountNumber);
    }
}
