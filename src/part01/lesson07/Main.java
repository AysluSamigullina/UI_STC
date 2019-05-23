package part01.lesson07;

import java.util.*;


/**
 * Основной класс
 */

public class Main {

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

        Factorial.getFactorial(arrayList);

        long finishTime1 = System.currentTimeMillis();
        long time1 = finishTime1 - startTime1;


//        for (int element : arrayList) {
//            System.out.println(element + "; факториал = " + Factorial.factorials.get(element));
//
//        }

        long startTime2 = System.currentTimeMillis();

        for (int i = 0; i < arrayList.size(); i++) {
            FactorialThread ft = new FactorialThread(arrayList.get(i));
            Thread thread = new Thread(ft);
            thread.start();
            //System.out.println(arrayList.get(i) + "; факториал " + ft.getReadyFactorial(arrayList.get(i)) + ": " + thread.getName());
        }
        long finishTime2 = System.currentTimeMillis();
        long time2 = finishTime2 - startTime2;

        //FactorialThread.print();
        System.out.println("Расчет без потоков: " + time1 + " ms");
        System.out.println("Расчет с потоками: " + time2 + " ms");

    }

}


