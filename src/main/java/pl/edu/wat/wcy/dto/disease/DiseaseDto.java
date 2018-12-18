package pl.edu.wat.wcy.dto.disease;

public class DiseaseDto {
    private String name;

    private String description;

    private DiseaseDto() {}

    public DiseaseDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
