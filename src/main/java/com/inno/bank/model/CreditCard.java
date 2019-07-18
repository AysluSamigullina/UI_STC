package com.inno.bank.model;

/**
 * Created by Pavel Borodin on 2019-07-17
 */
public class CreditCard {

    private final long id;
    private final long scoreId;
    private final String description;

    public CreditCard(long id, long scoreId, String description) {
        this.id = id;
        this.scoreId = scoreId;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public long getScoreId() {
        return scoreId;
    }

    public String getDescription() {
        return description;
    }
}
