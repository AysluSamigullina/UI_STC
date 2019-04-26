package part01.lesson04.task01;

import java.util.HashSet;
import java.util.Iterator;

public class ObjectBox {

    HashSet<Object> hashSet;

    public ObjectBox() {
        hashSet = new HashSet<>();
    }

    public HashSet<Object> addObject(Object o) {
        hashSet.add(o);
        return hashSet;
    }

    public HashSet<Object> deleteObject(Object o) {
        Iterator itr = hashSet.iterator();

        while (itr.hasNext()) {
            if (itr.next().equals(o)) {
                itr.remove();
            }
        }

        return hashSet;
    }

    static void dump(HashSet<Object> hs) {
        for (Iterator<?> i = hs.iterator(); i.hasNext(); ) {
            Object o = i.next();
            System.out.println(o);
        }
    }


}
