package part01.lesson11;

import java.io.IOException;
import java.util.*;


/**
 * Основной класс. Скопирован из 4 урока для использования стримов и лямбд
 */
public class Main {

    /**
     * Основной метод
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Integer[] numbers = new Integer[20];

        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(30);
        }

        System.out.println("Первоначальный список");
        Arrays.stream(numbers).forEach(System.out::println);

        int sum = Arrays.stream(numbers).mapToInt(i -> i.intValue()).sum();
        System.out.println("Сумма всех элементов: " + sum);

        boolean check = Arrays.stream(numbers).anyMatch(integer -> integer==10);
        System.out.println("Проверка на вхождение элемента: " + check);

        System.out.println("Список после деления: ");
        Arrays.stream(numbers).mapToDouble(i -> i/3.0).forEach(System.out::println);


    }
}
