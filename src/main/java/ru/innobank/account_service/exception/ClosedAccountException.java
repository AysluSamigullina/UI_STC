package ru.innobank.account_service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClosedAccountException extends RuntimeException {
    public ClosedAccountException(String accountNumber) {
        super("This account is already closed: " + accountNumber);
    }
}
