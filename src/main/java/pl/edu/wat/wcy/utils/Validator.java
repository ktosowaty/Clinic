package pl.edu.wat.wcy.utils;

public class Validator {
    public static <T> T requireNonNull(T object, String name) {
        if (object == null) throw new IllegalArgumentException("The object " + name + " can't be null.");
        return object;
    }

    public static <T> T requireNonNull(T object) {
        if (object == null) throw new IllegalArgumentException("The object can't be null.");
        return object;
    }
}
