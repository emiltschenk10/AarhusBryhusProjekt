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

    private ArrayList<Betalingsformer> betalingsformer;

    private Discount discount;

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

    public Discount getDiscount() {
        return discount;
    }

    public ArrayList<Betalingsformer> getBetalingsformer() {
        return new ArrayList<Betalingsformer>(betalingsformer);
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
}
