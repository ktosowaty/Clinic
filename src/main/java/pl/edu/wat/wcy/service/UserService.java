package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.user.UserDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.person.account.*;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.UserRepository;
import pl.edu.wat.wcy.repository.PatientRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public UserService(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    public void saveUser(UserDto userDto) {
        Username username = getUsername(userDto);
        Password password = new Password(userDto.getPlainPassword());
        Email email = getEmail(userDto);
        UserType userType = userDto.getUserType();
        //Patient patient = findPatient(userDto.getPersonId());
        User user = new User(username, password, email, userType);
        userRepository.save(user);
        //patient.setUser(user);
        //patientRepository.save(patient);
    }

//    public String login(LoginDto loginDto) {
//        String usernameStr = loginDto.getUsername();
//        String plainPassword = loginDto.getPassword();
//        checkCredentials(usernameStr, plainPassword);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usernameStr, plainPassword);
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return tokenProvider.generateToken(authentication);
//    }

    private Username getUsername(UserDto userDto) {
        Username username = new Username(userDto.getUsername());
        Optional<User> existingAccount = userRepository.findByUsername(username);
        if (existingAccount.isPresent())
            throw new IllegalArgumentException("User with given username '" + username + "' already exists.");
        return username;
    }

    private Email getEmail(UserDto userDto) {
        Email email = new Email(userDto.getEmail());
        Optional<User> existingAccount = userRepository.findByEmail(email);
        if (existingAccount.isPresent())
            throw new IllegalArgumentException("User with given email '" + email + "' already exists.");
        return email;
    }

    private Patient findPatient(long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("patient",  patientId));
    }

    private void checkCredentials(String usernameStr, String plainPassword) {
        Username username = new Username(usernameStr);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("user", username));
        if (!user.getPassword().matches(plainPassword)) throw new IllegalArgumentException("Wrong password.");
    }
}
