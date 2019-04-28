package part01.lesson05;

/**
 * Животное
 */
public class Pet {
    /**
     * Уникальный идентификатор
     */
    private int id;

    /**
     * Кличка
     */
    private String petname;

    /**
     * Хозяин
     */
    private Person person;

    /**
     * Вес
     */
    private int weight;

    /**
     * Коснтруктор
     *
     * @param id
     * @param petname
     * @param person
     * @param weight
     */
    public Pet(int id, String petname, Person person, int weight) {
        this.id = id;
        this.petname = petname;
        this.person = person;
        this.weight = weight;
    }

    /**
     * Получает уникальный идентификатор
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Получает кличку
     *
     * @return
     */
    public String getPetname() {
        return petname;
    }

    /**
     * Полуачает вес
     *
     * @return
     */
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return petname + " " + id + " (" + person.getName() + " " + person.getAge() + " " + person.isSex() +") " + weight;
    }

    /**
     * Получает имя хозяина
     *
     * @return
     */
    public String getPersonName() {
        return person.getName();
    }
}
