package pl.edu.wat.wcy.dto.benefit;

public class PurchaseRequestDto {
    private long patientId;

    private long benefitPackageId;

    private PurchaseRequestDto() {}

    public PurchaseRequestDto(long patientId, long benefitPackageId) {
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
