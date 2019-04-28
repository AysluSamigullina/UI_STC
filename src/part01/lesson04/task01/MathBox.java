package part01.lesson04.task01;

import java.util.*;

/**
 * Класс математических действий
 */
public class MathBox extends ObjectBox {
    /**
     * Конструктор
     *
     * @param numbers
     */
    public MathBox(Integer[] numbers) {
        hashSet = new HashSet<>();
        Collections.addAll(hashSet, numbers );
    }

    /**
     * Суммирует числа из коллекции
     *
     * @return
     */
    public double summator() {
        double summa = 0.0;

        for (Object element: hashSet) {
            if (element instanceof Double)
                summa =+ (Double) element;
            if (element instanceof Integer)
                summa =+ (Integer) element;
        }

        return summa;
    }

    /**
     * Делит каждый элемент коллекции чисел на делитель-аргумент
     *
     * @param m
     */
    public void splitter (double m) {
        HashSet<Double> set2 = new HashSet<>();
        for (Object element: hashSet) {
            if (element instanceof Double)
                set2.add((Double) element / m );
            if (element instanceof Integer)
                set2.add((Integer) element / m );
        }

        hashSet = new HashSet<>(set2);
    }

    /**
     * Выполняет поиск целого числа и при наличии его удаляет из коллекции
     *
     * @param n
     */
    public void checkInt(Integer n) {
        hashSet.remove(n);
    }

    /**
     * Вычисляет хеш-код
     *
     * @return
     */
    @Override
    public int hashCode() {
        final int p = 31;
        int summa = 0;
        for (Object element: hashSet) {
            summa =+ element.hashCode() * p;
        }

        return summa;
    }

    /**
     * Сравнивает объекты
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (hashSet.hashCode() != o.hashCode() || !(o instanceof MathBox)) {
            return false;
        }

        List list  = new ArrayList();
        List list1 = new ArrayList();

        list.addAll(hashSet);
        list1.addAll(((MathBox) o).hashSet);

        Collections.sort(list);
        Collections.sort(list1);

        if ((list.size() != list1.size())) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            if ((double) list.get(i) != (double) list1.get(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Преобразовывает в строку
     *
     * @return
     */
    @Override
    public String toString() {
        String s = "";
        for (Object element: hashSet) {
            s = s + element.toString() + "; ";
        }

        return s;
    }
}
