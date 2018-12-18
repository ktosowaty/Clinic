package pl.edu.wat.wcy.model.person.data;

import javax.persistence.Column;
import java.util.Objects;

public class PhoneNumber {
    private static final int PHONE_NUMBER_LENGTH = 9;

    @Column(length = 9)
    private String number;

    private PhoneNumber() {}

    public PhoneNumber(String number) {
        if (!isValidPhoneNumber(number)) throw new IllegalArgumentException("Given number is not valid: " + number + ".");
        this.number = number;
    }

    private boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("[0-9]+") && number.length() == PHONE_NUMBER_LENGTH;
    }

    public String getNumber() {
        return number;
    }

    public static PhoneNumber parsePhoneNumber(String number) {
        return (number == null) ? null : new PhoneNumber(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
