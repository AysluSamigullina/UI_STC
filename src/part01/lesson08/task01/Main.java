package part01.lesson08.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Основной класс
 */

public class Main {

    /**
     * Основной метод
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Kate", 20, 50, false));
        personList.add(new Person("Misha", 39, 70, true));
        personList.add(new Person("Anna", 32, 45, false));
        personList.add(new Person("Ivan", 45, 78, true));

        String fileName = "lesson08";
        Serial serial = new Serial(fileName);

        /**
         * Сериализация в файл
         */
        personList.forEach(person ->
                serial.serialize(person, fileName)
        );

        ArrayList<Person> lines = new ArrayList<>();

        /**
         * Десериализация из файла
         */
        for (int i = 0; i < personList.size(); i++) {              // чем заменить окончание цикла?
            lines.add((Person) serial.deSerialize(fileName));
        }

        lines.forEach(System.out::println);

    }
}
