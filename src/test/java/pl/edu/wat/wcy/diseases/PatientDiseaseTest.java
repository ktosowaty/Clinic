package pl.edu.wat.wcy.diseases;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import pl.edu.wat.wcy.model.disease.Disease;
import pl.edu.wat.wcy.model.disease.PatientDisease;
import pl.edu.wat.wcy.model.person.patient.Patient;

public class PatientDiseaseTest {
    /*@Test
    public void constructor_givenProperData_shouldCreateObject() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Disease disease = Mockito.mock(Disease.class);
        String remarks = "pikej";

        // when
        PatientDisease patientDisease = new PatientDisease(patient, disease, remarks);

        // then
        Assert.assertEquals(patient, patientDisease.getPatient());
        Assert.assertEquals(disease, patientDisease.getDisease());
        Assert.assertEquals(remarks, patientDisease.getRemarks());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullPatient_shouldThrowIAE() {
        // given
        Patient patient = null;
        Disease disease = Mockito.mock(Disease.class);
        String remarks = "pikej";

        // when
        new PatientDisease(patient, disease, remarks);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullDisease_shouldThrowIAE() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Disease disease = null;
        String remarks = "pikej";

        // when
        new PatientDisease(patient, disease, remarks);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoObjectsWthSameId_shouldReturnTrue() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Disease disease = Mockito.mock(Disease.class);
        String remarks = "pikej";
        PatientDisease patientDisease1 = new PatientDisease(patient, disease, remarks);
        ReflectionTestUtils.setField(patientDisease1, "id", 123L);
        PatientDisease patientDisease2 = new PatientDisease(patient, disease, remarks);
        ReflectionTestUtils.setField(patientDisease2, "id", 123L);

        // when
        boolean equalsResult = patientDisease1.equals(patientDisease2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoObjectsWthDifferentIds_shouldReturnFalse() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        Disease disease = Mockito.mock(Disease.class);
        String remarks = "pikej";
        PatientDisease patientDisease1 = new PatientDisease(patient, disease, remarks);
        ReflectionTestUtils.setField(patientDisease1, "id", 123L);
        PatientDisease patientDisease2 = new PatientDisease(patient, disease, remarks);
        ReflectionTestUtils.setField(patientDisease2, "id",  43862L);

        // when
        boolean equalsResult = patientDisease1.equals(patientDisease2);

        // then
        Assert.assertFalse(equalsResult);
    }*/
}
