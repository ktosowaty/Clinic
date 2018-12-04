package pl.edu.wat.wcy.diseases;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import pl.edu.wat.wcy.model.disease.Disease;
import pl.edu.wat.wcy.model.disease.Medicine;
import pl.edu.wat.wcy.model.disease.MedicineDisease;

public class MedicineDiseaseTest {
    /*@Test
    public void constructor_givenProperData_shouldCreateObject() {
        // given
        Medicine medicine = Mockito.mock(Medicine.class);
        Disease disease = Mockito.mock(Disease.class);
        String remarks = "pikej";

        // when
        MedicineDisease medicineDisease = new MedicineDisease(medicine, disease, remarks);

        // then
        Assert.assertEquals(medicine, medicineDisease.getMedicine());
        Assert.assertEquals(disease, medicineDisease.getDisease());
        Assert.assertEquals(remarks, medicineDisease.getRemarks());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullMedicine_shouldThrowIAE() {
        // given
        Medicine medicine = null;
        Disease disease = Mockito.mock(Disease.class);
        String remarks = "pikej";

        // when
        new MedicineDisease(medicine, disease, remarks);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullDisease_shouldThrowIAE() {
        // given
        Medicine medicine = Mockito.mock(Medicine.class);
        Disease disease = null;
        String remarks = "pikej";

        // when
        new MedicineDisease(medicine, disease, remarks);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoObjectsWithSameId_shouldReturnTrue() {
        // given
        Medicine medicine = Mockito.mock(Medicine.class);
        Disease disease = Mockito.mock(Disease.class);
        String remarks = "pikej";
        MedicineDisease medicineDisease1 = new MedicineDisease(medicine, disease, remarks);
        ReflectionTestUtils.setField(medicineDisease1, "id", 123L);
        MedicineDisease medicineDisease2 = new MedicineDisease(medicine, disease, remarks);
        ReflectionTestUtils.setField(medicineDisease2, "id", 123L);

        // when
        boolean equalsResult = medicineDisease1.equals(medicineDisease2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoObjectsWithDifferentIds_shouldReturnFalse() {
        // given
        Medicine medicine = Mockito.mock(Medicine.class);
        Disease disease = Mockito.mock(Disease.class);
        String remarks = "pikej";
        MedicineDisease medicineDisease1 = new MedicineDisease(medicine, disease, remarks);
        ReflectionTestUtils.setField(medicineDisease1, "id", 123L);
        MedicineDisease medicineDisease2 = new MedicineDisease(medicine, disease, remarks);
        ReflectionTestUtils.setField(medicineDisease2, "id", 8763421L);

        // when
        boolean equalsResult = medicineDisease1.equals(medicineDisease2);

        // then
        Assert.assertFalse(equalsResult);
    }*/
}
