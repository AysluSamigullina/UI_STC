package part01.lesson06.task02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Words {

    private static char[] symbols = {'(', '.', '|', '!', '|', '?', ')', '+', '"', '"'};
    private static final int MAX_LENGHT = 15;
    private static final int MAX_PHRASES_COUNT = 20;
    private static final int MAX_ARRAY = 1000;

    /**
     * Составляет случайное слово
     * @return
     */
    public static String randomWord() {
        Random random = new Random();
        StringBuilder word = new StringBuilder();
        int length = 1 + (int) ( Math.random() * MAX_LENGHT );
        for (int i = 0; i < length; i++) {
            word.append((char)('a' + random.nextInt(26)));
        }

        return word.toString();
    }

    /**
     * Составляет предложение из случайных слов
     * @return
     */
    public static String randomPhrase() {
        Random random = new Random();
        int length = 1 + (int) ( Math.random() * MAX_LENGHT );
        StringBuilder phrase = new StringBuilder(length);
        for (int i = 0; i < length ; i++) {
            phrase.append(randomWord());
            int comma = random.nextInt(10);
            if (comma == 5 & i != length-1) {
                phrase.append(",");
            }
            phrase.append(" ");
        }

        char first = Character.toUpperCase(phrase.charAt(0));
        phrase.setCharAt(0, first );
        phrase.setCharAt(phrase.length()-1, symbols[random.nextInt(10)]);

        return phrase.toString();
    }

    
    /**
     * Создает абзац из случайных слов
     * @return
     */
    public static String createIndent() {
        StringBuilder indent = new StringBuilder();
        int length = 1 + (int) ( Math.random() * MAX_PHRASES_COUNT );
        indent.append("\t");
        for (int i = 0; i < length; i++) {
            indent.append(randomPhrase());
            indent.append(" ");
        }
        indent.append("\n");

        return indent.toString();
    }

    
    /**
     * Создает текст из случайных абзацев
     * @return
     */
    public static StringBuilder createText() {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < random.nextInt(10); i++) {
            text.append(createIndent());
        }

        return text;
    }

    

     /**
     * Создает массив уникальных слов
     * @return
     */
    public static String[] createWordsArray() {
        int volume = 1 + (int) ( Math.random() * MAX_ARRAY );
        String[] wordsLibrary = new String[volume];

        for (int i = 0; i < volume; i++) {
            wordsLibrary[i] = randomWord();
        }

        return wordsLibrary;
    }




}
