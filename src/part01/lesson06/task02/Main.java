package part01.lesson06.task02;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.io.*;

/**
 * Основной класс
 */
public class Main {

    /**
     * Основной метод
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        String[] library = Words.createWordsArray();

        getFiles("/Users/timur/projects/UI_STC/lesson06-2/", 5, 1024, library, 10);
        // /Users/timur/projects/UI_STC/

    }

    /**
     * Метод создает в определенном каталоге файлы переданного размера и записывает в них массив чисел
     * @param path
     * @param n
     * @param size
     * @param words
     * @param probability
     * @throws FileNotFoundException
     */
    static void getFiles(String path, int n, int size, String[] words, int probability) throws FileNotFoundException {

       Random random = new Random();

       StringBuilder text = Words.createText();
       String str = String.valueOf(text);
       String[] wordsFromText = str.split(" ");

       int changingWords = wordsFromText.length % probability;

       for (int i = 0; i < changingWords; i++) {
           int randomWordIndex = random.nextInt(wordsFromText.length - 1);
           wordsFromText[randomWordIndex] = words[random.nextInt(words.length - 1)];
       }

        String newText = String.join(" ", wordsFromText);

        System.out.println(newText);

        //String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            String s = "tmp" + i;
            s = s+".txt";
            File newFile = new File(path, s);
            if (newFile.canWrite()) {
                FileOutputStream fos = new FileOutputStream(newFile, false);
                byte[] buffer = newText.getBytes();

                try {

                    fos.write(buffer, 0, size);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
