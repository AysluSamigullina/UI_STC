package part01.lesson16;

import java.time.LocalDate;

/**
 * Класс User для оформления таблицы users
 */
public class User {

    private int id;
    private String name;
    private LocalDate localDate;
    private int loginId;
    private String city;
    private String email;
    private String description;

    public User(String name, LocalDate localDate, int loginId, String city, String email, String description) {
        this.name = name;
        this.localDate = localDate;
        this.loginId = loginId;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    public User(int id, String name, LocalDate localDate, int loginId, String city, String email, String description) {
        this.id = id;
        this.name = name;
        this.localDate = localDate;
        this.loginId = loginId;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public int getLoginId() {
        return loginId;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + localDate + " " + loginId + " " + city + " " + email + " " + description;
    }
}
