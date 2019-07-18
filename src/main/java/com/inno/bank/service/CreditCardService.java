package com.inno.bank.service;

import com.inno.bank.model.CreditCard;
import com.inno.bank.repository.db.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pavel Borodin on 2019-07-17
 */
@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public List<CreditCard> getCreditCardInfoAll() {
        return creditCardRepository.getCreditCardInfoAll();
    }

}
