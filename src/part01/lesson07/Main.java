package part01.lesson07;

import java.math.BigInteger;
import java.util.*;

/**
 * Основной класс
 */

public class Main {

    static HashMap<Integer, BigInteger> factorials = new HashMap<>();

    /**
     * Основной метод
     * @param args
     */

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        int size = 10000;
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arrayList.add(random.nextInt(10000));
        }

        Collections.sort(arrayList);

        long startTime1 = System.currentTimeMillis();

        Thread thread1 = new Thread();
        thread1.run();

        getFactorial(arrayList);

        long finishTime = System.currentTimeMillis();
        long time = finishTime - startTime1;


        for (int element : arrayList) {
            System.out.println(element + " факториал " + factorials.get(element));

        }

        System.out.println(time + " ms");

    }

    /**
     * Записывает факториал в hashmap, использует предыдущие расчеты
     * @param list
     * @return
     */
    public static HashMap<Integer, BigInteger> getFactorial(ArrayList<Integer> list) {
        int key;
        BigInteger value;
        key = list.get(0);
        value = factorial(key);
        factorials.put(key, value);

        for (int i = 1; i < list.size(); i++) {
            key = list.get(i);
            value = factorial(list.get(i - 1), list.get(i));
            factorials.put(key, value);
        }

        return factorials;
    }

    /**
     * Считает факториал передаваемого аргумента
     * @param num
     * @return
     */
    public static BigInteger factorial(int num) {
        if (num == 0) {
            return BigInteger.valueOf(1);
        }

        BigInteger result = BigInteger.valueOf(1);

        for (int i = 1; i <= num; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }

    /**
     * Считает факториал передаваемого аргумента num2, используя расчет num1
     * @param num1
     * @param num2
     * @return
     */
    public static BigInteger factorial(int num1, int num2) {
        BigInteger result = BigInteger.valueOf(1);
        BigInteger factNum1 = factorials.get(num1);

        if (num1 == num2) {
            return factNum1;
        }

        for (int i = num1 + 1; i <= num2; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        result = result.multiply(factNum1);

        return result;
    }
}


