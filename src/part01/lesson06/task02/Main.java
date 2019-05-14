package part01.lesson06.task02;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //Words words = new Words();
//        System.out.println("это случайное слово " + Words.randomWord());
//        System.out.println("это случайная фраза " + Words.randomPhrase());
//        System.out.println("Это случайный абзац " + Words.createIndent() );
//        System.out.println("Это случайный текст " + Words.createText());

        String[] library = Words.createWordsArray();

        getFiles("/Users/timur/projects/UI_STC/lesson06-2/", 5, 10, library, 10);
        // /Users/timur/projects/UI_STC/

    }

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

        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = "tmp" + i;
            File newFile = new File(path, names[i]);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

//


//        for (String str1: file.list()) {
//            System.out.println(str1);
//        }

    }
}
