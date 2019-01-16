package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.person.PatientDto;
import pl.edu.wat.wcy.dto.person.PatientProjection;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.person.data.*;
import pl.edu.wat.wcy.model.person.data.address.Address;
import pl.edu.wat.wcy.model.person.data.address.Province;
import pl.edu.wat.wcy.model.person.data.address.ZipCode;
import pl.edu.wat.wcy.model.person.data.name.FullName;
import pl.edu.wat.wcy.model.person.data.name.Name;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.model.person.user.User;
import pl.edu.wat.wcy.repository.PatientRepository;
import pl.edu.wat.wcy.repository.UserRepository;
import pl.edu.wat.wcy.security.AuthenticatedUser;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public PatientProjection findPatientByPesel(String peselStr) {
        Pesel pesel = new Pesel(peselStr);
        return patientRepository.findByPesel(pesel)
                .orElseThrow(() -> new ResourceNotFoundException("patient", pesel));
    }

    public PatientProjection findPatientByName(String firstName, String surname) {
        Name name = new Name(firstName, surname);
        return patientRepository.findByFullNameName(name)
                .orElseThrow(() -> new ResourceNotFoundException("patient", name));
    }

    public void savePatientBySecretary(PatientDto patientDto) {
        Patient patient = createPatient(patientDto);
        patientRepository.save(patient);
    }

    public void savePatientByHimself(AuthenticatedUser authenticatedUser, PatientDto patientDto) {
        Patient patient = createPatient(patientDto);
        patientRepository.save(patient);
        User user = findUser(authenticatedUser.getId());
        user.setPerson(patient);
        userRepository.save(user);
    }

    public List<PatientProjection> findPatients() {
        return patientRepository.findAllProjectedBy();
    }

    private Patient createPatient(PatientDto patientDto) {
        FullName fullName = createFullName(patientDto);
        Pesel pesel = new Pesel(patientDto.getPeselStr());
        checkIfPatientExist(pesel);
        Gender gender = patientDto.getGender();
        LocalDate birthDate = LocalDate.parse(patientDto.getBirthDateStr());
        Address address = createAddress(patientDto);
        PhoneNumber phoneNumber = new PhoneNumber(patientDto.getPhoneNumberStr());
        return new Patient(fullName, pesel, gender, birthDate, address, phoneNumber);
    }

    private FullName createFullName(PatientDto patientDto) {
        Name name = createName(patientDto);
        String secondName = patientDto.getSecondName();
        return new FullName(name, secondName);
    }

    private Name createName(PatientDto patientDto) {
        String firstName = patientDto.getFirstName();
        String surname = patientDto.getSurname();
        return new Name(firstName, surname);
    }

    private void checkIfPatientExist(Pesel pesel) {
        Optional<PatientProjection> existingPatient = patientRepository.findByPesel(pesel);
        if (existingPatient.isPresent())
            throw new IllegalArgumentException("Patient with given pesel '" + pesel + "' already exists.");
    }

    private Address createAddress(PatientDto patientDto) {
        String street = patientDto.getStreet();
        ZipCode zipCode = new ZipCode(patientDto.getZipCodeStr());
        String city = patientDto.getCity();
        Province province = getProvince(patientDto);
        return new Address(street, zipCode, city, province);
    }

    private Province getProvince(PatientDto patientDto) {
        String provinceStr = patientDto.getProvinceStr().trim().toLowerCase();
        return Province.fromString(provinceStr);
    }

    private User findUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", userId));
    }
}
