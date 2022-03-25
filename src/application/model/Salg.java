package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Salg {

    private LocalDateTime dato;

    private boolean betalt;

    private Prisliste prisliste;

    private Kunde kunde;

    private ArrayList<Betalingsform> betalingsformer = new ArrayList<>();

    private ArrayList<Ordrelinje> ordrelinjer;


    public Salg() {
        this.dato = LocalDateTime.now();
        this.betalt = false;
    }

    public Salg(LocalDateTime dato, boolean betalt, Prisliste prisliste) {
        this.dato = dato;
        this.betalt = betalt;
        this.prisliste = prisliste;
    }


    public ArrayList<Betalingsform> getBetalingsformer() {
        return new ArrayList<Betalingsform>(betalingsformer);
    }

    public ArrayList<Ordrelinje> getOrdrelinjer() {
        return new ArrayList<Ordrelinje>(ordrelinjer);
    }

    public Kunde getKunde() {
        return kunde;
    }

    public LocalDateTime getDato() {
        return dato;
    }

    public Prisliste getPrisliste() {
        return prisliste;
    }

    public void setKunde(Kunde kunde) {
        if (this.kunde != kunde) {
            Kunde oldKunde = this.kunde;
            if (oldKunde != null) {
                oldKunde.removeSalg(this);
            }
            this.kunde = kunde;
            if (kunde != null) {
                kunde.addSalg(this);
            }
        }
    }

    public void addOrdrelinje(Ordrelinje ordrelinje){
        if (!ordrelinjer.contains(ordrelinje)){
            ordrelinjer.add(ordrelinje);
            ordrelinje.setSalg(this);
        }
    }

    public void removeOrdrelinje(Ordrelinje ordrelinje){
        if (ordrelinjer.contains(ordrelinje)){
            ordrelinjer.remove(ordrelinje);
            ordrelinje.setSalg(null);
        }
    }
}
