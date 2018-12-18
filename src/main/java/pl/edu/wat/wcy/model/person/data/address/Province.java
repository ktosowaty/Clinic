package pl.edu.wat.wcy.model.person.data.address;

public enum Province {
    DOLNOSLASKIE("dolnośląskie"),
    KUJAWSKO_POMORSKIE("kujawsko-pomorskie"),
    LUBELSKIE("lubelskie"),
    LUBUSKIE("lubuskie"),
    LODZKIE("łódzkie"),
    MALOPOLSKIE("małopolskie"),
    MAZOWIECKIE("mazowieckie"),
    OPOLSKIE("opolskie"),
    PODKARPACKIE("podkarpackie"),
    PODLASKIE("podlaskie"),
    POMORSKIE("pomorskie"),
    SLASKIE("śląskie"),
    SWIETOKRZYSKIE("świętokrzyskie"),
    WARMINSKO_MAZURSKIE("warmińsko-mazurskie"),
    WIELKOPOLSKIE("wielkopolskie"),
    ZACHODNIOPOMORSKIE("zachodniopomorskie");

    private final String text;

    Province(final String text) {
        this.text = text;
    }

    public static Province fromString(String text) {
        for (Province province : Province.values()) {
            if (province.text.equals(text)) {
                return province;
            }
        }
        throw new IllegalArgumentException("Unknown province: " + text + ".");
    }
}
