package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.benefit.BenefitPackageProjection;
import pl.edu.wat.wcy.dto.benefit.PurchaseRequestDto;
import pl.edu.wat.wcy.dto.benefit.PurchaseResponseDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.benefit.BenefitPackage;
import pl.edu.wat.wcy.model.benefit.Purchase;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.BenefitPackageRepository;
import pl.edu.wat.wcy.repository.PatientRepository;
import pl.edu.wat.wcy.repository.PurchaseRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BenefitPackageService {
    private final BenefitPackageRepository benefitPackageRepository;
    private final PatientRepository patientRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public BenefitPackageService(BenefitPackageRepository benefitPackageRepository, PatientRepository patientRepository,
                                 PurchaseRepository purchaseRepository) {
        this.benefitPackageRepository = benefitPackageRepository;
        this.patientRepository = patientRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public List<BenefitPackageProjection> findPackages() {
        return benefitPackageRepository.findAllProjectedBy();
    }

    public PurchaseResponseDto purchasePackage(PurchaseRequestDto purchaseRequestDto) {
        Patient patient = findPatient(purchaseRequestDto.getPatientId());
        BenefitPackage benefitPackage = findPackage(purchaseRequestDto.getBenefitPackageId());
        Purchase purchase = new Purchase(patient, benefitPackage);
        purchaseRepository.save(purchase);
        return new PurchaseResponseDto(patient.getFullName().getName(), benefitPackage.getName(),
                purchase.getFromDate(), purchase.getToDate(), benefitPackage.getYearCost());
    }

    private Patient findPatient(long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("patient", patientId));
    }

    private BenefitPackage findPackage(long packageId) {
        return benefitPackageRepository.findById(packageId)
                .orElseThrow(() -> new ResourceNotFoundException("benefit package", packageId));
    }
}
