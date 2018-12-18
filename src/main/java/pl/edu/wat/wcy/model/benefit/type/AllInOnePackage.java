package pl.edu.wat.wcy.model.benefit.type;

import pl.edu.wat.wcy.model.benefit.BenefitPackage;
import pl.edu.wat.wcy.model.benefit.Money;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("all_in_one")
public class AllInOnePackage extends BenefitPackage {
    protected AllInOnePackage() {}

    public AllInOnePackage(String name, Money yearCost, String description) {
        super(name, yearCost, description);
    }
}
