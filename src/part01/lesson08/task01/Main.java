package part01.lesson08.task01;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Person person1 = new Person("Kate", 20, 50, false);
        Person person2 = new Person("Misha", 39, 70, true);
        Person person3 = new Person("Anna", 32, 45, false);
        Person person4 = new Person("Ivan", 45, 78, true);

        String fileName = "lesson08";

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(person1);
            objectOutputStream.writeObject(person2);
            objectOutputStream.writeObject(person3);
            objectOutputStream.writeObject(person4);

            objectOutputStream.writeObject(new Person("Tom", 34, 67, true));
            person1.setName("Rita");
            objectOutputStream.reset();
            objectOutputStream.writeObject(person1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Person> lines = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            for (int i = 0; i < 5; i++) {
                lines.add((Person) objectInputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        lines.forEach(System.out::println);

    }
}
