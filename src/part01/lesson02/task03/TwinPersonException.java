package part01.lesson02.task03;

/**
 * Исключение для одинакового возраста и имени
 */
public class TwinPersonException extends Exception {

    /**
     * Генерирует исключение
     *
     * @param message
     */
    public TwinPersonException(String message) {
        super(message);
    }
}
