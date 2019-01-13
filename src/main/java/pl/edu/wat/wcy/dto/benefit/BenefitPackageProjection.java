package pl.edu.wat.wcy.dto.benefit;

import pl.edu.wat.wcy.model.benefit.Money;

public interface BenefitPackageProjection {
    long getId();

    String getName();

    Money getYearCost();

    String getDescription();
}
