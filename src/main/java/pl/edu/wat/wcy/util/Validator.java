package pl.edu.wat.wcy.util;

public class Validator {
    public static <T> T requireNonNull(T object, String name) {
        if (object == null) throw new IllegalArgumentException("The object " + name + " can't be null.");
        return object;
    }

    public static <T> T requireNonNull(T object) {
        if (object == null) throw new IllegalArgumentException("The object can't be null.");
        return object;
    }

    public static int requireNonNegative(int number, String name) {
        if (number < 0) throw new IllegalArgumentException(name + " can't be negative.");
        return number;
    }

    public static long requireNonNegative(long number, String name) {
        if (number < 0) throw new IllegalArgumentException(name + " can't be negative.");
        return number;
    }
}
