package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Udlejning {
    private LocalDate afleveringsDato;
    private LocalDateTime udleveringsDato;
    private boolean betalt;
    private Kunde kunde;
    private Prisliste prisliste;
    private Betalingsform betalingsform;
    private ArrayList<Ordrelinje> ordrelinjes;

    public Udlejning(LocalDate afleveringsDato, LocalDateTime udleveringsDato, Kunde kunde,Prisliste prisliste){
        this.afleveringsDato=afleveringsDato;
        this.udleveringsDato = udleveringsDato;
        this.kunde = kunde;
        this.prisliste = prisliste;
    }

    public LocalDate getAfleveringsDato() {
        return afleveringsDato;
    }

    public void setAfleveringsDato(LocalDate afleveringsDato) {
        this.afleveringsDato = afleveringsDato;
    }

    public LocalDateTime getUdleveringsDato() {
        return udleveringsDato;
    }

    public void setUdleveringsDato(LocalDateTime udleveringsDato) {
        this.udleveringsDato = udleveringsDato;
    }

    public void setPrisliste(Prisliste prisliste) {
        this.prisliste = prisliste;
    }

    public Betalingsform getBetalingsform() {
        return betalingsform;
    }

    public void setBetalingsform(Betalingsform betalingsform) {
        this.betalingsform = betalingsform;
    }

    public void addOrdrelinje(Ordrelinje ordrelinje){
        if(!ordrelinjes.contains(ordrelinje)){
            ordrelinjes.add(ordrelinje);
        }
    }

    public double beregnPris(){
        double sum = 0.0;
        for (Ordrelinje o:ordrelinjes) {
            sum += o.getPris();
        }
        return sum;
    }


}
