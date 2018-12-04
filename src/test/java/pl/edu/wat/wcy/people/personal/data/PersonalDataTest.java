package pl.edu.wat.wcy.people.personal.data;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wat.wcy.model.person.data.Gender;
import pl.edu.wat.wcy.model.person.data.PersonalData;
import pl.edu.wat.wcy.model.person.data.Pesel;

import java.time.LocalDate;

public class PersonalDataTest {
    /*@Test
    public void constructor_givenProperData_shouldCreatePersonalDataObject() {
        // given
        String firstName = "Bolo";
        String secondName = "Bolerino";
        String surname = "Bolczyński";
        Gender gender = Gender.MAN;
        LocalDate birthDate = LocalDate.parse("1993-03-19");
        Pesel pesel = new Pesel("93031909099");

        // when
        PersonalData personalData = new PersonalData(firstName, secondName, surname, gender, birthDate, pesel);

        // then
        Assert.assertEquals(firstName, personalData.getFirstName());
        Assert.assertEquals(secondName, personalData.getSecondName());
        Assert.assertEquals(surname, personalData.getSurname());
        Assert.assertEquals(gender, personalData.getGender());
        Assert.assertEquals(birthDate, personalData.getBirthDate());
        Assert.assertEquals(pesel, personalData.getPatientPesel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullFirstName_shouldThrowIAE() {
        // given
        String firstName = null;
        String secondName = "Bolerino";
        String surname = "Bolczyński";
        Gender gender = Gender.MAN;
        LocalDate birthDate = LocalDate.parse("1993-03-19");
        Pesel pesel = new Pesel("93031909099");

        // when
        new PersonalData(firstName, secondName, surname, gender, birthDate, pesel);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullSurname_shouldThrowIAE() {
        // given
        String firstName = "Bolo";
        String secondName = "Bolerino";
        String surname = null;
        Gender gender = Gender.MAN;
        LocalDate birthDate = LocalDate.parse("1993-03-19");
        Pesel pesel = new Pesel("93031909099");

        // when
        new PersonalData(firstName, secondName, surname, gender, birthDate, pesel);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullSex_shouldThrowIAE() {
        // given
        String firstName = "Bolo";
        String secondName = "Bolerino";
        String surname = "Bolczyński";
        Gender gender = null;
        LocalDate birthDate = LocalDate.parse("1993-03-19");
        Pesel pesel = new Pesel("93031909099");

        // when
        new PersonalData(firstName, secondName, surname, gender, birthDate, pesel);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullBirthDate_shouldThrowIAE() {
        // given
        String firstName = "Bolo";
        String secondName = "Bolerino";
        String surname = "Bolczyński";
        Gender gender = Gender.MAN;
        LocalDate birthDate = null;
        Pesel pesel = new Pesel("93031909099");

        // when
        new PersonalData(firstName, secondName, surname, gender, birthDate, pesel);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullPesel_shouldThrowIAE() {
        // given
        String firstName = "Bolo";
        String secondName = "Bolerino";
        String surname = "Bolczyński";
        Gender gender = Gender.MAN;
        LocalDate birthDate = LocalDate.parse("1993-03-19");
        Pesel pesel = null;

        // when
        new PersonalData(firstName, secondName, surname, gender, birthDate, pesel);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoPersonalDataObjectsWithSamePesel_shouldReturnTrue() {
        // given
        PersonalData personalData1 = new PersonalData("Bolo", "Bolerino", "Bolczyński", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        PersonalData personalData2 = new PersonalData("Bajer", null, "Bajerowicz", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));

        // when
        boolean equalsResult = personalData1.equals(personalData2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoPersonalDataObjectsWithDifferentPesel_shouldReturnFalse() {
        // given
        PersonalData personalData1 = new PersonalData("Bolo", "Bolerino", "Bolczyński", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        PersonalData personalData2 = new PersonalData("Bolo", "Bolerino", "Bolczyński", Gender.MAN,
                LocalDate.parse("1995-03-17"), new Pesel("95031709099"));

        // when
        boolean equalsResult = personalData1.equals(personalData2);

        // then
        Assert.assertFalse(equalsResult);
    }*/
}
