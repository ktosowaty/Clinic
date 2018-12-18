package pl.edu.wat.wcy.dto.history;

import pl.edu.wat.wcy.model.person.data.Pesel;

import java.time.LocalDate;

public class PatientDiseaseDto {
    private String patientFirstName;

    private String patientSurname;

    private Pesel patientPesel;

    private String diseaseName;

    private String diagnosis;

    private LocalDate diagnosisDate;

    private String medicineName;

    private String medicineDosage;

    private PatientDiseaseDto() {}

    public PatientDiseaseDto(String patientFirstName, String patientSurname, Pesel patientPesel, String diseaseName,
                             String diagnosis, LocalDate diagnosisDate, String medicineName, String medicineDosage) {
        this.patientFirstName = patientFirstName;
        this.patientSurname = patientSurname;
        this.patientPesel = patientPesel;
        this.diseaseName = diseaseName;
        this.diagnosis = diagnosis;
        this.diagnosisDate = diagnosisDate;
        this.medicineName = medicineName;
        this.medicineDosage = medicineDosage;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public Pesel getPatientPesel() {
        return patientPesel;
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
