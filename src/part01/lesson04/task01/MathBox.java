package part01.lesson04.task01;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class MathBox<T> {

    HashSet<T> hashSet;

    public <T> MathBox(int[] nums) {
        hashSet = new HashSet<>();
        for (int t : nums) {
         //   hashSet.add(t);
        }
    }


    public long summator() {

        long summa = 0;
        Iterator itr = hashSet.iterator();
        while (itr.hasNext()) {
            int a = (int) itr.next();
            summa =+ a;
        }

        return summa;
    }

//    public void splitter(int m) {
//        HashSet<Double> set2 = new HashSet<Double>();
//        Iterator itr = hashSet.iterator();
//        while (itr.hasNext()) {
//
//            double a = (int) itr.next() / m;
//            set2.add(a);
//        }
//        hashSet = set2;
//    }

    @Override
    public String toString() {
        return "qqq" + hashSet.hashCode();
    }


}
