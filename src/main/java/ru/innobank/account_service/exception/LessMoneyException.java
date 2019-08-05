package ru.innobank.account_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED )
public class LessMoneyException extends RuntimeException {
    public LessMoneyException(int amount) {
        super("Required sum is more than actual balance of account: " + amount);
    }

}
