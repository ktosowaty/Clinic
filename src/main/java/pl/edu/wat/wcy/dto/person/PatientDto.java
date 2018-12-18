package pl.edu.wat.wcy.dto.person;

import pl.edu.wat.wcy.model.person.data.Gender;

import java.time.LocalDate;

public class PatientDto {
    private String firstName;

    private String surname;

    private String secondName;

    private Gender gender;

    private String birthDateStr;

    private String peselStr;

    private String street;

    private String zipCodeStr;

    private String city;

    private String provinceStr;

    private String phoneNumberStr;

    private PatientDto() {}

    public PatientDto(String firstName, String surname, String secondName, Gender gender, String birthDateStr,
                      String peselStr, String street, String zipCodeStr, String city, String provinceStr, String phoneNumberStr) {
        this.firstName = firstName;
        this.surname = surname;
        this.secondName = secondName;
        this.gender = gender;
        this.birthDateStr = birthDateStr;
        this.peselStr = peselStr;
        this.street = street;
        this.zipCodeStr = zipCodeStr;
        this.city = city;
        this.provinceStr = provinceStr;
        this.phoneNumberStr = phoneNumberStr;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getBirthDateStr() {
        return birthDateStr;
    }

    public String getPeselStr() {
        return peselStr;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCodeStr() {
        return zipCodeStr;
    }

    public String getCity() {
        return city;
    }

    public String getProvinceStr() {
        return provinceStr;
    }

    public String getPhoneNumberStr() {
        return phoneNumberStr;
    }
}
