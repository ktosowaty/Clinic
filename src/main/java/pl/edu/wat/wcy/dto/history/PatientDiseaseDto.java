package pl.edu.wat.wcy.dto.history;

import java.time.LocalDate;

public class PatientDiseaseDto {
    private String diseaseName;

    private String diagnosis;

    private LocalDate diagnosisDate;

    private String medicineName;

    private String medicineDosage;

    private PatientDiseaseDto() {}

    public PatientDiseaseDto(String diseaseName, String diagnosis, LocalDate diagnosisDate,
                             String medicineName, String medicineDosage) {
        this.diseaseName = diseaseName;
        this.diagnosis = diagnosis;
        this.diagnosisDate = diagnosisDate;
        this.medicineName = medicineName;
        this.medicineDosage = medicineDosage;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public LocalDate getDiagnosisDate() {
        return diagnosisDate;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineDosage() {
        return medicineDosage;
    }
}
