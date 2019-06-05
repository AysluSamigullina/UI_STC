package part01.lesson15;

import java.time.LocalDate;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
