package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.medicine.MedicineDto;
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

    @Secured("PATIENT")
    @GetMapping("/{name}")
    public ResponseEntity<MedicineDto> getMedicine(@PathVariable String name) {
        MedicineDto medicineDto = medicineService.findMedicine(name);
        return new ResponseEntity<>(medicineDto, HttpStatus.OK);
    }

    @Secured("PATIENT")
    @PostMapping("/create")
    public ResponseEntity<MedicineDto> postMedicine(@RequestBody MedicineDto medicineDto) {
        medicineService.saveMedicine(medicineDto);
        return new ResponseEntity<>(medicineDto, HttpStatus.CREATED);
    }
}
