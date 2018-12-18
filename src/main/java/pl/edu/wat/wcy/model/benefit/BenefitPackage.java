package pl.edu.wat.wcy.model.benefit;

import javax.persistence.*;

import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Entity
@Table(name = "medical_packages")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "package_type")
public abstract class BenefitPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Embedded
    private Money yearCost;

    @Column(nullable = false, updatable = false)
    private String description;

    protected BenefitPackage() {}

    public BenefitPackage(String name, Money yearCost, String description) {
        this.name = requireNonNull(name, "name");
        this.yearCost = requireNonNull(yearCost, "year cost");
        this.description = requireNonNull(description, "description");
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getYearCost() {
        return yearCost;
    }

    public String getDescription() {
        return description;
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
