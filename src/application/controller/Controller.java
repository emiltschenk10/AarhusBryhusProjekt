package application.controller;

import application.model.*;
import setup.StorageInitializer;
import storage.Storage;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Controller {

    private Storage storage;
    private static Controller uniqueInstance;

    private Controller(){
        storage = Storage.getInstance();
    }

    public static Controller getInstance(){
        if(uniqueInstance==null){
            uniqueInstance = new Controller();
        }
        return uniqueInstance;
    }


    public Produktgruppe createProduktGruppe(String navn, String beskrivelse) {
        Produktgruppe produktgruppe = new Produktgruppe(navn, beskrivelse);
        storage.addProduktGruppe(produktgruppe);
        return produktgruppe;
    }

    public Prisliste createPrisliste(String navn) {
        Prisliste prisliste = new Prisliste(navn);
        storage.addPrisliste(prisliste);
        return prisliste;
    }

    public Arrangement createArrangement(String navn, String beskrivelse, double pris, LocalDate date) {

        if (pris < 0) {
            throw new IllegalArgumentException("Pris må ikke være mindre end 0");
        } else if(date.isBefore(LocalDate.now())) {
        throw new IllegalArgumentException("Dato skal være idag eller senere");
        }else {

                Arrangement arrangement = new Arrangement(navn, beskrivelse, pris, date);
                storage.addArrangement(arrangement);
                return arrangement;
            }
        }


    public Salg createSalgUdenParm() {
        Salg salg = new Salg();
        storage.addSalg(salg);
        return salg;
    }

    public Salg createSalgMedParm(LocalDate dato, boolean betalt, Prisliste prisliste) {
        Salg salg = new Salg(dato, betalt, prisliste);
        storage.addSalg(salg);
        return salg;
    }

    public Udlejning createUdlejning(LocalDate afleveringsdato, LocalDate udlejningdato, Kunde kunde, Prisliste prisliste) {
        Udlejning udlejning = new Udlejning(afleveringsdato, udlejningdato, kunde, prisliste);
        storage.addUdlejning(udlejning);
        return udlejning;
    }

    public Udlejning createUdlejningUdenParm() {
        Udlejning udlejning = new Udlejning();
        storage.addUdlejning(udlejning);
        return udlejning;
    }

    public Kunde createKunde(String navn, int tlf, String adresse) {
        Kunde kunde = new Kunde(navn, tlf, adresse);
        storage.addKunde(kunde);
        return kunde;
    }
    public void setKundePåUdlejning(Kunde kunde, Udlejning udlejning) {
        udlejning.setKunde(kunde);
    }

    public ProcentDiscount createProcentDiscount(double procent){
        return new ProcentDiscount(procent);
    }

    public AftaltDiscount createAftaltDiscount(double pris){
        return new AftaltDiscount(pris);
    }

    public KlipDiscount createKlipDiscount(){
        return new KlipDiscount();
    }


    public Betalingsform createBetalingsform(String navn, String type) {
        Betalingsform betalingsform = new Betalingsform(navn, type);
        storage.addBetalingsform(betalingsform);
        return betalingsform;
    }

    public void setBetalingsformPåUdlejning(Betalingsform betalingsform, Udlejning udlejning) {
        udlejning.setBetalingsform(betalingsform);
    }

    public void setPrislistePåUdlejning(Udlejning udlejning, Prisliste prisliste) {
        udlejning.setPrisliste(prisliste);
    }

    public Produkt createProdukt(String navn, String beskrivelse, int klipPris, double pant, Produktgruppe produktgruppe) {
        if (klipPris < 0 || pant < 0) {
            throw new IllegalArgumentException("Klipris og pant må ikke værem indre end 0");
        } else {
            Produkt produkt = produktgruppe.createProdukt(navn, beskrivelse, klipPris, pant);
            return produkt;
        }
    }

    public Ordrelinje createOrdrelinjeSalg(Produkt produkt, int antal, Salg salg) {
        if (antal < 1) {
            throw new IllegalArgumentException("Antal må ikke være mindre end 1");
        } else {
            Ordrelinje ordrelinje = salg.createOrdrelinje(produkt, antal);
            return ordrelinje;
        }
    }

    public Ordrelinje createOrdrelinjeUdlejning(Produkt produkt, int antal, Udlejning udlejning) {
        if (antal < 1) {
            throw new IllegalArgumentException("Antal må ikke være mindre end 1");
        } else {
            Ordrelinje ordrelinje = udlejning.createOrdrelinje(produkt, antal);
            return ordrelinje;
        }

    }

    public ArrayList<Ordrelinje> getOrdrelinjePåUdlejning(Udlejning udlejning) {
        return udlejning.getOrdrelinjer();
    }

    //Metoder til prisliste
    public void addProduktTilPrisliste(Produkt produkt, double pris, Prisliste prisliste) {
        if (pris < 1) {
            throw new IllegalArgumentException("Pris må ikke være mindre end 1");
        } else {
            prisliste.addProdukt(produkt, pris);
        }
    }

    public void addArragementTilPrisliste(Arrangement arrangement, Prisliste prisliste) {
        prisliste.addArragement(arrangement);
    }

    //Metoder til salg
    public void setKundePåSalg(Kunde kunde, Salg salg) {
        salg.setKunde(kunde);
    }

    public void setPrislistePåSalg(Salg salg, Prisliste prisliste) {
        salg.setPrisliste(prisliste);
    }

    public void setBetalingsformPåSalg(Betalingsform betalingsform, Salg salg) {
        salg.setBetalingsform(betalingsform);
    }

    public void setSalgSomBetalt(Salg salg, Boolean betalt) {
        salg.setBetalt(betalt);
    }

    public void setUdlejningSomBetalt(Udlejning udlejning, Boolean betalt) {
        udlejning.setBetalt(betalt);
    }

    public void setUdlejningSomUdestående(Udlejning udlejning, Boolean udestående) {
        udlejning.setUdestående(udestående);
    }

    public void setSalgsDato(Salg salg, LocalDate dato) {
        salg.setSalgsDato(dato);
    }

    public void setAfleveringsDato(Udlejning udlejning, LocalDate date) {
        udlejning.setAfleveringsDato(date);
    }

    public void setUdleveringsDato(Udlejning udlejning, LocalDate date) {
        if (udlejning.getAfleveringsDato() != null && date.isAfter(udlejning.getAfleveringsDato()))
        {
            throw new DateTimeException("Udleveringsdato skal være før afleveringsdato");
        }
        else {
            udlejning.setUdleveringsDato(date);
        }
    }

    public void setDiscount(Ordrelinje ordrelinje, Discount discount) {
        ordrelinje.setDiscount(discount);
    }

    public ArrayList<Arrangement> getArrangementer() {
        return storage.getArrangementer();
    }

    public void beregnPris(Salg salg) {
        salg.beregnPris();
    }

    //Remove Metoder

    public void removeSalg(Salg salg) {
        salg.setBetalingsform(null);
        salg.setKunde(null);
        salg.setPrisliste(null);
        storage.removeSalg(salg);
        for (Ordrelinje ordrelinje : salg.getOrdrelinjer()) {
            salg.removeOrdrelinje(ordrelinje);
        }
    }


    public void removeUdlejning(Udlejning udlejning) {
        storage.removeUdlejning(udlejning);
        if (udlejning.getOrdrelinjer() != null) {
            for (Ordrelinje o : udlejning.getOrdrelinjer()) {
                udlejning.removeOrdrelinje(o);
            }
        }
    }

    public void removeArrangement(Arrangement arrangement) {
        storage.removeArrangement(arrangement);
    }


    //Get metoder

    public Prisliste getPrislisteFraSalg(Salg salg) {
        return salg.getPrisliste();
    }

    public ArrayList<Prisliste> getAllePrislister() {
        return storage.getPrislister();
    }

    public ArrayList<Ordrelinje> getOrdrelinjer(Salg salg) {
        return salg.getOrdrelinjer();
    }

    public ArrayList<Produktgruppe> getProduktGrupper() {
        return storage.getProduktGrupper();
    }
    public ArrayList<Betalingsform> getBetalingsformer(){
        return storage.getBetalingsformer();
    }

    public ArrayList<Produkt> getProduktGruppensProdukter(Produktgruppe produktgruppe) {
        return produktgruppe.getProdukter();
    }

    public StringBuilder getAlleSalg() {
        StringBuilder salg = new StringBuilder();
        for (Salg s : storage.getSalgs()) {
            salg.append(s.toString()).append("\n");
        }
        return salg;
    }

    public ArrayList<Salg> getAktuelleSalg() {
        ArrayList<Salg> nySalg = new ArrayList<>();
        for (Salg salg : storage.getSalgs()) {
            if (!salg.isBetalt() || salg.getDato().isAfter(LocalDate.now())) {
                nySalg.add(salg);
            }
        }
        return nySalg;
    }

    public ArrayList<Udlejning> getAktuelleUdlejninger() {
        ArrayList<Udlejning> nyUdlejninger = new ArrayList<>();
        for (Udlejning udlejning : storage.getUdlejnings()) {
            if (!udlejning.isBetalt() || udlejning.getAfleveringsDato().isAfter(LocalDate.now()) || udlejning.isUdestående()) {
                nyUdlejninger.add(udlejning);
            }
        }
        return nyUdlejninger;
    }

    public ArrayList<Produkt> alleProdukter() {
        ArrayList<Produkt> alleProdukter = new ArrayList<>();
        for (int i = 0; i < storage.getProduktGrupper().size(); i++) {
            Produktgruppe produktgruppe = storage.getProduktGrupper().get(i);
            alleProdukter.addAll(produktgruppe.getProdukter());
        }
        return alleProdukter;
    }

    public ArrayList<Produkt> TilgængeligeProdukterTilPrisliste(Prisliste prisliste) {
        ArrayList<Produkt> alleProdukter = new ArrayList<>(alleProdukter());
        ArrayList<Produkt> tilgængeligeProdukter = new ArrayList<>();
        for (Produkt p : alleProdukter()) {
            if (!prisliste.getProduktpriser().containsKey(p)) {
                tilgængeligeProdukter.add(p);
            }
        }
        return tilgængeligeProdukter;
    }


    //Statestik

    public double salgForDato(LocalDate dato) {

        double totalIndtjening = 0.0;

        for (Salg s : storage.getSalgs()) {
            if (dato.equals(s.getDato())) {
                totalIndtjening = totalIndtjening + s.beregnPris();
            }
        }

        return totalIndtjening;
    }


    public int salgForProduktogProduktgruppe(Produktgruppe produktgruppe, Produkt produkt, LocalDate dato, Arrangement arrangement) {
        int totalSolgte = 0;

        for (Salg s : storage.getSalgs()) {
            if (s.salgSituation(dato,arrangement)) {
                for (Ordrelinje o : s.getOrdrelinjer()) {
                    if (o.produktOgProduktGruppe(produkt, produktgruppe)) {
                        totalSolgte += o.getAntal();
                    }
                }
            }
        }
        return totalSolgte;
    }

    public StringBuilder getUdeståendeProdukterPåUdlejning(Udlejning udlejning) {
        StringBuilder sb = new StringBuilder();
        for (Ordrelinje o : udlejning.getOrdrelinjer()) {
            sb.append(o.getProdukt() + " Antal: " + o.getAntal() + "\n");
        }
        return sb;
    }

    public StringBuilder getAlleUdeståendeProdukter() {
        StringBuilder sb = new StringBuilder();
        for (Udlejning u : getAktuelleUdlejninger()) {
            sb.append(getUdeståendeProdukterPåUdlejning(u));
        }
        return sb;
    }


    public int antalBrugteKlip(LocalDate dato1, LocalDate dato2) {
        int antal = 0;

        if (dato1.isAfter(dato2)) {
            throw new DateTimeException("dato1 skal være før dato 2");
        } else {
            for (Salg s : storage.getSalgs()) {
                if (s.getDato().isAfter(dato1) || s.getDato().equals(dato1) && s.getDato().isBefore(dato2) || s.getDato().isEqual(dato2)) {
                    if (s.getBetalingsform() != null && s.getBetalingsform().getNavn().equals("Klippekort")) {
                        for (Ordrelinje o : s.getOrdrelinjer()) {
                            antal += o.getProdukt().getKlipPris() * o.getAntal();
                        }
                    }
                }

            }
            return antal;
        }
    }

    public int antalSolgteKlip(LocalDate dato1, LocalDate dato2) {
        int antal = 0;

        if (dato1.isAfter(dato2)) {
            throw new DateTimeException("dato1 skal være før dato 2");
        } else {
            for (Salg s : storage.getSalgs()) {
                if (s.getDato().isAfter(dato1) || s.getDato().equals(dato1) && s.getDato().isBefore(dato2) || s.getDato().isEqual(dato2)) {
                    for (Ordrelinje o : s.getOrdrelinjer()) {
                        if (o.getProdukt().getNavn().equals("Klippekort")) {
                            antal += o.getAntal();
                        }
                    }
                }

            }
            return antal;
        }


    }


    public StringBuilder salgForDagen() {
        StringBuilder salg = new StringBuilder();
        for (Salg s : storage.getSalgs()) {
            if (s.getDato().equals(LocalDate.now())) {
                salg.append(s.salgsInfoDag());
            }
        }
        return salg;
    }

    public StringBuilder arragementerForDag(LocalDate date) {
        StringBuilder arrangementer = new StringBuilder();
        for (Arrangement a : storage.getArrangementer()) {
            if(a.getDate().equals(date))
            arrangementer.append(a);
            }
        return arrangementer;
        }




//    Serializable-----------------------------------------------

    /**
     * Loads the storage (including all objects in storage).
     */

    public void loadStorage() {
        try (FileInputStream fileIn = new FileInputStream("storage.ser")) {
            try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
                storage = (Storage) in.readObject();

                System.out.println("Storage loaded from file storage.ser.");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error loading storage object.");
                throw new RuntimeException(ex);
            }
        } catch (IOException ex) {
            System.out.println("Error loading storage object.");
            throw new RuntimeException(ex);
        }

    }

    public void saveStorage() {
        try (FileOutputStream fileOut = new FileOutputStream("storage.ser")) {
            try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(storage);
                System.out.println("Storage saved in file storage.ser.");
            }
        } catch (IOException ex) {
            System.out.println("Error saving storage object.");
            throw new RuntimeException(ex);
        }
    }
}
