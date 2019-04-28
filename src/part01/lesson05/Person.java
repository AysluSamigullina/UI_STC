package part01.lesson05;

/**
 * Оформление класса Person, хозяев
 */
public class Person {

    private String name;
    private int age;
    private boolean sex;

    public Person(String name, int age, boolean sex) {
        this.name = name;
        this.age  = age;
        this.sex  = sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isSex() {
        return sex;
    }
}
