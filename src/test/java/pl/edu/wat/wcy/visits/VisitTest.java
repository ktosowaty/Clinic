package pl.edu.wat.wcy.visits;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.Visit;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.patient.Patient;

import java.time.LocalDateTime;

public class VisitTest {
    @Test
    public void constructor_givenProperData_shouldCreateVisitObject() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Doctor doctor = Mockito.mock(Doctor.class);
        LocalDateTime visitStart = LocalDateTime.parse("2018-10-10T10:10:00");
        Money cost = new Money(10000);

        // when
        Visit visit = new Visit(patient, doctor, visitStart, cost);

        // then
        Assert.assertEquals(patient, visit.getPatient());
        Assert.assertEquals(doctor, visit.getDoctor());
        Assert.assertEquals(visitStart, visit.getVisitStart());
        Assert.assertEquals(cost, visit.getCost());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullPatient_shouldThrowIAE() {
        // given
        Patient patient = null;
        Doctor doctor = Mockito.mock(Doctor.class);
        LocalDateTime visitStart = LocalDateTime.parse("2018-10-10T10:10:00");
        Money cost = new Money(10000);

        // when
        new Visit(patient, doctor, visitStart, cost);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullDoctor_shouldThrowIAE() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Doctor doctor = null;
        LocalDateTime visitStart = LocalDateTime.parse("2018-10-10T10:10:00");
        Money cost = new Money(10000);

        // when
        new Visit(patient, doctor, visitStart, cost);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullVisitStart_shouldThrowIAE() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Doctor doctor = Mockito.mock(Doctor.class);
        LocalDateTime visitStart = null;
        Money cost = new Money(10000);

        // when
        new Visit(patient, doctor, visitStart, cost);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullCost_shouldThrowIAE() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Doctor doctor = Mockito.mock(Doctor.class);
        LocalDateTime visitStart = LocalDateTime.parse("2018-10-10T10:10:00");
        Money cost = null;

        // when
        new Visit(patient, doctor, visitStart, cost);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoVisitsWIthSameId_shouldReturnTrue() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Doctor doctor = Mockito.mock(Doctor.class);
        LocalDateTime visitStart = LocalDateTime.parse("2018-10-10T10:10:00");
        Money cost = new Money(10000);
        Visit visit1 = new Visit(patient, doctor, visitStart, cost);
        ReflectionTestUtils.setField(visit1, "id", 123L);
        Visit visit2 = new Visit(patient, doctor, visitStart, cost);
        ReflectionTestUtils.setField(visit2, "id", 123L);

        // when
        boolean equalsResult = visit1.equals(visit2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoVisitsWIthDifferentIds_shouldReturnFalse() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Doctor doctor = Mockito.mock(Doctor.class);
        LocalDateTime visitStart = LocalDateTime.parse("2018-10-10T10:10:00");
        Money cost = new Money(10000);
        Visit visit1 = new Visit(patient, doctor, visitStart, cost);
        ReflectionTestUtils.setField(visit1, "id", 123L);
        Visit visit2 = new Visit(patient, doctor, visitStart, cost);
        ReflectionTestUtils.setField(visit2, "id", 432567L);

        // when
        boolean equalsResult = visit1.equals(visit2);

        // then
        Assert.assertFalse(equalsResult);
    }
}
