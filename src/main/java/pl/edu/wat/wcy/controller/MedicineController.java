package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.MedicineDto;
import pl.edu.wat.wcy.dto.MedicineProjection;
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
    public ResponseEntity<MedicineProjection> getMedicine(@PathVariable String name) {
        MedicineProjection medicineProjection = medicineService.findMedicine(name);
        return new ResponseEntity<>(medicineProjection, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MedicineDto> postMedicine(@RequestBody MedicineDto medicineDto) {
        medicineService.saveMedicine(medicineDto);
        return new ResponseEntity<>(medicineDto, HttpStatus.CREATED);
    }
}
