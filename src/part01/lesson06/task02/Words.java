package part01.lesson06.task02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Words {

    private static char[] symbols = {'(', '.', '|', '!', '|', '?', ')', '+', '"', '"'};

    public static String randomWord(int length) {
        Random random = new Random();
        StringBuilder word = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            word.append((char)('a' + random.nextInt(26)));
        }

        return word.toString();
    }

    public static String randomPhrase(int length) {
        Random random = new Random();
        StringBuilder phrase = new StringBuilder(length);
        for (int i = 0; i < length ; i++) {
            phrase.append(randomWord(random.nextInt(15))).append(" ");
        }

        char first = Character.toUpperCase(phrase.charAt(0));
        phrase.setCharAt(0, first );

        int endOfPhrase = phrase.length()-1;
        phrase.setCharAt(endOfPhrase, symbols[random.nextInt(10)]);

        return phrase.toString();
    }

    public HashSet<String> createWordsArray(int volume) {
        Random random = new Random();
        HashSet<String> wordsLibrary = new HashSet<>();

        for (int i = 0; i < volume; i++) {
            wordsLibrary.add(randomWord(random.nextInt(15)));
        }

        return wordsLibrary;
    }
}
