package part01.lesson15;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/*
1)    Спроектировать базу
-      Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
-      Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
-      Таблица USER_ROLE содержит поля id, user_id, role_id
Типы полей на ваше усмотрению, возможно использование VARCHAR(255)
2)      Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
    a)Используя параметризированный запрос
    b)Используя batch процесс
3)   Сделать параметризированную выборку по login_ID и name одновременно
4)      Перевести connection в ручное управление транзакциями
    a)      Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE) между sql операциями установить логическую точку сохранения(SAVEPOINT)
    б)   Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE) между sql операциями установить точку сохранения (SAVEPOINT A), намеренно ввести некорректные данные на последней операции, что бы транзакция откатилась к логической точке SAVEPOINT A
 */

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        DataBaseHelper dbh = new DataBaseHelper();
        ArrayList<User> users = createPersons();
        final String SQL_INSERT =
                "INSERT INTO users (name, birthday, login_id, city, email, description)" +
                        "VALUES (?,?,?,?,?,?)";
        final String SQL_SELECT = "SELECT * FROM users WHERE login_id = ? AND name = ?";
        final String SQL_INSERT_ROLES = "INSERT INTO roles (name, description) VALUES (?::role_names,?)";
        final String SQL_INSERT_USERROLE = "INSERT INTO user_role (user_id, role_id) VALUES (?,?)";
        // параметризованный запрос
//        dbh.insertUserWithParametres(SQL_INSERT,users);
//        // batch запрос
//        dbh.insertUsersWithBatches(SQL_INSERT, users);

        //Сделать параметризированную выборку по login_ID и name одновременно
        //       dbh.select(SQL_SELECT);

        //Перевести connection в ручное управление транзакциями
        dbh.manualTransaktions();
        System.out.println("Creating savepoint...");
        Savepoint first = dbh.getConnect().setSavepoint("insertingRoleSavePoint");
        try {
            dbh.insertRoles(SQL_INSERT_ROLES);
            dbh.insertUserRole(SQL_INSERT_USERROLE);
            dbh.getConnect().commit();
        } catch (SQLException e) {
            System.out.println("SQLException. Executing rollback to savepoint...");
            dbh.getConnect().rollback(first);
        }
        dbh.close();
    }

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
