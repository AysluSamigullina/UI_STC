package part01.lesson09;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClass extends ClassLoader {

    final private String PATH = "SomeClass.java";

    /**
     * Считывает с консоли текст
     *
     * @return
     * @throws IOException
     */
    public static String read() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringBuilder text = new StringBuilder();
        String begin = "package part01.lesson09;\n" +
                "\n" +
                "public class SomeClass implements Worker {\n" +
                "    @Override\n" +
                "    public void doWork() {";
        String end = "  }\n" + "}";

        text.append(begin).append(" ");
        while (!(s = bufferedReader.readLine()).equals("")) {
            text.append(s).append(" ");
        }
        text.append(end).append(" ");
        return text.toString();
    }

    /**
     * Записывает в файл
     *
     * @param fileName
     * @param content
     */
    public static void write(String fileName, String content) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] buffer = content.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (PATH.equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name); // механизм загрузки
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass starts work: " + name);
        if (PATH.equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("SomeClass.class"));
                return defineClass(name, bytes, 0, bytes.length); // мапит byte[] в Class
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
