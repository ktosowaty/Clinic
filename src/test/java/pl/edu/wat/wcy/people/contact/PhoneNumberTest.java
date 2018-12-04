package pl.edu.wat.wcy.people.contact;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wat.wcy.model.person.data.PhoneNumber;

public class PhoneNumberTest {
    @Test
    public void constructor_givenProperNumber_shouldReturnPhoneNumberObject() {
        // given
        String number = "123456789";

        // when
        PhoneNumber phoneNumber = new PhoneNumber(number);

        // then
        Assert.assertEquals(number, phoneNumber.getNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNull_shouldThrowIAE() {
        // given
        String number = null;

        // when
        new PhoneNumber(number);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNonNumericString_shouldThrowIAE() {
        // given
        String number = "2543asdfk";

        // when
        new PhoneNumber(number);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenStringOfIncorrectLength_shouldThrowIAE() {
        // given
        String number = "1234567";

        // when
        new PhoneNumber(number);

        // then
        // throw IAE
    }

    @Test
    public void parse_givenProperNumber_shouldReturnPhoneNumberObject() {
        // given
        String number = "123456789";

        // when
        PhoneNumber phoneNumber = PhoneNumber.parsePhoneNumber(number);

        // then
        Assert.assertEquals(number, phoneNumber.getNumber());
    }

    @Test
    public void parse_givenNull_shouldReturnNull() {
        // given
        String number = null;

        // when
        PhoneNumber phoneNumber = PhoneNumber.parsePhoneNumber(number);

        // then
        Assert.assertNull(phoneNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_givenNonNumericString_shouldThrowIAE() {
        // given
        String number = "123sfd789";

        // when
        PhoneNumber.parsePhoneNumber(number);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_givenStringOfIncorrectLength_shouldThrowIAE() {
        // given
        String number = "123789";

        // when
        PhoneNumber.parsePhoneNumber(number);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoSamePhoneNumbers_shouldReturnTrue() {
        // given
        PhoneNumber phoneNumber1 = new PhoneNumber("123456789");
        PhoneNumber phoneNumber2 = new PhoneNumber("123456789");

        // when
        boolean equalsResult = phoneNumber1.equals(phoneNumber2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoDifferentPhoneNumbers_shouldReturnFalse() {
        // given
        PhoneNumber phoneNumber1 = new PhoneNumber("123456789");
        PhoneNumber phoneNumber2 = new PhoneNumber("987654321");

        // when
        boolean equalsResult = phoneNumber1.equals(phoneNumber2);

        // then
        Assert.assertFalse(equalsResult);
    }
}
