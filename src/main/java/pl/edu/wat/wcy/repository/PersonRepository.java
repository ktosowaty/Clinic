package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.edu.wat.wcy.model.person.Person;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends JpaRepository<T, Long> {
}
