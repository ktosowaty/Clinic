package pl.edu.wat.wcy.dto.benefit;

public class PurchaseRequestDto {
    private long benefitPackageId;

    private PurchaseRequestDto() {}

    public PurchaseRequestDto(long benefitPackageId) {
        this.benefitPackageId = benefitPackageId;
    }

    public long getBenefitPackageId() {
        return benefitPackageId;
    }
}
