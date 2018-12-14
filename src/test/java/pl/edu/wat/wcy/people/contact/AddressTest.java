package pl.edu.wat.wcy.people.contact;

public class AddressTest {
    /*@Test
    public void constructor_givenProperData_shouldCreateAddressObject() {
        // given
        String street = "bolczynska 15";
        ZipCode zipCode = new ZipCode("12-345");
        String city = "warszawa";
        String province = "mazowieckie";

        // when
        Address address = new Address(street, zipCode, city, province);

        // then
        Assert.assertEquals(street, address.getStreet());
        Assert.assertEquals(zipCode, address.getZipCode());
        Assert.assertEquals(city, address.getCity());
        Assert.assertEquals(province, address.getProvince());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullStreet_shouldThrowIAE() {
        // given
        String street = null;
        ZipCode zipCode = new ZipCode("12-345");
        String city = "warszawa";
        String province = "mazowieckie";

        // when
        new Address(street, zipCode, city, province);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullZipCode_shouldThrowIAE() {
        // given
        String street = "bolczynska 15";
        ZipCode zipCode = null;
        String city = "warszawa";
        String province = "mazowieckie";

        // when
        new Address(street, zipCode, city, province);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullCity_shouldThrowIAE() {
        // given
        String street = "bolczynska 15";
        ZipCode zipCode = new ZipCode("12-345");
        String city = null;
        String province = "mazowieckie";

        // when
        new Address(street, zipCode, city, province);

        // then
        // throw IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_givenNullProvince_shouldThrowIAE() {
        // given
        String street = "bolczynska 15";
        ZipCode zipCode = new ZipCode("12-345");
        String city = "warszawa";
        String province = null;

        // when
        new Address(street, zipCode, city, province);

        // then
        // throw IAE
    }

    @Test
    public void equals_givenTwoSameAddresses_shouldReturnTrue() {
        // given
        Address address1 = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        Address address2 = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");

        // when
        boolean equalsResult = address1.equals(address2);

        // then
        Assert.assertTrue(equalsResult);
    }

    @Test
    public void equals_givenTwoAddressesWithDifferentStreet_shouldReturnFalse() {
        // given
        Address address1 = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        Address address2 = new Address("bajerowa 99/88", new ZipCode("12-345"), "warszawa", "mazowieckie");

        // when
        boolean equalsResult = address1.equals(address2);

        // then
        Assert.assertFalse(equalsResult);
    }

    @Test
    public void equals_givenTwoAddressesWithDifferentZipCode_shouldReturnFalse() {
        // given
        Address address1 = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        Address address2 = new Address("bolczynska 15", new ZipCode("98-765"), "warszawa", "mazowieckie");

        // when
        boolean equalsResult = address1.equals(address2);

        // then
        Assert.assertFalse(equalsResult);
    }

    @Test
    public void equals_givenTwoAddressesWithDifferentCity_shouldReturnFalse() {
        // given
        Address address1 = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        Address address2 = new Address("bolczynska 15", new ZipCode("12-345"), "marki", "mazowieckie");

        // when
        boolean equalsResult = address1.equals(address2);

        // then
        Assert.assertFalse(equalsResult);
    }

    @Test
    public void equals_givenTwoAddressesWithDifferentProvince_shouldReturnFalse() {
        // given
        Address address1 = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "mazowieckie");
        Address address2 = new Address("bolczynska 15", new ZipCode("12-345"), "warszawa", "podlaskie");

        // when
        boolean equalsResult = address1.equals(address2);

        // then
        Assert.assertFalse(equalsResult);
    }*/
}
