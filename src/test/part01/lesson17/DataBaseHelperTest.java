package part01.lesson17;

import org.junit.jupiter.api.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;


import static org.junit.jupiter.api.Assertions.*;

class DataBaseHelperTest {

    private static DataBaseHelper dataBaseHelper;
    private static Connection connection;

    @BeforeAll
    static void initAll() {
        dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.connect();
        connection = dataBaseHelper.getConnect();
        createTables();

    }

    @Test
    void testConnection() {
        assertNotNull(connection);
    }

    @Test
    void insertUserWithParametres() {
        Statement select;
        ResultSet rs;
        String s1, s2 = "";
        ArrayList<User> users1 = new ArrayList<>();
        users1.add(new User(
                "Safia",
                LocalDate.parse("2017.01.23", DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.US)),
                888, "Kazan", "ss@ya.ru", "safika"));

        s1 = users1.get(0).toString();
        dataBaseHelper.insertUserWithParametres("INSERT INTO users (name, birthday, login_id, city, email, description)" +
                "VALUES (?,?,?,?,?,?)", users1);
        try {
            select = connection.createStatement();
            rs = select.executeQuery("SELECT * from users");
            while (rs.next()) {
                s2 = rs.getString(2) + " " + rs.getDate(3).toString() + " " + rs.getInt(4) + " " +
                        rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(s1, s2);
    }

    @Test
    void insertUsersWithBatches() {
        Statement select;
        ResultSet rs;
        String s1, s2 = "";
        ArrayList<User> users2 = new ArrayList<>();
        users2.add(new User(
                "Amina",
                LocalDate.parse("2015.06.10", DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.US)),
                3, "Ufa", "am@ya.ru", "aminka"));

        s1 = users2.get(0).toString();
        dataBaseHelper.insertUsersWithBatches("INSERT INTO users (name, birthday, login_id, city, email, description)" +
                "VALUES (?,?,?,?,?,?)", users2);
        try {
            select = connection.createStatement();
            rs = select.executeQuery("SELECT * from users");
            while (rs.next()) {
                s2 = rs.getString(2) + " " + rs.getDate(3).toString() + " " + rs.getInt(4) + " " +
                        rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(s1, s2);
    }

    @Test
    void insertRoles() {
        Statement statement;
        ResultSet rs;
        String s1 = "", s2 = "";

        dataBaseHelper.insertRoles("INSERT INTO roles (name, description) VALUES (?::role_names,?)");
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * from roles");
            while (rs.next()) {
                s1 = rs.getString(2);
                s2 = rs.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals("Billing", s1);
        assertEquals("iiii", s2);
    }

    @Test
    void insertUserRole() {
        assertThrows(SQLException.class, () -> dataBaseHelper.insertUserRole("INSERT INTO user_role (user_id, role_id) VALUES (?,?)"));
    }

    @Test
    void select() {
        Statement statement;
        int quantity = 0;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (name, birthday, login_id, city, email, description) VALUES ('ayslu', '1987-04-17', '555', 'Ufa', 'a@ya.ru', 'aaa')");
            quantity = dataBaseHelper.select("SELECT * FROM users WHERE login_id = ? AND name = ?");
            statement.executeQuery("SELECT * FROM users WHERE login_id = '555' AND name = 'ayslu'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1, quantity);
    }

    @Test
    void manualTransaktions() {
        boolean flag = true;
        dataBaseHelper.manualTransaktions();
        try {
            flag = connection.getAutoCommit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertFalse(flag);
    }

    @AfterAll
    static void dropTables() {
        Statement statement;
        try {
            statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE user_role");
            statement.executeUpdate("DROP TABLE roles");
            statement.executeUpdate("DROP TYPE role_names");
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables() {
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE users (id serial PRIMARY KEY, name VARCHAR(25), birthday Date, login_id int, city varchar(30), email varchar(30), description VARCHAR(255))");
            statement.executeUpdate("CREATE TYPE role_names as ENUM ('Administration', 'Clients', 'Billing')");
            statement.executeUpdate("CREATE TABLE roles\n" +
                    "(\n" +
                    "    id          serial PRIMARY KEY,\n" +
                    "    name        role_names,\n" +
                    "    description VARCHAR(255)\n" +
                    ")");
            statement.executeUpdate("CREATE TABLE user_role\n" +
                    "(\n" +
                    "    id      SERIAL,\n" +
                    "    user_id INTEGER REFERENCES users (id),\n" +
                    "    role_id INTEGER REFERENCES roles (id),\n" +
                    "    PRIMARY KEY (id, user_id, role_id)\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}