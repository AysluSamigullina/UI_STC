package com.inno.bank.service;

import com.inno.bank.model.DebitCard;
import com.inno.bank.repository.db.DebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pavel Borodin on 2019-07-17
 */
@Service
public class DebitCardService {

    private final DebitCardRepository debitCardRepository;

    @Autowired
    public DebitCardService(DebitCardRepository debitCardRepository) {
        this.debitCardRepository = debitCardRepository;
    }

    public List<DebitCard> getDebitCardInfoAll() {
        return debitCardRepository.getDebitCardInfoAll();
    }
}
