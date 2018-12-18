package pl.edu.wat.wcy.model.benefit.type;

import pl.edu.wat.wcy.model.benefit.BenefitPackage;
import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.person.doctor.Specialization;

import javax.persistence.*;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Entity
@DiscriminatorValue("free_specialist")
public class FreeSpecialistPackage extends BenefitPackage {
    @Column
    @Enumerated(value = EnumType.STRING)
    private Specialization specialization;

    protected FreeSpecialistPackage() {}

    public FreeSpecialistPackage(String name, Money yearCost, String description, Specialization specialization) {
        super(name, yearCost, description);
        this.specialization = requireNonNull(specialization, "specialization");
    }

    public Specialization getSpecialization() {
        return specialization;
    }
}
