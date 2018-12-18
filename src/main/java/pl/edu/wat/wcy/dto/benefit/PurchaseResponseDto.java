package pl.edu.wat.wcy.dto.benefit;

import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.person.data.name.Name;

import java.time.LocalDate;

public class PurchaseResponseDto {
    private Name patientName;

    private String packageName;

    private LocalDate from;

    private LocalDate to;

    private Money cost;

    private PurchaseResponseDto() {}

    public PurchaseResponseDto(Name patientName, String packageName, LocalDate from, LocalDate to, Money cost) {
        this.patientName = patientName;
        this.packageName = packageName;
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Name getPatientName() {
        return patientName;
    }

    public String getPackageName() {
        return packageName;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public Money getCost() {
        return cost;
    }
}
