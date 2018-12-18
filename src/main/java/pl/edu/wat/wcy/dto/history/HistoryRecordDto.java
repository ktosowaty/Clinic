package pl.edu.wat.wcy.dto.history;

public class HistoryRecordDto {
    private long patientId;

    private String diseaseName;

    private String diagnosis;

    private String diagnosisDateStr;

    private String medicineName;

    private String medicineDiseaseDosage;

    private HistoryRecordDto() {}

    public HistoryRecordDto(long patientId, String diseaseName, String diagnosis, String diagnosisDateStr,
                            String medicineName, String medicineDiseaseDosage) {
        this.patientId = patientId;
        this.diseaseName = diseaseName;
        this.diagnosis = diagnosis;
        this.diagnosisDateStr = diagnosisDateStr;
        this.medicineName = medicineName;
        this.medicineDiseaseDosage = medicineDiseaseDosage;
    }

    public long getPatientId() {
        return patientId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDiagnosisDateStr() {
        return diagnosisDateStr;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineDiseaseDosage() {
        return medicineDiseaseDosage;
    }
}
