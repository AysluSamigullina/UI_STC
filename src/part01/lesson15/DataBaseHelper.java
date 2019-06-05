package part01.lesson15;

import java.sql.*;
import java.util.ArrayList;

/**
 * Класс для работы с БД
 */
public class DataBaseHelper {

    private Connection connect;
    private PreparedStatement psInsert;
    private PreparedStatement psSelect;

    /**
     * Перечисление значений поля name из таблицы roles
     */
    public enum Role {
        Administration,
        Clients,
        Billing;
    }

    /**
     * Конструктор
     *
     * @throws SQLException
     */
    public DataBaseHelper() throws SQLException {
        connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test",
                "postgres", "12345");
    }

    /**
     * Метод геттер для получения Connection
     * @return
     */
    public Connection getConnect() {
        return connect;
    }

    /**
     * Добавляет строку в таблицу users БД, используя параметризованный запрос
     * @param sql
     * @param users
     * @throws SQLException
     */
    public void insertUserWithParametres(String sql, ArrayList<User> users) throws SQLException {
        try {
            psInsert = connect.prepareStatement(sql);
            for (User u : users) {
                psInsert.setString(1, u.getName());
                psInsert.setObject(2, u.getLocalDate());
                psInsert.setInt(3, u.getLoginId());
                psInsert.setString(4, u.getCity());
                psInsert.setString(5, u.getEmail());
                psInsert.setString(6, u.getDescription());
                psInsert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавляет строку в таблицу users БД, используя параметризованный batch запрос
     *
     * @param sql
     * @param users
     * @throws SQLException
     */
    public void insertUsersWithBatches(String sql, ArrayList<User> users) throws SQLException {
        psInsert = connect.prepareStatement(sql);
        final int batchSize = 1000;
        int count = 0;
        for (User u : users) {
            psInsert.setString(1, u.getName());
            psInsert.setObject(2, u.getLocalDate());
            psInsert.setInt(3, u.getLoginId());
            psInsert.setString(4, u.getCity());
            psInsert.setString(5, u.getEmail());
            psInsert.setString(6, u.getDescription());
            psInsert.addBatch();
            if (++count % batchSize == 0) {
                psInsert.executeBatch();
            }
        }
        psInsert.executeBatch();
    }

    /**
     * Добавляет строку в таблицу roles БД, используя параметризованный запрос
     * @param sql
     */
    public void insertRoles(String sql) {
        try {
            psInsert = connect.prepareStatement(sql);
            psInsert.setString(1, Role.Billing.toString());
            psInsert.setString(2, "iiii");
            psInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавляет строку в таблицу user_role БД, используя параметризованный запрос
     * @param sql
     * @throws SQLException
     */
    public void insertUserRole(String sql) throws SQLException {
        psInsert = connect.prepareStatement(sql);
        psInsert.setInt(1, 18);
        psInsert.setString(3, "9");  // умышленно допущена ошибка для срабатывания savepoint
        psInsert.executeUpdate();
    }

    /**
     * Выбирает строки из таблицы users, используя параметризованный запрос
     * @param sql
     * @throws SQLException
     */
    public void select(String sql) throws SQLException {
        try {
            psSelect = connect.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        psSelect.setInt(1, 555);
        psSelect.setString(2, "ayslu");
        ResultSet rs = psSelect.executeQuery();
        while (rs.next()) {
            int id_ = rs.getInt(1);
            String name_ = rs.getString(2);
            String birthday_ = rs.getDate(3).toString();
            int loginID_ = rs.getInt(4);
            String city_ = rs.getString(5);
            String email_ = rs.getString(6);
            String description_ = rs.getString(7);
            System.out.println(id_ + " " + name_ + " " + birthday_ + " " + loginID_ + " " + city_ + " " + email_ + " " + description_);
        }
    }

    /**
     * Переводит управление на ручное управление транзакциями
     * @throws SQLException
     */
    public void manualTransaktions() throws SQLException {
        connect.setAutoCommit(false);
    }

    /**
     * Закрывает соединение с базой
     * @throws SQLException
     */
    public void close() throws SQLException {
        connect.close();
    }
}
