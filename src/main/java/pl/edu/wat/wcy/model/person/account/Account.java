package pl.edu.wat.wcy.model.person.account;

import pl.edu.wat.wcy.model.person.Person;

import javax.persistence.*;

import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @Embedded
    private Login login;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Column
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
    private Person person;

    private Account() {
        // JPA
    }

    public Account(Login login, Password password, Email email, AccountType accountType, Person person) {
        this.login = requireNonNull(login, "login");
        this.password = requireNonNull(password, "password");
        this.email = requireNonNull(email, "email");
        this.accountType = requireNonNull(accountType, "account type");
        this.person = requireNonNull(person, "person");
    }

    public long getId() {
        return id;
    }

    public Login getLogin() {
        return login;
    }

    public Password getPassword() {
        return password;
    }

    public Email getEmail() {
        return email;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
