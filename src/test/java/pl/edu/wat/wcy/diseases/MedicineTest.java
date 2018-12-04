package pl.edu.wat.wcy.diseases;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import pl.edu.wat.wcy.model.disease.Medicine;

public class MedicineTest {
    @Test
    public void constructor_givenProperValues_shouldCreateMedicineObject() {
        // given
        String name = "Wpierdol forte";
        String description = "Lek bólowy, gorączkowy, zapalny.";
        String sideEffects = null;

        // when
        Medicine medicine = new Medicine(name, description, sideEffects);
        ReflectionTestUtils.setField(medicine, "id", 123L);

        // then
        Assert.assertEquals(123L, medicine.getId());
        Assert.assertEquals("Wpierdol forte", medicine.getName());
        Assert.assertEquals("Lek bólowy, gorączkowy, zapalny.", medicine.getDescription());
        Assert.assertNull(medicine.getSideEffects());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullName_shouldThrowIAE() {
        // given
        String name = null;
        String description = "Lek bólowy, gorączkowy, zapalny.";
        String sideEffects = null;

        // when
        new Medicine(name, description, sideEffects);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoMedicinesWithSameId_shouldReturnTrue() {
        // given
        Medicine medicine1 = new Medicine("Wpierdol forte", "Lek bólowy, gorączkowy, zapalny.", null);
        ReflectionTestUtils.setField(medicine1, "id", 123L);
        Medicine medicine2 = new Medicine("Wpierdol forte", "Lek bólowy, gorączkowy, zapalny.", null);
        ReflectionTestUtils.setField(medicine2, "id", 123L);

        // when
        boolean equalsResult = medicine1.equals(medicine2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoDiseasesWithDifferentIds_shouldReturnFalse() {
        // given
        Medicine medicine1 = new Medicine("Wpierdol forte", "Lek bólowy, gorączkowy, zapalny.", null);
        ReflectionTestUtils.setField(medicine1, "id", 123L);
        Medicine medicine2 = new Medicine("Wpierdol forte", "Lek bólowy, gorączkowy, zapalny.", null);
        ReflectionTestUtils.setField(medicine2, "id", 456L);

        // when
        boolean equalsResult = medicine1.equals(medicine2);

        // then
        Assert.assertFalse(equalsResult);
    }
}
