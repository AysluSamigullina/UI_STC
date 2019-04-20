package part01.lesson02.task01;

/**
 * Основной класс
 */
public class Main {
    /**
     * Основной метод
     *
     * @param args String[]
     * @throws MyException
     */
    public static void main(String[] args) throws MyException {
        String s = "Hello, World!";

        Object obj = null;

        try {
            if (obj.equals(s) == false) {
                System.err.println("This may result in NullPointerException if unknownObject is null");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("null нельзя сравнить с ненулевым объектом");

        } finally {
            System.out.println(s);
        }

        if (s.equals(obj) == false) {
            System.out.println("А обратный вариант возможен");
        }

        char[] ch = s.toCharArray();

        try {
            for (int i = 0; i < 20; i++) {
                System.out.println(ch[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Мы вышли за пределы массива");
        } finally {
            System.out.println(s);
        }

        try {
            throw new MyException();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
