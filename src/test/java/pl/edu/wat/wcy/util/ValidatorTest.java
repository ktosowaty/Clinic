package pl.edu.wat.wcy.util;

import org.junit.Assert;
import org.junit.Test;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

public class ValidatorTest {
    @Test(expected = IllegalArgumentException.class)
    public void requireNonNull_givenNull_shouldThrowIAE() {
        // given
        String nullString = null;

        // when
        requireNonNull(nullString);

        // then
        // throw IAE
    }

    @Test
    public void requireNonNull_givenEmptyString_shouldReturnStringObject() {
        // given
        String emptyString = "";

        // when
        String actual = requireNonNull(emptyString);

        // then
        Assert.assertEquals("", actual);
    }

    @Test
    public void requireNonNull_givenLongObject_shouldReturnLongObject() {
        // given
        Long variable = 12345L;

        // when
        Long actual = requireNonNull(variable);

        // then
        Assert.assertEquals(12345L, (long) actual);
    }
}
