package pl.edu.wat.wcy.packages;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wat.wcy.model.benefit.Money;

public class MoneyTest {
    @Test
    public void constructor_givenPositiveValue_shouldCreateMoneyObject() {
        // given
        long value = 1321;

        // when
        Money money = new Money(value);

        // then
        Assert.assertEquals(1321, money.getCost());
    }

    @Test
    public void constructor_givenZeroValue_shouldCreateMoneyObject() {
        // given
        long value = 0;

        // when
        Money money = new Money(0);

        // then
        Assert.assertEquals(0, money.getCost());
    }

    @Test
    public void constructor_givenNegativeValue_shouldCreateMoneyObject() {
        // given
        long value = -1321;

        // when
        Money money = new Money(value);

        // then
        Assert.assertEquals(-1321, money.getCost());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_givenNull_shouldThrowIAE() {
        // given
        Money money = new Money(123);
        Money toAdd = null;

        // when
        money.add(toAdd);

        // then
        // throw IAE
    }

    @Test
    public void add_givenPositiveMoney_shouldUpdateValue() {
        // given
        Money money = new Money(123);
        Money toAdd = new Money(50);

        // when
        money.add(toAdd);

        // then
        Assert.assertEquals(173, money.getCost());
    }

    @Test
    public void add_givenNegativeMoney_shouldUpdateValue() {
        // given
        Money money = new Money(123);
        Money toAdd = new Money(-50);

        // when
        money.add(toAdd);

        // then
        Assert.assertEquals(73, money.getCost());
    }

    @Test
    public void add_givenZeroMoney_shouldNotUpdateValue() {
        // given
        Money money = new Money(123);
        Money toAdd = new Money(0);

        // when
        money.add(toAdd);

        // then
        Assert.assertEquals(123, money.getCost());
    }

    @Test
    public void toString_givenZeroMoney_shouldReturnProperValue() {
        // given
        Money money = new Money(0);

        // when
        String moneyStr = money.toString();

        // then
        Assert.assertEquals("0.00", moneyStr);
    }

    @Test
    public void toString_givenPositiveMoney_shouldReturnProperValue() {
        // given
        Money money = new Money(123);

        // when
        String moneyStr = money.toString();

        // then
        Assert.assertEquals("1.23", moneyStr);
    }

    @Test
    public void toString_givenPositiveMoneyLessThanOne_shouldReturnProperValue() {
        // given
        Money money = new Money(89);

        // when
        String moneyStr = money.toString();

        // then
        Assert.assertEquals("0.89", moneyStr);
    }

    @Test
    public void toString_givenPositiveMoneyLessThanOneTenth_shouldReturnProperValue() {
        // given
        Money money = new Money(9);

        // when
        String moneyStr = money.toString();

        // then
        Assert.assertEquals("0.09", moneyStr);
    }

    @Test
    public void toString_givenNegativeMoney_shouldReturnProperValue() {
        // given
        Money money = new Money(-123);

        // when
        String moneyStr = money.toString();

        // then
        Assert.assertEquals("-1.23", moneyStr);
    }

    @Test
    public void toString_givenNegativeMoneyGreaterThanOneTenth_shouldReturnProperValue() {
        // given
        Money money = new Money(-6);

        // when
        String moneyStr = money.toString();

        // then
        Assert.assertEquals("-0.06", moneyStr);
    }

    @Test
    public void equals_givenTwoMoneyObjectsWithSameValue_shouldReturnTrue() {
        // given
        Money money1 = new Money(123);
        Money money2 = new Money(123);

        // when
        boolean equalsResult = money1.equals(money2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoMoneyObjectsWithDifferentValues_shouldReturnFalse() {
        // given
        Money money1 = new Money(123);
        Money money2 = new Money(456);

        // when
        boolean equalsResult = money1.equals(money2);

        // then
        Assert.assertFalse(equalsResult);
    }
}
