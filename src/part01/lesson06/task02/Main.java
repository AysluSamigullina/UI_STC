package part01.lesson06.task02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        String s = "";
        Words words = new Words();

        //words.randomWord(random.nextInt(15));
        System.out.println("это случайное слово " + words.randomWord(random.nextInt(15)));

        HashSet<String> library = words.createWordsArray(20);  // надо потом задать рандомный объем
        System.out.println("это словарь");
        library.forEach(System.out::println);

        System.out.println("это случайная фраза " + words.randomPhrase(random.nextInt(15)));

    }
}
