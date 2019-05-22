package part01.lesson02.task01;

/**
 * Основной класс
 */
public class Main {
    static String s = "Hello, World!";

    /**
     * Основной метод
     * @param args String[]
     * @throws MyException
     */
    public static void main(String[] args) throws MyException {

        method1();
        method2();
        method3();
    }

    /**
     * Выбрасывает исключение NullPointerException
     *
     * @return
     */
    private static String method1() {
        Object obj = null;
        try {
            if (!obj.equals(s)) {
                return "This may result in NullPointerException if unknownObject is null";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "null нельзя сравнить с ненулевым объектом";
        } finally {

            return s;
        }
    }

    /**
     * Выбрасывает исключение ArrayIndexOutOfBoundsException
     */
    private static void method2() {
        char[] ch = s.toCharArray();

        try {
            for (int i = 0; i < 20; i++) {
                System.out.println(ch[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Мы вышли за пределы массива");
        }
    }

    /**
     * Выбрасывает собственное исключение
     */
    private static void method3() {
        try {
            throw new MyException();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
