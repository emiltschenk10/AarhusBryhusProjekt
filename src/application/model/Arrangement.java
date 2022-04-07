package application.model;

import java.time.LocalDate;

public class Arrangement {

    private String navn;

    private String beskrivelse;

    private double pris;

    private LocalDate date;

    public Arrangement(String navn, String beskrivelse, double pris, LocalDate date){
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.date = date;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    @Override
    public String toString() {
        return "Navn: " + navn + "\n" + "Beskrivelse:  " + beskrivelse + " \n" +  "Pris: " + pris + "\n" + "\n";
    }
}
