package pl.edu.wat.wcy.model.disease;

import javax.persistence.*;

import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Table(name = "diseases")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    private Disease() {
        // JPA
    }

    public Disease(String name, String description) {
        this.name = requireNonNull(name, "disease name");
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disease disease = (Disease) o;
        return id == disease.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
