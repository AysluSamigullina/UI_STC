package com.inno.bank.repository.db;

import com.inno.bank.model.DebitCard;
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
public class DebitCardRepository {

    private static final Logger log = Logger.getLogger(DebitCardRepository.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DebitCardRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private final String SELECT_ALL_DEBIT_CARD = "SELECT * FROM debit_card";

    private final RowMapper<DebitCard> ROW_MAPPER = (rs, i) -> new DebitCard(
            rs.getLong("ID"),
            rs.getLong("SCORE_ID"),
            rs.getString("DESCRIPTION")
    );

    public List<DebitCard> getDebitCardInfoAll() {
        log.info("Get all debit card");
        return jdbcTemplate.query(
                SELECT_ALL_DEBIT_CARD,
                ROW_MAPPER
        );
    }
}
