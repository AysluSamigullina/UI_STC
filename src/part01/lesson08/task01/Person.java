package part01.lesson08.task01;

import java.io.Serializable;

/**
 * Человек
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private int weight;
    private boolean sex;

    /**
     * Конструктор
     *
     * @param name
     * @param age
     * @param weight
     * @param sex
     */
    public Person(String name, int age, int weight, boolean sex) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
    }

    @Override
    public String toString() {
        if (sex)
            return "Name: " + name + " Age: " + age + " Weight: " + weight + " Sex: " + "male.";
        else
            return "Name: " + name + " Age: " + age + " Weight: " + weight + " Sex: " + "female.";
    }
}
