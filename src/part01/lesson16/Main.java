package part01.lesson16;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/*
Взять за основу ДЗ_13,
покрыть код логированием
в основных блоках try покрыть уровнем INFO
с исключениях catch покрыть уровнем ERROR
настроить конфигурацию логера, что бы все логи записывались в БД, таблица LOGS,
колонки ID, DATE, LOG_LEVEL, MESSAGE, EXCEPTION
 */

/**
 * Основной класс
 */
public class Main {
    /**
     * Логгер
     */
    private static Logger logger = LogManager.getLogger(Main.class);

    /**
     * Основной метод
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        /**
         * Параметризованный SQL-запрос для добавления новой строки в таблицу Users
         */
        final String SQL_INSERT =
                "INSERT INTO users (name, birthday, login_id, city, email, description)" +
                        "VALUES (?,?,?,?,?,?)";
        /**
         * Параметризованный SQL-запрос для выборки полей из таблицы Users
         */
        final String SQL_SELECT = "SELECT * FROM users WHERE login_id = ? AND name = ?";
        /**
         * Параметризованный SQL-запрос для добавления новой строки в таблицу Roles
         */
        final String SQL_INSERT_ROLES = "INSERT INTO roles (name, description) VALUES (?::role_names,?)";
        /**
         * Параметризованный SQL-запрос для добавления новой строки в таблицу user_role
         */
        final String SQL_INSERT_USERROLE = "INSERT INTO user_role (user_id, role_id) VALUES (?,?)";

        ArrayList<User> users = createPersons();
        logger.info("qwe");
        DataBaseHelper dbh = new DataBaseHelper();
        logger.info("работа с БД");
        // параметризованный запрос
        dbh.insertUserWithParametres(SQL_INSERT, users);
        // batch запрос
        dbh.insertUsersWithBatches(SQL_INSERT, users);
        for (User u : dbh.select(SQL_SELECT)) {
            System.out.println(u);
        }
        logger.info("перевод на ручное управление");
        dbh.manualTransaktions();
        logger.info("работа с savepoint");
        Savepoint point = null;
        try {
            dbh.insertRoles(SQL_INSERT_ROLES);
            point = dbh.getConnect().setSavepoint("point1");
            dbh.insertUserRole(SQL_INSERT_USERROLE);
            dbh.getConnect().commit();
        } catch (SQLException e) {
            logger.error("ошибка записи!", e);
            dbh.getConnect().rollback(point);
        }
        logger.info("закрытие связи с БД");
        dbh.close();
    }

    /**
     * Метод для создания списка пользователей
     *
     * @return
     */
    public static ArrayList<User> createPersons() {
        ArrayList<User> arrUsers = new ArrayList<>();
        arrUsers.add(new User(
                "ayslu",
                LocalDate.parse("1987.04.17", DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.US)),
                555, "Ufa", "aa@ya.ru", "qwerty"));
        arrUsers.add(new User("Dinara",
                LocalDate.parse("2015.07.22", DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.US)),
                333, "Kazan", "d@ya.ru", "qaz"));

        return arrUsers;
    }
}
