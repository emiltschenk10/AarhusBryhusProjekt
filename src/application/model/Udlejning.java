package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Udlejning {
    private LocalDate afleveringsDato;
    private LocalDate udleveringsDato;
    private boolean betalt;
    private boolean udestående;
    private Kunde kunde;
    private Prisliste prisliste;
    private Betalingsform betalingsform;
    private ArrayList<Ordrelinje> ordrelinjes = new ArrayList<>();
    private int udlejningsNr;
    private  static  int udlejningNext = 0;


    public Udlejning(LocalDate afleveringsDato, LocalDate udleveringsDato, Kunde kunde,Prisliste prisliste){
        this.afleveringsDato=afleveringsDato;
        this.udleveringsDato = udleveringsDato;
        this.kunde = kunde;
        this.prisliste = prisliste;
    }

    public Udlejning(){

    }

    public void udlejningsNr(){
        udlejningNext++;
        this.udlejningsNr = udlejningNext;
    }

    public Ordrelinje createOrdrelinje(Produkt produkt,int antal, double pris){
        Ordrelinje o = new Ordrelinje(produkt,antal,prisliste.getProduktpriser().get(produkt));
        o.setUdlejning(this);
        this.addOrdrelinje(o);
        return o;
    }

    public boolean isBetalt(){
        return betalt;
    }

    public int getUdlejningsNr(){return udlejningsNr;}

    public boolean isUdestående(){
        return udestående;
    }

    public LocalDate getAfleveringsDato() {
        return afleveringsDato;
    }

    public ArrayList<Ordrelinje> getOrdrelinjer() {
        return new ArrayList<>(ordrelinjes);
    }

    public void setAfleveringsDato(LocalDate afleveringsDato) {
        this.afleveringsDato = afleveringsDato;
    }

    public LocalDate getUdleveringsDato() {
        return udleveringsDato;
    }

    public void setUdleveringsDato(LocalDate udleveringsDato) {
        this.udleveringsDato = udleveringsDato;
    }

    public void setPrisliste(Prisliste prisliste) {
        this.prisliste = prisliste;
    }

    public void setKunde(Kunde kunde) {
        if (this.kunde != kunde) {
            Kunde oldKunde = this.kunde;
            if (oldKunde != null) {
                oldKunde.removeUdlejning(this);
            }
            this.kunde = kunde;
            if (kunde != null) {
                kunde.addUdlejning(this);
            }
        }
    }

    public Betalingsform getBetalingsform() {
        return betalingsform;
    }

    public void setBetalingsform(Betalingsform betalingsform) {
        this.betalingsform = betalingsform;
    }

    public void setBetalt(Boolean betalt){this.betalt = betalt;}

    public void setUdestående(Boolean udestående){
        this.udestående=udestående;
    }

    public void addOrdrelinje(Ordrelinje ordrelinje){
        if(!ordrelinjes.contains(ordrelinje)){
            ordrelinjes.add(ordrelinje);
            ordrelinje.setUdlejning(this);
        }
    }

    public double beregnPris(){
        double sum = 0.0;
        for (Ordrelinje o:ordrelinjes) {
            sum += o.getPris();
        }
        return sum;
    }

    public double beregnRestPris(){
        double sum = 0;
        for (Ordrelinje o : ordrelinjes){
            sum += o.beregnUdlejningsPris();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Udlejningsnr " + udlejningsNr;

    }
}
