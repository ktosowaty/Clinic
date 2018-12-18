package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.benefit.BenefitPackageProjection;
import pl.edu.wat.wcy.dto.benefit.PurchaseDto;
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
    public ResponseEntity<List<BenefitPackageProjection>> getPackages() {
        List<BenefitPackageProjection> packages = benefitPackageService.findPackages();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseDto> postPurchase(@RequestBody PurchaseDto purchaseDto) {
        benefitPackageService.purchasePackage(purchaseDto);
        return new ResponseEntity<>(purchaseDto, HttpStatus.CREATED);
    }
}
