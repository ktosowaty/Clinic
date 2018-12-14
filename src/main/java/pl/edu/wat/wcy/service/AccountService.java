package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.AccountDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.person.account.*;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.AccountRepository;
import pl.edu.wat.wcy.repository.PatientRepository;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, PatientRepository patientRepository) {
        this.accountRepository = accountRepository;
        this.patientRepository = patientRepository;
    }

    // TODO: 05.12.2018 rozszerzyć na doctor i secretary? niespójne
    // TODO: 14.12.2018 poprawic zapis w bazie
    public void saveAccount(AccountDto accountDto) {
        Login login = new Login(accountDto.getLoginStr());
        Password password = new Password(accountDto.getPlainPassword());
        Email email = new Email(accountDto.getEmailStr());
        checkIfAccountExist(email);
        AccountType accountType = accountDto.getAccountType();
        Patient patient = findPatient(accountDto.getPersonId());
        Account account = new Account(login, password, email, accountType, patient);
        accountRepository.save(account);
        patientRepository.save(patient);
    }

    private void checkIfAccountExist(Email email) {
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        if (existingAccount.isPresent())
            throw new IllegalArgumentException("Account with given email '" + email + "' already exists.");
    }

    // TODO: 05.12.2018 jw
    private Patient findPatient(long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (!patient.isPresent()) throw new ResourceNotFoundException("patient",  patientId);
        return patient.get();
    }
}
