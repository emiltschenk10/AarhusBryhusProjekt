package application.model;

import java.util.ArrayList;

public class Produktgruppe {
    private String navn;
    private String beskrivelse;

    // composition: --> 0..* Produkt
    private final ArrayList<Produkt> produkter = new ArrayList<>();

    public Produktgruppe(String navn, String beskrivelse) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public String getNavn() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setNavn(String navn) {
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public ArrayList<Produkt> getProdukter() { return new ArrayList<>(produkter);
    }
    public Produkt createProdukt(String navn, String beskrivelse, int klipPris, double pant) {
        Produkt produkt = new Produkt(navn,beskrivelse,klipPris,pant,this);
        produkter.add(produkt);
        return produkt;
    }
    public void removePerson(Produkt produkt) {
        if (produkter.contains(produkt)) {
            produkter.remove(produkt);
        }
    }


}
