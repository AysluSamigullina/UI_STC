package ru.innobank.account_service.repository;

import ru.innobank.account_service.model.Account;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innobank.account_service.model.Operation;

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
    private java.util.Date d = new java.util.Date();
    private Date dataTime = new java.sql.Date(d.getTime());

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

    private final RowMapper<Operation> RW_journal = (resultSet, i) -> new Operation(
            resultSet.getDate("date"),
            resultSet.getString("score_id"),
            resultSet.getInt("user_id"),
            resultSet.getString("type"),
            resultSet.getInt("summa")
    );

    public List<Account> getUserAccounts(int user_id) {
        log.info("Get all accounts of user");
        String sql = "SELECT * FROM accounts WHERE user_id =" + "'" + user_id + "'";
        return jdbcTemplate.query(sql, ROW_MAPPER);

    }

    public void addAccount(String score, int user_id) {
        log.info("create account");
        jdbcTemplate.update("INSERT into accounts (score_id, user_id, amount, holded, open) values (?, ?, ?, ?, ?)", score, user_id, 0, 0, dataTime );
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, type) VALUES (?, ?, ?, ?)", dataTime, score, user_id, "Открытие счета");
    }

    public Account findAccountByScore(String score) {
        log.info("look for account by score");
        String sql = "SELECT * FROM accounts where score_id = " + "'" + score + "'";
        List<Account> list = jdbcTemplate.query(sql, ROW_MAPPER);
        return list.get(0);
    }

    public void refillAccount(String score, int sum) {
        String SQL = "UPDATE accounts SET amount = ? WHERE score_id = ?";
        jdbcTemplate.update(SQL, sum, score);
    }

    public void writeRefill(String score, int user_id, int sum) {
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, type, summa) VALUES (?, ?, ?, ?, ?)", dataTime, score, user_id, "Пополнение счета", sum);
    }

    public void writewithdraw(String score, int user_id, int sum) {
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, type, summa) VALUES (?, ?, ?, ?, ?)", dataTime, score, user_id, "Списание со счета", sum);
    }

    public void deleteAccount(String score) {
        log.info("deleting account");
        String sql = "DELETE FROM accounts WHERE score_id = "+ "'" + score + "'";
        jdbcTemplate.update(sql);
    }

    public int getBalance(String score) {
        Account account = findAccountByScore(score);
        return account.getAmount() - account.getHolded();

    }

    public void closeAccount(String score) {
        log.info("closing account");
        java.util.Date d = new java.util.Date();
        Date dataTime = new java.sql.Date(d.getTime());
        jdbcTemplate.update("UPDATE accounts SET close = ? WHERE score_id = ?", dataTime, score );
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, type) VALUES (?, ?, ?, ?)", dataTime, score, findAccountByScore(score).getUserID(), "Закрытие счета");
    }

    public List<Operation> listOfOperations(String score) {
        log.info("Get all operations of user");
        String sql = "SELECT * FROM journal WHERE score_id =" + "'" + score + "'";
        return jdbcTemplate.query(sql, RW_journal);
    }

    public void holdMoney(String score, int sum) {

    }
}
