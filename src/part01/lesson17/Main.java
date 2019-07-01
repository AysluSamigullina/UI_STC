package part01.lesson17;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/*
Взять за основу ДЗ_13. Написать тест на CRUD операции
Инициализацию соединение с БД произвести один раз перед началом тестов,
после завершения всех тестов, закрыть соединие с БД
Подготовку данных для CRUD операций производить в методе Init используя @Before
Задание 2
Используя Spy и Mockito создать заглушки для интерфейса JDBC
 */

/**
 * Основной класс
 */
public class Main {
    /**
     * Основной метод
     *
     * @param args
     * @throws SQLException
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException, IOException {
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
        DataBaseHelper dbh = new DataBaseHelper();
        // сюда надо добавить тест, есть ли связь с бд
        dbh.connect();    //или try/catch?
        // параметризованный запрос
        dbh.insertUserWithParametres(SQL_INSERT, users);
        // batch запрос
        dbh.insertUsersWithBatches(SQL_INSERT, users);
        dbh.select(SQL_SELECT);
        dbh.manualTransactions();
        System.out.println("Creating savepoint...");
        Savepoint point = null;
        try {
            dbh.insertRoles(SQL_INSERT_ROLES);
            point = dbh.getConnect().setSavepoint("point1");
            dbh.insertUserRole(SQL_INSERT_USERROLE);
            dbh.getConnect().commit();
        } catch (SQLException e) {
            System.out.println("SQLException. Executing rollback to savepoint...");
            try {
                dbh.getConnect().rollback(point);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
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
