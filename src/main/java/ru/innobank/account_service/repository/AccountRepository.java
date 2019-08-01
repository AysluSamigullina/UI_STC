package ru.innobank.account_service.repository;

import ru.innobank.account_service.model.Account;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innobank.account_service.model.Holding;
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
            resultSet.getString("description"),
            resultSet.getInt("summa"),
            resultSet.getInt("holding_id")
    );

    public List<Account> getUserAccounts(int user_id) {
        log.info("Get all accounts of user");
        String sql = "SELECT * FROM accounts WHERE user_id =" + "'" + user_id + "'";
        return jdbcTemplate.query(sql, ROW_MAPPER);

    }

    public void addAccount(String score, int user_id) {
        log.info("create account");
        jdbcTemplate.update("INSERT into accounts (score_id, user_id, amount, holded, open) values (?, ?, ?, ?, ?)", score, user_id, 0, 0, dataTime );
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, description) VALUES (?, ?, ?, ?)", dataTime, score, user_id, "Открытие счета");
    }

    public Account findAccountByScore(String score) {
        log.info("look for account by score");
        String sql = "SELECT * FROM accounts where score_id = " + "'" + score + "'";
        List<Account> list = jdbcTemplate.query(sql, ROW_MAPPER);
        return list.get(0);
    }

    public void refillAccount(String score, int sum) {
        jdbcTemplate.update("UPDATE accounts SET amount = ? WHERE score_id = ?", sum, score);
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, description, summa) VALUES (?, ?, ?, ?, ?)", dataTime, score, findAccountByScore(score).getUserID(), "Пополнение счета", sum);
    }

    public void withdrawForHolding(String score, int user_id, int newsum, int firstsum) {
        jdbcTemplate.update("UPDATE accounts SET amount = ? WHERE score_id = ?", newsum, score);
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, description, summa) VALUES (?, ?, ?, ?, ?)", dataTime, score, user_id, "Списание в счет блокировки", firstsum);
    }

    public int getBalance(String score) {
        Account account = findAccountByScore(score);
        return account.getAmount(); // - account.getHolded();
    }

    public int getHoldedBalance(String score) {
        Account account = findAccountByScore(score);
        return account.getHolded();
    }

    public void closeAccount(String score) {
        log.info("closing account");
        java.util.Date d = new java.util.Date();
        Date dataTime = new java.sql.Date(d.getTime());
        jdbcTemplate.update("UPDATE accounts SET close = ? WHERE score_id = ?", dataTime, score );
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, description) VALUES (?, ?, ?, ?)", dataTime, score, findAccountByScore(score).getUserID(), "Закрытие счета");
    }

    public List<Operation> listOfOperations(String score) {
        log.info("Get all operations of user");
        String sql = "SELECT * FROM journal WHERE score_id =" + "'" + score + "'";
        return jdbcTemplate.query(sql, RW_journal);
    }

    /**
     * Переводит сумму с поля amount в поле holded
     * @param holding
     */
    public void refillToHolded(String score, int newsum, int firstsum, String id) {
//        todo id поправить на transactionId и поправить конекты к базе на новые
        log.info("холдирование");
        jdbcTemplate.update("UPDATE accounts SET holded = ? WHERE score_id = ?", newsum, score);
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, description, summa, holding_id) VALUES (?, ?, ?, ?, ?, ?)", dataTime, score, findAccountByScore(score).getUserID(), "Блокировка суммы", firstsum, id);
    }

    /**
     * Уменьшает общую заблокированную сумму на сумму успешной транзакции
     * @param id
     */
    public void withdrawHolded(int id) {
        log.info("списание заблокированной суммы");
        String sql = "SELECT * FROM journal WHERE holding_id =" + "'" + id + "'";
        Operation operation = jdbcTemplate.query(sql, RW_journal).get(0);
        int holdedSum = jdbcTemplate.query(sql, RW_journal).get(0).getSum(); //нашла сумму холда по id
        // уменьшить общую сумму холда
        //найти первоначальную сумму и уменьшить на holdedSum
        int first = getHoldedBalance(operation.getScore());
        int second = first - holdedSum;
        jdbcTemplate.update("UPDATE accounts SET holded = ? WHERE score_id = ?", second, operation.getScore());
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, description, summa) VALUES (?, ?, ?, ?, ?)", dataTime, operation.getScore(), findAccountByScore(operation.getScore()).getUserID(), "Списание заблокированной суммы", holdedSum);

    }

    /**
     * Возвращает заблокированную сумму на основной счет при неудачной транзакции
     */
    public void returnHolded(int id) {
        log.info("возврат заблокированной суммы");
        String sql = "SELECT * FROM journal WHERE holding_id =" + "'" + id + "'";
        Operation operation = jdbcTemplate.query(sql, RW_journal).get(0);
        int holdedSum = operation.getSum(); //нашла сумму холда по id
        // уменьшить общую сумму холда
        //найти первоначальную сумму и уменьшить на holdedSum
        int firstHolded = getHoldedBalance(operation.getScore());
        int secondHolded = firstHolded - holdedSum;

        jdbcTemplate.update("UPDATE accounts SET holded = ? WHERE score_id = ?", secondHolded, operation.getScore());
        jdbcTemplate.update("INSERT into journal (date, score_id, user_id, description, summa, holding_id) VALUES (?, ?, ?, ?, ?, ?)", dataTime, operation.getScore(), findAccountByScore(operation.getScore()).getUserID(), "Возврат заблокированной суммы", holdedSum, id);

        int firstAmount = getBalance(operation.getScore());
        int secondAmount = firstAmount + holdedSum;
        jdbcTemplate.update("UPDATE accounts SET amount = ? WHERE score_id = ?", secondAmount, operation.getScore());
        //jdbcTemplate.update("INSERT into journal (date, score_id, user_id, description, summa) VALUES (?, ?, ?, ?, ?)", dataTime, operation.getScore(), findAccountByScore(operation.getScore()).getUserID(), "Возврат заблокрованной суммы", holdedSum);

    }

}
