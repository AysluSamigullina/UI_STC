package part01.lesson02.task02;

import java.util.Random;

/**
 * Работа с массивом
 */
public class ArrayTasks {

    /**
     * Создает массив
     *
     * @param size
     * @return
     */
    public int[] numbersList(int size) {
        int[] numbers = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt();
        }

        return numbers;
    }

    /**
     * Находит корни
     *
     * @param array
     * @throws NegativeNumberException
     */
    public void sqrt(int[] array) throws NegativeNumberException {
        for (int i = 0; i < array.length; i++) {
            try {
                if (array[i] < 0) {
                    throw new NegativeNumberException();
                }
                int k = (int) Math.sqrt(array[i]);

                if ((k * k) == array[i]) {
                    System.out.println(array[i]);
                }
            } catch (NegativeNumberException e) {
                e.printStackTrace();
                System.out.println(array[i] + " является отрицательным числом, корень не находится");
            }
        }
    }
}
