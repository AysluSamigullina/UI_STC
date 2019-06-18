package part01.lesson16;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Класс для работы с БД
 */
public class DataBaseHelper {

    private Connection connect;
    private PreparedStatement psInsert;
    private PreparedStatement psSelect;
    private static final String PATH_TO_PROPERTIES = "resources/lesson17/conf.properties";
//    private final String USERNAME = "postgres";
//    private final String PASSWORD = "12345";

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
    public DataBaseHelper() {
//        connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test",
//                USERNAME, PASSWORD);
    }

    public void connect() {
        FileInputStream fileInputStream = null;
        Properties prop = new Properties();
        try {
            //обращаемся к файлу и получаем данные
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            String url = prop.getProperty("url");
            String login = prop.getProperty("username");
            String password = prop.getProperty("password");

            connect = DriverManager.getConnection(url, login, password);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружен");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    public void insertUsersWithBatches(String sql, ArrayList<User> users) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void select(String sql) {
        try {
            psSelect = connect.prepareStatement(sql);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Переводит управление на ручное управление транзакциями
     * @throws SQLException
     */
    public void manualTransaktions() {
        try {
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Закрывает соединение с базой
     * @throws SQLException
     */
    public void close() {
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
