package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.user.UserRequestDto;
import pl.edu.wat.wcy.dto.user.UserResponseDto;
import pl.edu.wat.wcy.model.person.user.*;
import pl.edu.wat.wcy.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        Username username = getUsername(userRequestDto);
        Password password = new Password(userRequestDto.getPlainPassword());
        Email email = getEmail(userRequestDto);
        UserType userType = userRequestDto.getUserType();
        User user = new User(username, password, email, userType);
        userRepository.save(user);
        return new UserResponseDto(username, email, userType);
    }

    private Username getUsername(UserRequestDto userRequestDto) {
        Username username = new Username(userRequestDto.getUsername());
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent())
            throw new IllegalArgumentException("User with given username '" + username + "' already exists.");
        return username;
    }

    private Email getEmail(UserRequestDto userRequestDto) {
        Email email = new Email(userRequestDto.getEmail());
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent())
            throw new IllegalArgumentException("User with given email '" + email + "' already exists.");
        return email;
    }
}
