package pl.edu.wat.wcy.model.benefit;

import javax.persistence.Column;

import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

public class Money {
    @Column
    private long value;

    private Money() {}

    public Money(long value) {
        this.value = value;
    }

    public void add(Money money) {
        requireNonNull(money, "money");
        value += money.getValue();
    }

    private long getTotalPart() {
        return Math.abs(value/100);
    }

    private long getFractionalPart() {
        return Math.abs(value%100);
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        String str;
        if (getFractionalPart() < 10) str = getTotalPart() + ".0" + getFractionalPart();
        else str = getTotalPart() + "." + getFractionalPart();
        if (value < 0) str = "-" + str;
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
