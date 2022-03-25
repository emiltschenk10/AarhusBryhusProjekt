package application.model;

public class Arrangement {

    private String navn;

    private String beskrivelse;

    private double pris;

    public Arrangement(String navn, String beskrivelse, double pris){
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }
}
