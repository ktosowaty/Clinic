package pl.edu.wat.wcy.diseases;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import pl.edu.wat.wcy.model.disease.Disease;

public class DiseaseTest {
    @Test
    public void constructor_givenProperValues_shouldCreateDiseaseObject() {
        // given
        String name = "Pierdolec pospolity";
        String description = "Choroba objawia się niedojebaniem mózgowym";

        // when
        Disease disease = new Disease(name, description);
        ReflectionTestUtils.setField(disease, "id", 123L);

        // then
        Assert.assertEquals(123L, disease.getId());
        Assert.assertEquals("Pierdolec pospolity", disease.getName());
        Assert.assertEquals("Choroba objawia się niedojebaniem mózgowym", disease.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullName_shouldThrowIAE() {
        // given
        String name = null;
        String description = "Choroba objawia się niedojebaniem mózgowym";

        // when
        new Disease(name, description);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoDiseasesWithSameId_shouldReturnTrue() {
        // given
        Disease disease1 = new Disease("Buka", null);
        ReflectionTestUtils.setField(disease1, "id", 123L);
        Disease disease2 = new Disease("Pikej", "profesor wat xD");
        ReflectionTestUtils.setField(disease2, "id", 123L);

        // when
        boolean equalsResult = disease1.equals(disease2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoDiseasesWithDifferentIds_shouldReturnFalse() {
        // given
        Disease disease1 = new Disease("Buka", null);
        ReflectionTestUtils.setField(disease1, "id", 123L);
        Disease disease2 = new Disease("Pikej", "profesor wat xD");
        ReflectionTestUtils.setField(disease2, "id", 456L);

        // when
        boolean equalsResult = disease1.equals(disease2);

        // then
        Assert.assertFalse(equalsResult);
    }
}
