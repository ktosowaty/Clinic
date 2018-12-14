package pl.edu.wat.wcy.people.contact;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wat.wcy.model.person.data.address.ZipCode;

public class ZipCodeTest {
    @Test
    public void constructor_givenProperString_shouldCreateZipCodeObject() {
        // given
        String code = "12-345";

        // when
        ZipCode zipCode = new ZipCode(code);

        // then
        Assert.assertEquals(code, zipCode.getCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullString_shouldThrowIAE() {
        // given
        String code = null;

        // when
        new ZipCode(code);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenInvalidString_shouldThrowIAE() {
        // given
        String code = "pikej";

        // when
        new ZipCode(code);

        // then
        // throw IAE
    }

    @Test
    public void parse_givenNull_shouldReturnNull() {
        // given
        String code = null;

        // when
        ZipCode zipCode = ZipCode.parseZipCode(code);

        // then
        Assert.assertNull(zipCode);
    }

    @Test
    public void parse_givenProperString_shouldCreateZipCodeObject() {
        // given
        String code = "19-876";

        // when
        ZipCode zipCode = ZipCode.parseZipCode(code);

        // then
        Assert.assertEquals(code, zipCode.getCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_givenInvalidString_shouldThrowIAE() {
        // given
        String code = "pikej";

        // when
        ZipCode.parseZipCode(code);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoSameZipCodes_shouldReturnTrue() {
        // given
        ZipCode zipCode1 = new ZipCode("12-345");
        ZipCode zipCode2 = new ZipCode("12-345");

        // when
        boolean equalsResult = zipCode1.equals(zipCode2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoDifferentZipCodes_shouldReturnFalse() {
        // given
        ZipCode zipCode1 = new ZipCode("12-345");
        ZipCode zipCode2 = new ZipCode("99-999");

        // when
        boolean equalsResult = zipCode1.equals(zipCode2);

        // then
        Assert.assertFalse(equalsResult);
    }
}
