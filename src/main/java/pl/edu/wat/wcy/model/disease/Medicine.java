package pl.edu.wat.wcy.model.disease;

import javax.persistence.*;

import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false, length = 40)
    private String name;

    @Column
    private String description;

    @Column
    private String sideEffects;

    private Medicine() {
        // JPA
    }

    public Medicine(String name, String description, String sideEffects) {
        this.name = requireNonNull(name, "medicine name");
        this.description = description;
        this.sideEffects = sideEffects;
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

    public String getSideEffects() {
        return sideEffects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return id == medicine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
