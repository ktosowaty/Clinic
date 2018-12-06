package pl.edu.wat.wcy.dto;

import pl.edu.wat.wcy.model.person.data.Gender;

import java.time.LocalDate;

public class PatientDto {
    private String firstName;

    private String surname;

    private String secondName;

    private Gender gender;

    private LocalDate birthDate;

    private String peselStr;

    private String street;

    private String zipCodeStr;

    private String city;

    private String province;

    private String phoneNumberStr;

    private PatientDto() {}

    public PatientDto(String firstName, String surname, String secondName, Gender gender, LocalDate birthDate,
                      String peselStr, String street, String zipCodeStr, String city, String province, String phoneNumberStr) {
        this.firstName = firstName;
        this.surname = surname;
        this.secondName = secondName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.peselStr = peselStr;
        this.street = street;
        this.zipCodeStr = zipCodeStr;
        this.city = city;
        this.province = province;
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

    public LocalDate getBirthDate() {
        return birthDate;
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

    public String getProvince() {
        return province;
    }

    public String getPhoneNumberStr() {
        return phoneNumberStr;
    }
}
