package pl.edu.wat.wcy.dto.benefit;

public class PurchaseDto {
    private long patientId;

    private long benefitPackageId;

    private PurchaseDto() {}

    public PurchaseDto(long patientId, long benefitPackageId) {
        this.patientId = patientId;
        this.benefitPackageId = benefitPackageId;
    }

    public long getPatientId() {
        return patientId;
    }

    public long getBenefitPackageId() {
        return benefitPackageId;
    }
}
