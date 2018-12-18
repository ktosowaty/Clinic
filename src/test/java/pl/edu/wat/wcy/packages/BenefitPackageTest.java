package pl.edu.wat.wcy.packages;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import pl.edu.wat.wcy.model.benefit.BenefitPackage;
import pl.edu.wat.wcy.model.benefit.Money;

public class BenefitPackageTest {
    /*@Test
    public void constructor_givenProperData_shouldCreateObject() {
        // given
        String name = "badziebadla";
        Money cost = new Money(10000);

        // when
        BenefitPackage benefitPackage = new BenefitPackage(name, cost);
        ReflectionTestUtils.setField(benefitPackage, "id", 123L);

        // then
        Assert.assertEquals(123L, benefitPackage.getId());
        Assert.assertEquals(name, benefitPackage.getName());
        Assert.assertEquals(cost, benefitPackage.getYearCost());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullName_shouldThrowIAE() {
        // given
        String name = null;
        Money cost = new Money(10000);

        // when
        new BenefitPackage(name, cost);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullCost_shouldThrowIAE() {
        // given
        String name = "badziebadla";
        Money cost = null;

        // when
        new BenefitPackage(name, cost);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoPackagesWithSameId_shouldReturnTrue() {
        // given
        BenefitPackage benefitPackage1 = new BenefitPackage("qwerty", new Money(123456));
        ReflectionTestUtils.setField(benefitPackage1, "id", 123L);
        BenefitPackage benefitPackage2 = new BenefitPackage("asdfgh", new Money(987654));
        ReflectionTestUtils.setField(benefitPackage2, "id", 123L);

        // when
        boolean equalsResult = benefitPackage1.equals(benefitPackage2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoPackagesWithDifferentIds_shouldReturnFalse() {
        // given
        BenefitPackage benefitPackage1 = new BenefitPackage("qwerty", new Money(123456));
        ReflectionTestUtils.setField(benefitPackage1, "id", 123L);
        BenefitPackage benefitPackage2 = new BenefitPackage("asdfgh", new Money(987654));
        ReflectionTestUtils.setField(benefitPackage2, "id", 987L);

        // when
        boolean equalsResult = benefitPackage1.equals(benefitPackage2);

        // then
        Assert.assertFalse(equalsResult);
    }*/
}
