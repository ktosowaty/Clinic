package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.PatientDto;
import pl.edu.wat.wcy.model.person.data.*;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.PatientRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient findPatientByPesel(String peselStr) {
        Pesel pesel = new Pesel(peselStr);
        Optional<Patient> patient = patientRepository.findByPesel(pesel);
        if (!patient.isPresent()) throw new IllegalArgumentException("Patient with given pesel '" + pesel + "' doesn't exist.");
        return patient.get();
    }

    public Patient findPatientByName(String firstName, String surname) {
        Name name = new Name(firstName, surname);
        Optional<Patient> patient = patientRepository.findByFullNameName(name);
        if (!patient.isPresent()) throw new IllegalArgumentException("Patient with given name '"
                + firstName + " " + surname + "' doesn't exist.");
        return patient.get();
    }

    public void savePatient(PatientDto patientDto) {
        FullName fullName = createFullName(patientDto);
        Gender gender = patientDto.getGender();
        LocalDate birthDate = patientDto.getBirthDate();
        Pesel pesel = new Pesel(patientDto.getPeselStr());
        checkIfPatientExist(pesel);
        Address address = createAddress(patientDto);
        PhoneNumber phoneNumber = new PhoneNumber(patientDto.getPhoneNumberStr());
        Patient patient = new Patient(fullName, gender, birthDate, pesel, address, phoneNumber);
        patientRepository.save(patient);
    }

    private FullName createFullName(PatientDto patientDto) {
        String firstName = patientDto.getFirstName();
        String surname = patientDto.getSurname();
        Name name = new Name(firstName, surname);
        String secondName = patientDto.getSecondName();
        return new FullName(name, secondName);
    }

    private void checkIfPatientExist(Pesel pesel) {
        Optional<Patient> existingPatient = patientRepository.findByPesel(pesel);
        if (existingPatient.isPresent())
            throw new IllegalArgumentException("Patient with given pesel '" + pesel + "' already exists.");
    }

    private Address createAddress(PatientDto patientDto) {
        String street = patientDto.getStreet();
        ZipCode zipCode = new ZipCode(patientDto.getZipCodeStr());
        String city = patientDto.getCity();
        String province = patientDto.getProvince();
        return new Address(street, zipCode, city, province);
    }
}
