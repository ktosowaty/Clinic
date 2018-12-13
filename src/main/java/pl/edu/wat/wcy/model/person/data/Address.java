package pl.edu.wat.wcy.model.person.data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

public class Address {
    @Column
    private String street;

    @Embedded
    private ZipCode zipCode;

    @Column
    private String city;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Province province;

    private Address() {
        // JPA
    }

    public Address(String street, ZipCode zipCode, String city, Province province) {
        this.street = requireNonNull(street, "street");
        this.zipCode = requireNonNull(zipCode, "zip code");
        this.city = requireNonNull(city, "city");
        this.province = requireNonNull(province, "province");
    }

    public String getStreet() {
        return street;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public Province getProvince() {
        return province;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(city, address.city) &&
                Objects.equals(province, address.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode, city, province);
    }
}
