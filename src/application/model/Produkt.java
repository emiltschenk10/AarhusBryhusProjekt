package application.model;

import java.io.Serializable;

public class Produkt implements Serializable {
    private String navn;
    private String beskrivelse;
    private int klipPris;
    private double pant;

    // composition: --> 1 Produktgruppe
    private Produktgruppe produktgruppe;


    Produkt(String navn, String beskrivelse, int klipPris, double pant, Produktgruppe produktgruppe) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.klipPris = klipPris;
        this.pant = pant;
        this.produktgruppe = produktgruppe;
    }

    public String getNavn() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public int getKlipPris() {
        return klipPris;
    }

    public double getPant() {
        return pant;
    }

    public Produktgruppe getProduktgruppe() {
        return produktgruppe;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public void setKlipPris(int klipPris) {
        this.klipPris = klipPris;
    }

    public void setPant(double pant) {
        this.pant = pant;
    }

    @Override
    public String toString() {
        return produktgruppe + " " + navn;
    }
}
