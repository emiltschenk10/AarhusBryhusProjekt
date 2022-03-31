package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;

public class Salg {

    private LocalDateTime dato;

    private boolean betalt;

    private Prisliste prisliste;

    private Kunde kunde;

    private Betalingsform betalingsform;

    private ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();

    private int salgsNr;

    private  static  int salgNext = 0;


    public Salg() {
        this.dato = LocalDateTime.now();
        this.betalt = false;
    }

    public Salg(LocalDateTime dato, boolean betalt, Prisliste prisliste) {
        this.dato = dato;
        this.betalt = betalt;
        this.prisliste = prisliste;
    }

    public void salgsNr(){
        salgNext++;
        this.salgsNr = salgNext;
    }

    public ArrayList<Ordrelinje> getOrdrelinjer() {
        return new ArrayList<>(ordrelinjer);
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

    public boolean isBetalt(){
        return betalt;
    }

    public void setPrisliste(Prisliste prisliste) {
        this.prisliste = prisliste;
    }

    public void setSalgsDato(LocalDateTime dato) {
        this.dato = dato;
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

    public void setBetalingsform(Betalingsform betalingsform) {
        this.betalingsform = betalingsform;
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

    public double beregnPris(){
    double sum = 0.0;
        for (Ordrelinje o:ordrelinjer) {
            sum += o.getPris();
        }
        return sum;
    }

    public Ordrelinje createOrdrelinje(Produkt produkt, int antal){
        Ordrelinje ordrelinje = new Ordrelinje(produkt, antal, prisliste.getProduktpriser().get(produkt));
        ordrelinjer.add(ordrelinje);
        ordrelinje.setSalg(this);
        return ordrelinje;
    }

    public String toString(){
        String result = "";
        if(kunde!=null){
            result = "Salgs Nr: " + salgsNr + "  Kunde: " + kunde.getNavn() + "  Dato: " + dato + "  Betalt: " + betalt;
        }else{
            result = "Salgs Nr: " + salgsNr + "  Dato: " + dato.toLocalDate() + "  Betalt: " + betalt;
        }
        return result;
    }
}
