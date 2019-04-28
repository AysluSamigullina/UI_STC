package part01.lesson04.task01;

import java.util.*;

/**
 *
 * Основной класс
 */

public class Main {
    /**
     *
     * Основной метод
     * @param args
     */

    public static void main(String[] args) {

        Integer[] numbers = new Integer[20];

        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(30);
        }

        MathBox mathBox = new MathBox(numbers);
        double sum = mathBox.summator();
        System.out.println("Summa: " + sum);

        mathBox.checkInt(3);
        mathBox.splitter(2.0);

        mathBox.addObject(44);
        mathBox.deleteObject(44);
        mathBox.dump();

        MathBox mathBox1 = new MathBox(numbers);
        mathBox1.splitter(2.0);

        System.out.println("сравнение   " + mathBox.equals(mathBox1));
        System.out.println(mathBox);
    }
}
