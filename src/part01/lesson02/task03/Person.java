package part01.lesson02.task03;

/**
 * Сущность Person
 */
public class Person implements Comparable<Person> {

    /**
     * Возраст
     */
    private int age;

    /**
     * Пол
     */
    private sexType sex;

    /**
     * Имя
     */
    private String name;

    enum sexType {MAN, WOMAN}

    @Override
    public int compareTo(Person that) {
        if (this.getSex() == sexType.WOMAN && that.getSex() == sexType.MAN)
            return 1;
        if (this.getAge() > that.getAge() && this.getSex().equals(that.getSex()))
            return 1;
        if (this.getSex().equals(that.getSex()) && this.getAge() == that.getAge() && this.getName().compareTo(that.getName()) > 0)
            return 1;
        else return -1;
    }

    @Override
    public boolean equals(Object obj) {
        Person that = (Person) obj;
        return (this.getAge() == that.getAge() && this.getName() == that.getName() && this.getSex() == that.getSex());
    }

    @Override
    public String toString() {
        return getName() + " " + getAge() + " " + getSex();
    }

    public Person(int age, sexType sex, String name) {
        this.setAge(age);
        this.sex  = sex;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if ((age < 100) & (age > 0)) {
            this.age = age;
        }
    }

    public sexType getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }
}
