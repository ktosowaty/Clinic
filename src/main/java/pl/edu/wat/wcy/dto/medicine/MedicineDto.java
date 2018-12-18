package pl.edu.wat.wcy.dto.medicine;

public class MedicineDto {
    private String name;

    private String description;

    private String sideEffects;

    private MedicineDto() {}

    public MedicineDto(String name, String description, String sideEffects) {
        this.name = name;
        this.description = description;
        this.sideEffects = sideEffects;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSideEffects() {
        return sideEffects;
    }
}
