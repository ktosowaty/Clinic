package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.benefit.BenefitPackageProjection;
import pl.edu.wat.wcy.dto.benefit.PurchaseRequestDto;
import pl.edu.wat.wcy.dto.benefit.PurchaseResponseDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.benefit.BenefitPackage;
import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.benefit.Purchase;
import pl.edu.wat.wcy.model.person.data.name.Name;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.model.person.user.User;
import pl.edu.wat.wcy.repository.BenefitPackageRepository;
import pl.edu.wat.wcy.repository.PurchaseRepository;
import pl.edu.wat.wcy.repository.UserRepository;
import pl.edu.wat.wcy.security.AuthenticatedUser;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BenefitPackageService {
    private final BenefitPackageRepository benefitPackageRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public BenefitPackageService(BenefitPackageRepository benefitPackageRepository, UserRepository userRepository,
                                 PurchaseRepository purchaseRepository) {
        this.benefitPackageRepository = benefitPackageRepository;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public List<BenefitPackageProjection> findPackages() {
        return benefitPackageRepository.findAllProjectedBy();
    }

    public PurchaseResponseDto purchasePackage(AuthenticatedUser authenticatedUser, PurchaseRequestDto purchaseRequestDto) {
        User user = findUser(authenticatedUser.getId());
        Patient patient = (Patient) user.getPerson();
        BenefitPackage benefitPackage = findPackage(purchaseRequestDto.getBenefitPackageId());
        Purchase purchase = new Purchase(patient, benefitPackage);
        purchaseRepository.save(purchase);
        return createResponse(patient, benefitPackage, purchase);
    }

    private User findUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", userId));
    }

    private BenefitPackage findPackage(long packageId) {
        return benefitPackageRepository.findById(packageId)
                .orElseThrow(() -> new ResourceNotFoundException("benefit package", packageId));
    }

    private PurchaseResponseDto createResponse(Patient patient, BenefitPackage benefitPackage, Purchase purchase) {
        Name patientName = patient.getFullName().getName();
        String packageName = benefitPackage.getName();
        LocalDate from = purchase.getFromDate();
        LocalDate to = purchase.getToDate();
        Money yearCost = benefitPackage.getYearCost();
        return new PurchaseResponseDto(patientName, packageName, from, to, yearCost);
    }
}
