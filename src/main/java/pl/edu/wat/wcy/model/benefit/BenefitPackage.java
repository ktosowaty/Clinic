package pl.edu.wat.wcy.model.benefit;

import javax.persistence.*;

import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Table(name = "medical_packages")
public class BenefitPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Embedded
    private Money cost;

    private BenefitPackage() {
        // JPA
    }

    public BenefitPackage(String name, Money cost) {
        this.name = requireNonNull(name, "package name");
        this.cost = requireNonNull(cost, "package cost");
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BenefitPackage that = (BenefitPackage) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
