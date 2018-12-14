package pl.edu.wat.wcy.model.person.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

public class Pesel {
    private static final int PESEL_LENGTH = 11;

    @Column(nullable = false, length = 11)
    private String pesel;

    private Pesel() {
        // JPA
    }

    public Pesel(String pesel) {
        checkPesel(pesel);
        this.pesel = pesel;
    }

    private void checkPesel(String pesel) {
        requireNonNull(pesel, "pesel");
        checkLength(pesel);
        checkDigits(pesel);
        checkDate(pesel);
        checkControlDigit(pesel);
    }

    private void checkLength(String pesel) {
        int length = pesel.length();
        if (length != PESEL_LENGTH) throw new IllegalArgumentException("Invalid pesel length: " + length);
    }

    private void checkDigits(String pesel) {
        if (!pesel.matches("[0-9]+")) throw new IllegalArgumentException("Invalid characters in pesel: " + pesel);
    }

    private void checkDate(String pesel) {
        String date = pesel.substring(0, 6);
        int day = getDay(date);
        int month = getMonth(date);
        int year = getYear(date, month);
        try {
            if (year >= 2000) month -= 20;
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid date in pesel. Wrong combination of year, month and day: " + pesel);
        }
    }

    @JsonIgnore
    private int getDay(String date) {
        return Integer.parseInt(date.substring(4));
    }

    @JsonIgnore
    private int getMonth(String date) {
        return Integer.parseInt(date.substring(2, 4));
    }

    @JsonIgnore
    private int getYear(String date, int month) {
        String first2YearDigits;
        if (month <= 12) first2YearDigits = "19";
        else first2YearDigits = "20";
        String last2YearDigits = date.substring(0, 2);
        return Integer.parseInt(first2YearDigits + last2YearDigits);
    }

    private void checkControlDigit(String pesel) {
        int controlDigit = (getDigit(pesel.charAt(0)) * 9 + getDigit(pesel.charAt(1)) * 7 + getDigit(pesel.charAt(2)) * 3 +
                getDigit(pesel.charAt(3)) + getDigit(pesel.charAt(4)) * 9 + getDigit(pesel.charAt(5)) * 7 +
                getDigit(pesel.charAt(6)) * 3 + getDigit(pesel.charAt(7)) + getDigit(pesel.charAt(8)) * 9 +
                getDigit(pesel.charAt(9)) * 7) % 10;
        if (controlDigit != getDigit(pesel.charAt(10))) throw new IllegalArgumentException("Invalid control digit ("
                + controlDigit + ") in pesel: " + pesel + ".");
    }

    @JsonIgnore
    private int getDigit(Character character) {
        return Character.getNumericValue(character);
    }

    @JsonIgnore
    public Gender getGender() {
        int genderDigit = Character.getNumericValue(pesel.charAt(9));
        if (genderDigit % 2 == 0) return Gender.FEMALE;
        else return Gender.MALE;
    }

    @JsonIgnore
    public LocalDate getBirthDate() {
        String date = pesel.substring(0, 6);
        int day = getDay(date);
        int month = getMonth(date);
        int year = getYear(date, month);
        return LocalDate.of(year, month, day);
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesel pesel1 = (Pesel) o;
        return Objects.equals(pesel, pesel1.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }
}
