package part01.lesson02.task03;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Метод №2 для сортировки через Comparator
 */
public class Sort2 implements Sort {

    @Override
    public void sort(ArrayList<Person> arrayList) {

        arrayList.sort(
                Comparator.comparing(Person::getSex)
                        .thenComparing(Person::getAge)
                        .thenComparing(Person::getName)
        );
    }
}
