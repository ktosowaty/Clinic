package pl.edu.wat.wcy.model.person.data;

import javax.persistence.Column;

import java.util.Objects;

public class ZipCode {
    @Column(length = 6)
    private String code;

    private ZipCode() {
        // JPA
    }

    public ZipCode(String code) {
        if (!isValidZipCode(code)) throw new IllegalArgumentException("Invalid zip code: " + code + ".");
        this.code = code;
    }

    private boolean isValidZipCode(String code) {
        return code != null && code.matches("[0-9][0-9]-[0-9][0-9][0-9]");
    }

    public String getCode() {
        return code;
    }

    public static ZipCode parseZipCode(String code) {
        return (code == null) ? null : new ZipCode(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipCode zipCode1 = (ZipCode) o;
        return Objects.equals(code, zipCode1.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
