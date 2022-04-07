package application.model;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Modellerer et salg
 */
public class Salg {

    private LocalDate dato;

    private boolean betalt;

    private Prisliste prisliste;

    private Kunde kunde;

    private Betalingsform betalingsform;

    private ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();

    private int salgsNr;

    private  static  int salgNext = 0;

    /**
     * Initialiserer et nyt salg med dato sat til dagens dato og betalt sat til false.
     */
    public Salg() {
        this.dato = LocalDate.now();
        this.betalt = false;
        salgNext++;
        this.salgsNr = salgNext;
    }

    /**
     * Initialiserer et nyt salg med salgets dato, betalingsstatus på salget
     * samt den prisliste der bruges på salget.
     */
    public Salg(LocalDate dato, boolean betalt, Prisliste prisliste) {
        this.dato = dato;
        this.betalt = betalt;
        this.prisliste = prisliste;
        salgNext++;
        this.salgsNr = salgNext;
    }

    /**
     * Registrerer om der er betalt på salget.
     *
     * @param betalt er den nye betalingsstatus
     */
    public void setBetalt(Boolean betalt){
        this.betalt = betalt;
    }

    /**
     * Returnerer de ordrelinjer der er sat ind på salget.
     *
     * @return ordrelinjer for salget
     */
    public ArrayList<Ordrelinje> getOrdrelinjer() {
        return new ArrayList<>(ordrelinjer);
    }

    /**
     * Returnerer salgets nummer.
     *
     * @return nummer på salget
     */
    public int getSalgsNr(){
        return salgsNr;
    }

    /**
     * Returnerer kunden på salget.
     *
     * @return kunde
     */
    public Kunde getKunde() {
        return kunde;
    }

    /**
     * Returnerer datoen for salget.
     *
     * @return salgsdato
     */
    public LocalDate getDato() {
        return dato;
    }

    /**
     * Returnerer prislisten der er valgt på salget.
     *
     * @return prislisten på salget
     */
    public Prisliste getPrisliste() {
        return prisliste;
    }

    /**
     * Returnerer betalingsstatus på salget.
     *
     * @return salgets betalingsstatus
     */
    public boolean isBetalt(){
        return betalt;
    }

    /**
     * Registrerer den prisliste der bruges til salget.
     *
     * @param prisliste er den prisliste der benyttes på salget
     */
    public void setPrisliste(Prisliste prisliste) {
        this.prisliste = prisliste;
    }

    /**
     * Registrerer datoen for salget.
     *
     * @param dato er salgsdatoen
     */
    public void setSalgsDato(LocalDate dato) {
        this.dato = dato;
    }

    /**
     * Registrerer en kunde på salget.
     *
     * @param kunde er salgets kunde
     */
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

    /**
     * Registrerer hvilken betalingsform der bruges på salget.
     *
     * @param betalingsform er den betalingsmetode der bruges på  salget
     */
    public void setBetalingsform(Betalingsform betalingsform) {
        this.betalingsform = betalingsform;
    }

    /**
     * Returnerer salgets betalingsform.
     *
     * @return salgets betalingsform
     */
    public Betalingsform getBetalingsform() {
        return betalingsform;
    }


    /**
     * Fjerner en ordrelinje fra listen over ordrelinjer på salget.
     *
     * @param ordrelinje er den ordrelinje der fjernes
     */
    public void removeOrdrelinje(Ordrelinje ordrelinje){
        if (ordrelinjer.contains(ordrelinje)){
            ordrelinjer.remove(ordrelinje);
            ordrelinje.setSalg(null);
        }
    }

    /**
     * Returnerer den samlede pris for salgets ordrelinjer
     *
     * @return salgets samlede pris
     */
    public double beregnPris(){
    double sum = 0.0;
        for (Ordrelinje o:ordrelinjer) {
            sum += o.getPris();
        }
        return sum;
    }

    /**
     * Opretter en ny ordrelinje til salget.
     * @param produkt er produktet der bliver valgt
     * @param antal er hvor mange enheder der ønskes af produktet
     * @return det oprettede ordrelinje objekt
     * Pre: Prisliste != null
     */
    public Ordrelinje createOrdrelinje(Produkt produkt, int antal){
        Ordrelinje ordrelinje = new Ordrelinje(produkt, antal, prisliste.getProduktpriser().get(produkt));
        ordrelinjer.add(ordrelinje);
        ordrelinje.setSalg(this);
        return ordrelinje;
    }



    public boolean salgSituation(LocalDate date, Arrangement arrangement) {
        boolean b = false;
        if (date.equals(getDato()) && getPrisliste() != null) {
            if (arrangement == null ||  this.getPrisliste().getArragementer().contains(arrangement)) {
                b = true;
            }
        }
       return b;
    }


    /**
     * Returnerer informationer om salget.
     *
     * @return salgsnr, kundens navn hvis der er sat en kunde på salget,
     * salgsdatoen og betalingsstatus
     */
    public String toString(){
        String result = "";
        if(kunde!=null){
            result = "Salgs Nr: " + salgsNr + "  Kunde: " + kunde.getNavn() + "  Dato: " + dato + "  Betalt: " + betalt;
        }else{
            result = "Salgs Nr: " + salgsNr + "  Dato: " + dato + "  Betalt: " + betalt;
        }
        return result;
    }

    /**
     * Returnerer de produkter der er sat på salgets ordrelinjer samt hvor mange enheder der er
     * købt af hvert produkt på ordrelinjen.
     *
     * @return Ordrelinjernes produkter samt hvor mange enheder der er købt af produkterne
     */
    public StringBuilder salgsInfoDag(){
        StringBuilder result = new StringBuilder("");
        for (Ordrelinje o:ordrelinjer) {
            result.append("Produkt: " + o.getProdukt() + " Antal: " + o.getAntal() + " Pris: " + o.getPris() + " Samlet pris: " + beregnPris() + " Betalingsform: " + getBetalingsform() + "\n");

        }
        return result;
    }
}
