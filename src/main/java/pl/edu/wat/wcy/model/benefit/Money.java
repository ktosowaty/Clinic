package pl.edu.wat.wcy.model.benefit;

import javax.persistence.Column;

import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

public class Money {
    @Column
    private long cost;

    private Money() {}

    public Money(long cost) {
        this.cost = cost;
    }

    public void add(Money money) {
        requireNonNull(money, "money");
        cost += money.getCost();
    }

    private long getTotalPart() {
        return Math.abs(cost /100);
    }

    private long getFractionalPart() {
        return Math.abs(cost %100);
    }

    public long getCost() {
        return cost;
    }

    @Override
    public String toString() {
        String str;
        if (getFractionalPart() < 10) str = getTotalPart() + ".0" + getFractionalPart();
        else str = getTotalPart() + "." + getFractionalPart();
        if (cost < 0) str = "-" + str;
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return cost == money.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }
}
