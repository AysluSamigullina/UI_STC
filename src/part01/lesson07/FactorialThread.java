package part01.lesson07;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс для расчета факториала с использованием потоков
 */
public class FactorialThread implements Runnable {
    @Override
    public void run() {
        factorial(number);
    }

    /**
     * Объявление ConcurrentHashMap для совместного чтения и записи
     */
    private static ConcurrentHashMap<Integer, BigInteger> factorialsMap = new ConcurrentHashMap<>();

    /**
     * Число, факториал которого в дальнейшем будет рассчитываться
     */
    private int number;

    /**
     * Конструктор
     *
     * @param num
     */
    public FactorialThread(int num) {
        this.number = num;
    }

    static {
        factorialsMap.put(0, BigInteger.valueOf(1));
        factorialsMap.put(1, BigInteger.valueOf(1));
    }

    /**
     * Возвращает рассчитанный факториал передаваемого значение
     *
     * @param n
     * @return
     */
    public BigInteger getReadyFactorial(int n) {
        return factorialsMap.get(n);
    }

    /**
     * Считает факториал переданного аргумента
     *
     * @param num
     * @return
     */
    public BigInteger factorial(int num) {
        int tmp = findLessKey(num);
        BigInteger result = factorialsMap.get(tmp);

        for (int i = tmp + 1; i <= num; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        factorialsMap.put(num, result);
        return result;
    }

    /**
     * Находит последнее рассчитанное число
     *
     * @param num
     * @return
     */
    public int findLessKey(int num) {
        for (int i = num - 1; i > 1; i--) {
            if (factorialsMap.containsKey(i)) {
                return i;
            }
        }
        return 1;
    }

    /**
     * Выводит на печать пару ключ-значение от ConcurrentHashMap
     */
    public static void print() {
        for (ConcurrentHashMap.Entry entry : factorialsMap.entrySet()) {
            System.out.println(entry.getKey() + "; факториал " + entry.getValue());
        }
    }

}
