package com.inno.bank.repository.db;

import com.inno.bank.model.CreditCard;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pavel Borodin on 2019-07-17
 */
@Repository
public class CreditCardRepository {

    private static final Logger log = Logger.getLogger(CreditCardRepository.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CreditCardRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private final String SELECT_ALL_CREDIT_CARD = "SELECT * FROM credit_card";

    private final RowMapper<CreditCard> ROW_MAPPER = (rs, i) -> new CreditCard(
            rs.getLong("ID"),
            rs.getLong("SCORE_ID"),
            rs.getString("DESCRIPTION")
    );

    public List<CreditCard> getCreditCardInfoAll() {
        log.info("Get all credit card");
        return jdbcTemplate.query(
                SELECT_ALL_CREDIT_CARD,
                ROW_MAPPER
        );
    }
}
