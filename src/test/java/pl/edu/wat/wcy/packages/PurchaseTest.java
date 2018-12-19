package pl.edu.wat.wcy.packages;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import pl.edu.wat.wcy.model.benefit.BenefitPackage;
import pl.edu.wat.wcy.model.benefit.Purchase;
import pl.edu.wat.wcy.model.person.patient.Patient;

import java.time.LocalDate;

public class PurchaseTest {
    /*@Test
    public void constructor_givenProperData_shouldCreatePurchase() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        BenefitPackage benefitPackage = Mockito.mock(BenefitPackage.class);
        LocalDate fromDate = LocalDate.parse("2018-10-01");
        LocalDate toDate = LocalDate.parse("2019-10-01");

        // when
        Purchase purchase = new Purchase(patient, benefitPackage, fromDate, toDate);

        // then
        Assert.assertEquals(patient, purchase.getPatient());
        Assert.assertEquals(benefitPackage, purchase.getBenefitPackage());
        Assert.assertEquals(fromDate, purchase.getFromDate());
        Assert.assertEquals(toDate, purchase.getToDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullPatient_shouldThrowIAE() {
        // given
        Patient patient = null;
        BenefitPackage benefitPackage = Mockito.mock(BenefitPackage.class);
        LocalDate fromDate = LocalDate.parse("2018-10-01");
        LocalDate toDate = LocalDate.parse("2019-10-01");

        // when
        new Purchase(patient, benefitPackage, fromDate, toDate);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullMedicalPackage_shouldThrowIAE() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        BenefitPackage benefitPackage = null;
        LocalDate fromDate = LocalDate.parse("2018-10-01");
        LocalDate toDate = LocalDate.parse("2019-10-01");

        // when
        new Purchase(patient, benefitPackage, fromDate, toDate);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullFromDate_shouldThrowIAE() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        BenefitPackage benefitPackage = Mockito.mock(BenefitPackage.class);
        LocalDate fromDate = null;
        LocalDate toDate = LocalDate.parse("2019-10-01");

        // when
        new Purchase(patient, benefitPackage, fromDate, toDate);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullToDate_shouldShouldThrowIAE() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        BenefitPackage benefitPackage = Mockito.mock(BenefitPackage.class);
        LocalDate fromDate = LocalDate.parse("2018-10-01");
        LocalDate toDate = null;

        // when
        new Purchase(patient, benefitPackage, fromDate, toDate);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoPurchasesWithSameId_shouldReturnTrue() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        BenefitPackage benefitPackage = Mockito.mock(BenefitPackage.class);
        LocalDate fromDate = LocalDate.parse("2018-10-01");
        LocalDate toDate = LocalDate.parse("2019-10-01");
        Purchase purchase1 = new Purchase(patient, benefitPackage, fromDate, toDate);
        ReflectionTestUtils.setField(purchase1, "id", 123L);
        Purchase purchase2 = new Purchase(patient, benefitPackage, fromDate, toDate);
        ReflectionTestUtils.setField(purchase2, "id", 123L);

        // when
        boolean equalsResult = purchase1.equals(purchase2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoPurchasesWithDifferentIds_shouldReturnFalse() {
        // given
        Patient patient = Mockito.mock(Patient.class);
        BenefitPackage benefitPackage = Mockito.mock(BenefitPackage.class);
        LocalDate fromDate = LocalDate.parse("2018-10-01");
        LocalDate toDate = LocalDate.parse("2019-10-01");
        Purchase purchase1 = new Purchase(patient, benefitPackage, fromDate, toDate);
        ReflectionTestUtils.setField(purchase1, "id", 123L);
        Purchase purchase2 = new Purchase(patient, benefitPackage, fromDate, toDate);
        ReflectionTestUtils.setField(purchase2, "id", 4326968L);

        // when
        boolean equalsResult = purchase1.equals(purchase2);

        // then
        Assert.assertFalse(equalsResult);
    }*/
}
