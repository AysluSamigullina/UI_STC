package part01.lesson04.task01;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Класс объектоы
 */
public class ObjectBox {

    protected HashSet<Object> hashSet;

    /**
     * Конструктор
     */
    public ObjectBox() {
        hashSet = new HashSet<>();
    }

    /**
     * Добавляет объект в коллекцию
     *
     * @param o
     */
    public void addObject(Object o) {
        hashSet.add(o);

    }

    /**
     * Удаляет объект из коллекции
     *
     * @param o
     */
    public void deleteObject(Object o) {
        hashSet.remove(o);

    }

    /**
     * Выводит на экран список элементов коллекции
     */
    public void dump() {
        for (Iterator<?> i = hashSet.iterator(); i.hasNext(); ) {
            Object o = i.next();
            System.out.print(o + " ");
        }
        System.out.println();
    }
}
