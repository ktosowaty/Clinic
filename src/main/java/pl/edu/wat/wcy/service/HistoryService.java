package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.history.HistoryRecordDto;
import pl.edu.wat.wcy.dto.history.PatientDiseaseDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.disease.Disease;
import pl.edu.wat.wcy.model.disease.Medicine;
import pl.edu.wat.wcy.model.disease.MedicineDisease;
import pl.edu.wat.wcy.model.disease.PatientDisease;
import pl.edu.wat.wcy.model.person.data.Pesel;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistoryService {
    private final PatientRepository patientRepository;
    private final DiseaseRepository diseaseRepository;
    private final PatientDiseaseRepository patientDiseaseRepository;
    private final MedicineRepository medicineRepository;
    private final MedicineDiseaseRepository medicineDiseaseRepository;

    @Autowired
    public HistoryService(PatientRepository patientRepository, DiseaseRepository diseaseRepository,
                          PatientDiseaseRepository patientDiseaseRepository, MedicineRepository medicineRepository,
                          MedicineDiseaseRepository medicineDiseaseRepository) {
        this.patientRepository = patientRepository;
        this.diseaseRepository = diseaseRepository;
        this.patientDiseaseRepository = patientDiseaseRepository;
        this.medicineRepository = medicineRepository;
        this.medicineDiseaseRepository = medicineDiseaseRepository;
    }

    public List<PatientDiseaseDto> generatePatientHistory(String peselStr) {
        Pesel pesel = new Pesel(peselStr);
        return patientDiseaseRepository.findPatientDiseases(pesel);
    }

    public void saveRecord(HistoryRecordDto historyRecordDto) {
        Patient patient = findPatient(historyRecordDto.getPatientId());
        Disease disease = findDisease(historyRecordDto.getDiseaseName());
        PatientDisease patientDisease = createPatientDisease(patient, disease, historyRecordDto);
        Medicine medicine = findMedicine(historyRecordDto.getMedicineName());
        MedicineDisease medicineDisease = createMedicineDisease(medicine, patientDisease, historyRecordDto);
        patientDiseaseRepository.save(patientDisease);
        medicineDiseaseRepository.save(medicineDisease);
    }

    private Patient findPatient(long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("patient", patientId));
    }

    private Disease findDisease(String name) {
        return diseaseRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("disease", name));
    }

    private PatientDisease createPatientDisease(Patient patient, Disease disease, HistoryRecordDto historyRecordDto) {
        String diagnosis = historyRecordDto.getDiagnosis();
        LocalDate diagnosisDate = LocalDate.parse(historyRecordDto.getDiagnosisDateStr());
        return new PatientDisease(patient, disease, diagnosis, diagnosisDate);
    }

    private Medicine findMedicine(String name) {
        return medicineRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("medicine", name));
    }

    private MedicineDisease createMedicineDisease(Medicine medicine, PatientDisease patientDisease,
                                       HistoryRecordDto historyRecordDto) {
        String dosage = historyRecordDto.getMedicineDiseaseDosage();
        return new MedicineDisease(medicine, patientDisease, dosage);
    }
}
