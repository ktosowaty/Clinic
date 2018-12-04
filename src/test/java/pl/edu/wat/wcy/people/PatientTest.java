package pl.edu.wat.wcy.people;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import pl.edu.wat.wcy.model.person.data.Address;
import pl.edu.wat.wcy.model.person.data.PhoneNumber;
import pl.edu.wat.wcy.model.person.data.ZipCode;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.model.person.data.PersonalData;
import pl.edu.wat.wcy.model.person.data.Pesel;
import pl.edu.wat.wcy.model.person.data.Gender;

import java.time.LocalDate;

public class PatientTest {
    /*@Test
    public void constructor_givenProperData_shouldCreatePatient() {
        // given
        PersonalData personalData = new PersonalData("Bajer", null, "Bajerowicz", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        Address address = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        PhoneNumber phoneNumber = new PhoneNumber("123456789");

        // when
        Patient patient = new Patient(personalData, address, phoneNumber);

        // then
        Assert.assertEquals(personalData, patient.getPersonalData());
        Assert.assertEquals(address, patient.getAddress());
        Assert.assertEquals(phoneNumber, patient.getPhoneNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullPersonalData_shouldThrowIAE() {
        // given
        PersonalData personalData = null;
        Address address = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        PhoneNumber phoneNumber = new PhoneNumber("123456789");

        // when
        new Patient(personalData, address, phoneNumber);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullAddress_shouldThrowIAE() {
        // given
        PersonalData personalData = new PersonalData("Bajer", null, "Bajerowicz", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        Address address = null;
        PhoneNumber phoneNumber = new PhoneNumber("123456789");

        // when
        new Patient(personalData, address, phoneNumber);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullPhoneNumber_shouldThrowIAE() {
        // given
        PersonalData personalData = new PersonalData("Bajer", null, "Bajerowicz", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        Address address = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        PhoneNumber phoneNumber = null;

        // when
        new Patient(personalData, address, phoneNumber);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenPatientsWithSameId_shouldReturnTrue() {
        // given
        PersonalData personalData = new PersonalData("Bajer", null, "Bajerowicz", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        Address address = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        PhoneNumber phoneNumber = new PhoneNumber("123456789");
        Patient patient1 = new Patient(personalData, address, phoneNumber);
        ReflectionTestUtils.setField(patient1, "id", 123L);
        Patient patient2 = new Patient(personalData, address, phoneNumber);
        ReflectionTestUtils.setField(patient2, "id", 123L);

        // when
        boolean equalsResult = patient1.equals(patient2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenPatientsWithDifferentIds_shouldReturnFalse() {
        // given
        PersonalData personalData = new PersonalData("Bajer", null, "Bajerowicz", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        Address address = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        PhoneNumber phoneNumber = new PhoneNumber("123456789");
        Patient patient1 = new Patient(personalData, address, phoneNumber);
        ReflectionTestUtils.setField(patient1, "id", 123L);
        Patient patient2 = new Patient(personalData, address, phoneNumber);
        ReflectionTestUtils.setField(patient2, "id", 986432L);

        // when
        boolean equalsResult = patient1.equals(patient2);

        // then
        Assert.assertFalse(equalsResult);
    }*/
}
