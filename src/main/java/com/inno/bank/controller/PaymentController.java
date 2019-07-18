package com.inno.bank.controller;

import com.inno.bank.model.CreditCard;
import com.inno.bank.model.DebitCard;
import com.inno.bank.service.CreditCardService;
import com.inno.bank.service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Pavel Borodin on 2019-07-17
 */
@RestController
public class PaymentController {

    private final CreditCardService creditCardService;
    private final DebitCardService debitCardService;

    @Autowired
    public PaymentController(CreditCardService creditCardService, DebitCardService debitCardService) {
        this.creditCardService = creditCardService;
        this.debitCardService = debitCardService;
    }

    @GetMapping("/credit/card/all")
    public List<CreditCard> getCreditCardInfoAll() {
        return creditCardService.getCreditCardInfoAll();
    }

    @GetMapping("/debit/card/all")
    public List<DebitCard> getDebitCardInfoAll() {
        return debitCardService.getDebitCardInfoAll();
    }
}
