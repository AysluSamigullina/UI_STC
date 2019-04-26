package part01.lesson04.task01;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] numbers = new int[20];

        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
        }



        MathBox<? extends Number> mathBox = new MathBox(numbers);
        long sum = mathBox.summator();
        System.out.println(sum + " first sum ");
        System.out.println(mathBox.toString());

     //   mathBox.splitter(2);
     //   System.out.println(mathBox.toString());
    //    long sum2 = mathBox.summator();
     //   System.out.println(sum2);

    }


}





