package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.medicine.MedicineDto;
import pl.edu.wat.wcy.dto.medicine.MedicineProjection;
import pl.edu.wat.wcy.exception.AuthenticationException;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.security.AuthenticatedUser;
import pl.edu.wat.wcy.service.MedicineService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<MedicineProjection> getMedicine(@AuthenticationPrincipal AuthenticatedUser user,
                                                          @PathVariable String name) {
        if (user.getUserType() != UserType.DOCTOR) throw new AuthenticationException(user.getUsername());
        MedicineProjection projection = medicineService.findMedicine(name);
        return new ResponseEntity<>(projection, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MedicineDto> postMedicine(@AuthenticationPrincipal AuthenticatedUser user,
                                                    @RequestBody MedicineDto medicineDto) {
        if (user.getUserType() != UserType.DOCTOR) throw new AuthenticationException(user.getUsername());
        medicineService.saveMedicine(medicineDto);
        return new ResponseEntity<>(medicineDto, HttpStatus.CREATED);
    }
}
