package part01.lesson06.task01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Основной класс
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String s = "";

        List<String> stringList = new ArrayList<>();
        try {
            stringList = Files.readAllLines(Paths.get("lesson06_task01"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        for (String str: stringList) {
            s = s + str.toLowerCase().toString() + " ";
        }

        /**
         * Происходит отделение слов от небуквенных символов и выравнивание по регистру
         */
        String[] newstr = s.replaceAll("[^а-яА-Яa-zA-Z ]", "").split(" ");

        /**
         * Оставление только уникальных слов
         */
        HashSet<String> set = new HashSet<String>();
        Collections.addAll(set, newstr);

        /**
         * Сортировка слов по алфавиту
         */
        ArrayList<String> list = new ArrayList<>(set);
        Collections.sort(list);

        /**
         * Вывод слов в отдельный файл
         */
        try (DataOutputStream dataOutputStream =
                     new DataOutputStream(new FileOutputStream("lesson06_task01_out"))) {
            for (String str: list) {
                dataOutputStream.writeUTF(str);
            }

            System.out.println("Файл записан!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


