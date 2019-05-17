package part01.lesson08.task01;

import java.io.*;

/**
 * Класс для работы с файлом
 */
public class Serial {

    //private String fileName;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    /**
     * Конструктор
     *
     * @param file
     * @throws IOException
     */
    public Serial(String file) throws IOException {
        this.objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        this.objectInputStream = new ObjectInputStream(new FileInputStream(file));
    }

    /**
     * Записывает объекты в файл
     *
     * @param object
     * @param file
     */
    public void serialize(Object object, String file) {

        try {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывает объекты из файла
     *
     * @param file
     * @return
     */
    public Object deSerialize(String file) {
        Object object = new Object();
        try {
            object = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
