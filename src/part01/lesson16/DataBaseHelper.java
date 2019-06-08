package part01.lesson16;

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
     *
     * @return
     */
    public Connection getConnect() {
        return connect;
    }

    /**
     * Добавляет строку в таблицу users БД, используя параметризованный запрос
     *
     * @param sql
     * @param users
     */
    public void insertUserWithParametres(String sql, ArrayList<User> users) {
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
     *
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
     *
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
     *
     * @param sql
     * @throws SQLException
     */
    public ArrayList<User> select(String sql) throws SQLException {
        try {
            psSelect = connect.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        psSelect.setInt(1, 555);
        psSelect.setString(2, "ayslu");
        ResultSet rs = psSelect.executeQuery();
        ArrayList<User> userArrayList = new ArrayList<>();
        while (rs.next()) {
            userArrayList.add(new User(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(),
                    rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        return userArrayList;
    }

    /**
     * Переводит управление на ручное управление транзакциями
     *
     * @throws SQLException
     */
    public void manualTransaktions() throws SQLException {
        connect.setAutoCommit(false);
    }

    /**
     * Закрывает соединение с базой
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        connect.close();
    }
}
