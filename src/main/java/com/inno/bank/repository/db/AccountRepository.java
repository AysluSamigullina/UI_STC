package com.inno.bank.repository.db;

import com.inno.bank.model.Account;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
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

    private final RowMapper<Account> ROW_MAPPER = (rs, i) -> new Account(
            rs.getString("score_id"),
            rs.getInt("user_id"),
            rs.getInt("amount"),
            rs.getInt("holded"),
            rs.getDate("open"),
            rs.getDate("close")
    );

    public List<Account> getUserAccounts(int user_id) {
        log.info("Get all accounts of user");
        String sql = "SELECT * FROM accounts WHERE user_id =" + "'" + user_id + "'";
        return jdbcTemplate.query(sql, ROW_MAPPER);

    }

    public void addAccount(String score, int user_id) {
        log.info("create account");
        java.util.Date d = new java.util.Date();
        Date dataTime = new java.sql.Date(d.getTime());

        jdbcTemplate.update("INSERT into accounts (score_id, user_id, amount, holded, open) values (?, ?, ?, ?, ?)", score, user_id, 0, 0, dataTime );

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

    public void deleteAccount(String score) {
        log.info("deleting account");
        String sql = "DELETE FROM accounts WHERE score_id = "+ "'" + score + "'";
        jdbcTemplate.update(sql);
    }

    public int getBalance(String score) {
        Account account = findAccountByScore(score);
        return account.getAmount();

    }

    public void closeAccount(String score) {
        log.info("closing account");
        java.util.Date d = new java.util.Date();
        Date dataTime = new java.sql.Date(d.getTime());
        jdbcTemplate.update("UPDATE accounts SET close = ? WHERE score_id = ?", dataTime, score );
    }
}
