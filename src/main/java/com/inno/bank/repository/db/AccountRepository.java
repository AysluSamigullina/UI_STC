package com.inno.bank.repository.db;

import com.inno.bank.model.Account;

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
public class AccountRepository {

    private static final Logger log = Logger.getLogger(AccountRepository.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AccountRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private final String SELECT_ALL_ACCOUNT = "SELECT * FROM accounts";


    private final RowMapper<Account> ROW_MAPPER = (rs, i) -> new Account(
            rs.getString("score_id"),
            rs.getInt("user_id"),
            rs.getFloat("amount"),
            rs.getFloat("holded")
    );

    public List<Account> getAccountInfoAll() {
        log.info("Get all accounts");
        return jdbcTemplate.query(
                SELECT_ALL_ACCOUNT, ROW_MAPPER);

    }

    public void addAccount(String score, int user_id) {
        log.info("create account");
        jdbcTemplate.update("INSERT into accounts (score_id, user_id, amount, holded) values (?, ?, ?, ?)", score, user_id, 0, 0 );

    }

    public Account findAccountByScore(String score) {   // надо найти строку и замапить в объект
        log.info("look for account by score");
        String sql = "SELECT * FROM accounts where score_id = " + "'" + score + "'";
        List<Account> list = jdbcTemplate.query(sql, ROW_MAPPER);
        return list.get(0);
    }

    public void refillAccount(String score, double sum) {
        String SQL = "UPDATE accounts SET amount = ? WHERE score_id = ?";
        jdbcTemplate.update(SQL, sum, score);
    }
}
