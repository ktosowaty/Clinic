package pl.edu.wat.wcy.people;

public class DoctorTest {
    /*@Test
    public void constructor_givenProperData_shouldCreateDoctor() {
        // given
        PersonalData personalData = new PersonalData("Bolo", "Bolerino", "Bolczyński", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        PhoneNumber phoneNumber = new PhoneNumber("123456789");
        Specialization specialization = Specialization.CARDIOLOGIST;

        // when
        Doctor doctor = new Doctor(personalData, phoneNumber, specialization);

        // then
        Assert.assertEquals(personalData, doctor.getPersonalData());
        Assert.assertEquals(phoneNumber, doctor.getPhoneNumber());
        Assert.assertEquals(specialization, doctor.getSpecialization());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullPersonalData_shouldThrowIAE() {
        // given
        PersonalData personalData = null;
        PhoneNumber phoneNumber = new PhoneNumber("123456789");
        Specialization specialization = Specialization.CARDIOLOGIST;

        // when
        new Doctor(personalData, phoneNumber, specialization);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullPhoneNumber_shouldThrowIAE() {
        // given
        PersonalData personalData = new PersonalData("Bolo", "Bolerino", "Bolczyński", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        PhoneNumber phoneNumber = null;
        Specialization specialization = Specialization.CARDIOLOGIST;

        // when
        new Doctor(personalData, phoneNumber, specialization);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullSpecialization_shouldThrowIAE() {
        // given
        PersonalData personalData = new PersonalData("Bolo", "Bolerino", "Bolczyński", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        PhoneNumber phoneNumber = new PhoneNumber("123456789");
        Specialization specialization = null;

        // when
        new Doctor(personalData, phoneNumber, specialization);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoDoctorsWithSameId_shouldReturnTrue() {
        // given
        PersonalData personalData = new PersonalData("Bolo", "Bolerino", "Bolczyński", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        PhoneNumber phoneNumber = new PhoneNumber("123456789");
        Specialization specialization = Specialization.CARDIOLOGIST;
        Doctor doctor1 = new Doctor(personalData, phoneNumber, specialization);
        ReflectionTestUtils.setField(doctor1, "id", 123L);
        Doctor doctor2 = new Doctor(personalData, phoneNumber, specialization);
        ReflectionTestUtils.setField(doctor2, "id", 123L);

        // when
        boolean equalsResult = doctor1.equals(doctor2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoDoctorsWithDifferentIds_shouldReturnFalse() {
        // given
        PersonalData personalData = new PersonalData("Bolo", "Bolerino", "Bolczyński", Gender.MAN,
                LocalDate.parse("1993-03-19"), new Pesel("93031909099"));
        PhoneNumber phoneNumber = new PhoneNumber("123456789");
        Specialization specialization = Specialization.CARDIOLOGIST;
        Doctor doctor1 = new Doctor(personalData, phoneNumber, specialization);
        ReflectionTestUtils.setField(doctor1, "id", 123L);
        Doctor doctor2 = new Doctor(personalData, phoneNumber, specialization);
        ReflectionTestUtils.setField(doctor1, "id", 98742L);

        // when
        boolean equalsResult = doctor1.equals(doctor2);

        // then
        Assert.assertFalse(equalsResult);
    }*/
}
