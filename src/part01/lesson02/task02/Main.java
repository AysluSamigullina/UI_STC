package part01.lesson02.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Основной класс
 */
public class Main {

    /**
     * Работает с массивом
     *
     * @param args
     * @throws NegativeNumberException
     * @throws IOException
     */
    public static void main(String[] args) throws NegativeNumberException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int n = 0;
        try {
            n = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Введена строка вместо числа!");
        }

        if (n < 0) {
            throw new NegativeNumberException("Введено отрицательное число!");
        }

        ArrayTasks at = new ArrayTasks();
        int[] arr     = at.numbersList(n);

        for (int value : arr) {
            System.out.println(value);
        }

        System.out.println("начинаем находить корни");
        at.sqrt(arr);
    }
}
