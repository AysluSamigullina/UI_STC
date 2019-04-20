package part01.lesson02.task03;

import java.util.*;

/**
 * Основной класс
 */
public class Main {

    /**
     * Выполняет скрипт
     *
     * @param args
     *
     * @throws TwinPersonException
     */
    public static void main(String[] args) throws TwinPersonException {

        ArrayList<Person> personArray = createPersonArray(10000);

        validatePersonArray(personArray);

        ArrayList<Person> personArray1 = new ArrayList<Person>(personArray);
        long startTime1 = System.currentTimeMillis();

        Sort1 sort1 = new Sort1();
        sort1.sort(personArray);

        long stopTime1    = System.currentTimeMillis();
        long elapsedTime1 = stopTime1 - startTime1;
        System.out.println("Время работы метода №1: " + elapsedTime1 + " миллисекунд.");

        for (Person value : personArray) {
            System.out.println(value.getSex() + " " + value.getAge() + "  " + value.getName());
        }

        long startTime2 = System.currentTimeMillis();

        Sort2 sort2 = new Sort2();
        sort2.sort(personArray1);

        long stopTime2    = System.currentTimeMillis();
        long elapsedTime2 = stopTime2 - startTime2;
        System.out.println("Время работы метода №2: " + elapsedTime2 + " миллисекунд.");

        for (Person value : personArray1) {
            System.out.println(value.getSex() + " " + value.getAge() + "  " + value.getName());
        }
    }

    /**
     * Создаёт массив
     *
     * @param quantity
     *
     * @return
     */
    private static ArrayList<Person> createPersonArray(int quantity) {
        ArrayList<Person> personArray = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Random random      = new Random();
            String name        = randomAlphaNumeric(random.nextInt(5)+ 3);
            int age            = random.nextInt(99) + 1;
            int sex1           = random.nextInt(2);
            Person.sexType sex = Person.sexType.WOMAN;
            if (sex1 == 1)
                sex = Person.sexType.MAN;

            Person person = new Person(age, sex, name);
            personArray.add(person);
        }

        return personArray;
    }

    /**
     * Проверяет массив
     *
     * @param arrayList
     *
     * @throws TwinPersonException
     */
    private static void validatePersonArray(ArrayList<Person> arrayList) throws TwinPersonException {
        ArrayList<Person> tempArray = new ArrayList<>();

        for (Person value : arrayList) {
            if (tempArray.contains(value)) {
                throw new TwinPersonException(value.toString());
            }

            tempArray.add(value);
        }
    }

    /**
     * Параметра рандомной строки
     */
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Генерирует рандомную строку
     *
     * @param count
     *
     * @return
     */
    private static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
