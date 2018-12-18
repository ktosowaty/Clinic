package pl.edu.wat.wcy.model.person.account;

import pl.edu.wat.wcy.model.person.Person;

import javax.persistence.*;

import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Embedded
    private Username username;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    protected User() {}

    public User(Username username, Password password, Email email, UserType userType) {
        this.username = requireNonNull(username, "username");
        this.password = requireNonNull(password, "password");
        this.email = requireNonNull(email, "email");
        this.userType = requireNonNull(userType, "user type");
    }

    public long getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public Email getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        requireNonNull(person, "person");
        if (this.person != null) throw new IllegalArgumentException("This user already has an associated person.");
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
