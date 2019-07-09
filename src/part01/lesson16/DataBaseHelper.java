package part01.lesson16;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    /**
     * Логгер
     */
    private static Logger logger = LogManager.getLogger(Main.class);

    /**
     * Перечисление значений поля name из таблицы roles
     */
    public enum Role {
        Administration,
        Clients,
        Billing;
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
            logger.info("Установка соединенния с БД");
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружен");
            logger.error("Файл для записи не обнаружен", e);
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Не установлено соединение с БД", e);
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
                logger.info("Добавлена строка в Users");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Ошибка добавления строки в Users");
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
            logger.info("Добавление строки в Users с batch запросом");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Ошибка добавления строки с batch запросом");
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
            logger.info("Добавление строки в таблицу roles БД");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Ошибка добавления строку в таблицу roles БД");
        }
    }

    /**
     * Добавляет строку в таблицу user_role БД, используя параметризованный запрос
     * @param sql
     * @throws SQLException
     */
    public void insertUserRole(String sql) {
        try {
            psInsert = connect.prepareStatement(sql);
            psInsert.setInt(1, 18);
            psInsert.setString(3, "9");  // умышленно допущена ошибка для срабатывания savepoint
            psInsert.executeUpdate();
            logger.info("Добавление строки в таблицу user_role");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Ошибка добавления строки в таблицу user_role");
        }
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
            logger.info("Выбор строк из таблицы Users");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Ошибка выбора строк из таблицы Users");
        }

    }

    /**
     * Переводит управление на ручное управление транзакциями
     * @throws SQLException
     */
    public void manualTransaktions() {
        try {
            connect.setAutoCommit(false);
            logger.info("Перевод на ручное управление транзакциями");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Ошиька перевода на ручное управление");
        }
    }

    /**
     * Закрывает соединение с базой
     * @throws SQLException
     */
    public void close() {
        try {
            connect.close();
            logger.info("Закрытие соединения с БД");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Ошибка закрытия соединения");
        }
    }
}
