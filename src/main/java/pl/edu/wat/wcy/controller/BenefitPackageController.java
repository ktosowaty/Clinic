package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.edu.wat.wcy.dto.benefit.BenefitPackageProjection;
import pl.edu.wat.wcy.dto.benefit.PurchaseRequestDto;
import pl.edu.wat.wcy.dto.benefit.PurchaseResponseDto;
import pl.edu.wat.wcy.exception.AuthenticationException;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.security.AuthenticatedUser;
import pl.edu.wat.wcy.service.BenefitPackageService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/package")
public class BenefitPackageController {
    private final BenefitPackageService benefitPackageService;

    @Autowired
    public BenefitPackageController(BenefitPackageService benefitPackageService) {
        this.benefitPackageService = benefitPackageService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BenefitPackageProjection>> getPackages(@AuthenticationPrincipal AuthenticatedUser user) {
        if (user.getUserType() != UserType.PATIENT) throw new AuthenticationException(user.getUsername());
        List<BenefitPackageProjection> packages = benefitPackageService.findPackages();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponseDto> postPurchase(@AuthenticationPrincipal AuthenticatedUser user,
                                                            @RequestBody PurchaseRequestDto request) {
        if (user.getUserType() != UserType.PATIENT) throw new AuthenticationException(user.getUsername());
        PurchaseResponseDto response = benefitPackageService.purchasePackage(user, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
