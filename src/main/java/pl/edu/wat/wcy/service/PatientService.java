package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.model.person.account.Account;
import pl.edu.wat.wcy.model.person.account.Email;
import pl.edu.wat.wcy.model.person.data.Pesel;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.AccountRepository;
import pl.edu.wat.wcy.repository.PatientRepository;

import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, AccountRepository accountRepository) {
        this.patientRepository = patientRepository;
        this.accountRepository = accountRepository;
    }

    public Optional<Patient> findPatientByPesel(Pesel pesel) {
        return patientRepository.findByPersonalDataPesel(pesel);
    }

    public Optional<Patient> findPatientByName(String firstName, String surname) {
        return patientRepository.findByPersonalDataNameFirstNameAndPersonalDataNameSurname(firstName, surname);
    }

    public void savePatient(Patient patient) {
        // TODO: 03.12.2018 to dla sekretarki - zapisanie pacjenta bez konta
        Pesel pesel = patient.getPersonalData().getPesel();
        Optional<Patient> existingPatient = patientRepository.findByPersonalDataPesel(pesel);
        if (!existingPatient.isPresent()) patientRepository.save(patient);
        else throw new IllegalArgumentException("Patient with this pesel " + pesel + " already exists.");
    }

    public void saveAccount(Account account) {
        Email email = account.getEmail();
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        if (!existingAccount.isPresent()) accountRepository.save(account);
        else throw new IllegalArgumentException("Account with this email " + email + " already exists.");
    }
}
