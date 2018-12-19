package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.model.person.user.User;
import pl.edu.wat.wcy.model.person.user.Email;
import pl.edu.wat.wcy.model.person.user.Username;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(Username username);

    Optional<User> findByEmail(Email email);

    Optional<User> findByUsernameOrEmail(Username username, Email email);
}
