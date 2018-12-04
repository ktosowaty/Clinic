package pl.edu.wat.wcy.people.personal.data;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wat.wcy.model.person.data.Pesel;

public class PeselTest {
    @Test
    public void constructor_givenCorrectString_shouldCreatePeselObject() {
        // given
        String peselStr = "93031909099";

        // when
        Pesel pesel = new Pesel(peselStr);

        // then
        Assert.assertEquals(peselStr, pesel.getPesel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNull_shouldThrowIAE() {
        // given
        String peselStr = null;

        // when
        new Pesel(peselStr);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenStringOfIncorrectLength_shouldThrowIAE() {
        // given
        String peselStr = "12345678";

        // when
        new Pesel(peselStr);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNonAlphaNumericString_shouldThrowIAE() {
        // given
        String peselStr = "1j7s6v55pq0";

        // when
        new Pesel(peselStr);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenStringWithWrongDate_shouldThrowIAE() {
        // given
        String peselStr = "93662309099";

        // when
        new Pesel(peselStr);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenStringWithIncorrectControlDigit_shouldThrowIAE() {
        // given
        String peselStr = "93031909096";

        // when
        new Pesel(peselStr);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoSamePesels_shouldReturnTrue() {
        // given
        Pesel pesel1 = new Pesel("93031909099");
        Pesel pesel2 = new Pesel("93031909099");

        // when
        boolean equalsResult = pesel1.equals(pesel2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoDifferentPesels_shouldReturnFalse() {
        // given
        Pesel pesel1 = new Pesel("93031909099");
        Pesel pesel2 = new Pesel("95031709099");

        // when
        boolean equalsResult = pesel1.equals(pesel2);

        // then
        Assert.assertFalse(equalsResult);
    }
}
