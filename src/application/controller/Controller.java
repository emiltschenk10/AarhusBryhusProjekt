package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {


    private Storage storage = Storage.getInstance();

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

    public Arrangement createArrangement(String navn, String beskrivelse, double pris) {
        Arrangement arrangement = new Arrangement(navn, beskrivelse, pris);
        storage.addArrangement(arrangement);
        return arrangement;
    }

    public Salg createSalgUdenParm() {
        Salg salg = new Salg();
        salg.salgsNr();
        storage.addSalg(salg);
        return salg;
    }

    public Salg createSalgMedParm(LocalDate dato, boolean betalt, Prisliste prisliste) {
        Salg salg = new Salg(dato, betalt, prisliste);
        salg.salgsNr();
        storage.addSalg(salg);
        return salg;
    }

    public Udlejning createUdlejning(LocalDate afleveringsdato, LocalDate udlejningdato, Kunde kunde, Prisliste prisliste) {
        Udlejning udlejning = new Udlejning(afleveringsdato, udlejningdato, kunde, prisliste);
        udlejning.udlejningsNr();
        storage.addUdlejning(udlejning);
        return udlejning;
    }

    public Udlejning createUdlejningUdenParm() {
        Udlejning udlejning = new Udlejning();
        udlejning.udlejningsNr();
        storage.addUdlejning(udlejning);
        return udlejning;
    }

    public Kunde createKunde(String navn, int tlf, String email) {
        Kunde kunde = new Kunde(navn, tlf, email);
        storage.addKunde(kunde);
        return kunde;
    }

    public static void setKundePåUdlejning(Kunde kunde, Udlejning udlejning) {
        udlejning.setKunde(kunde);
    }


    public Betalingsform createBetalingsform(String navn, String type) {
        Betalingsform betalingsform = new Betalingsform(navn, type);
        storage.addBetalingsform(betalingsform);
        return betalingsform;
    }

    public static void setBetalingsformPåUdlejning(Betalingsform betalingsform, Udlejning udlejning) {
        udlejning.setBetalingsform(betalingsform);
    }

    public static Produkt createProdukt(String navn, String beskrivelse, int klipPris, double pant, Produktgruppe produktgruppe) {
        Produkt produkt = produktgruppe.createProdukt(navn, beskrivelse, klipPris, pant);
        return produkt;
    }

    public static Ordrelinje createOrdrelinjeSalg(Produkt produkt, int antal, Salg salg) {
        Ordrelinje ordrelinje = salg.createOrdrelinje(produkt, antal);
        return ordrelinje;
    }

    public static Ordrelinje createOrdrelinjeUdlejning(Produkt produkt, int antal, Udlejning udlejning) {
        Ordrelinje ordrelinje = udlejning.createOrdrelinje(produkt, antal);
        return ordrelinje;
    }

    public static ArrayList<Ordrelinje> getOrdrelinjePåUdlejning(Udlejning udlejning) {
        return udlejning.getOrdrelinjer();
    }

    //Metoder til prisliste
    public static void addProduktTilPrisliste(Produkt produkt, double pris, Prisliste prisliste) {
        prisliste.addProdukt(produkt, pris);
    }

    public static void addArragementTilPrisliste(Arrangement arrangement, Prisliste prisliste) {
        prisliste.addArragement(arrangement);
    }

    //Metoder til salg
    public static void addOrdrelinjeTilSalg(Ordrelinje ordrelinje, Salg salg) {
        salg.addOrdrelinje(ordrelinje);
    }

    public static void setKundePåSalg(Kunde kunde, Salg salg) {
        salg.setKunde(kunde);
    }

    public static void setBetalingsformPåSalg(Betalingsform betalingsform, Salg salg) {
        salg.setBetalingsform(betalingsform);
    }

    public static void setSalgSomBetalt(Salg salg,Boolean betalt){
        salg.setBetalt(betalt);
    }

    public static void setUdlejningSomBetalt(Udlejning udlejning,Boolean betalt){
        udlejning.setBetalt(betalt);
    }

    public static void setUdlejningSomUdestående(Udlejning udlejning, Boolean udestående){
        udlejning.setUdestående(udestående);
    }

    public static void setSalgsDato(Salg salg, LocalDate dato) {
        salg.setSalgsDato(dato);
    }

    public static void setAfleveringsDato(Udlejning udlejning, LocalDate date) {
        udlejning.setAfleveringsDato(date);
    }

    public static void setUdleveringsDato(Udlejning udlejning, LocalDate date) {
        udlejning.setUdleveringsDato(date);
    }

    public ArrayList<Arrangement> getArrangementer() {
        return storage.getArrangementer();
    }

    public static void beregnPris(Salg salg) {
        beregnPris(salg);
    }

    //Remove Metoder --------------------------------------------------------------------------------

    public void removeSalg(Salg salg){
        salg.setBetalingsform(null);
        salg.setKunde(null);
        salg.setPrisliste(null);
        storage.removeSalg(salg);
        for(Ordrelinje ordrelinje : salg.getOrdrelinjer()){
            salg.removeOrdrelinje(ordrelinje);
        }
    }




    //Get metoder

    public Prisliste getPrislisteFraSalg(Salg salg) {
        return salg.getPrisliste();
    }

    public ArrayList<Prisliste> getAllePrislister() {
        return Storage.getInstance().getPrislister();
    }

    public ArrayList<Ordrelinje> getOrdrelinjer(Salg salg) {
        return salg.getOrdrelinjer();
    }

    public ArrayList<Produktgruppe> getProduktGrupper() {
        return storage.getProduktGrupper();
    }

    public ArrayList<Produkt> getProduktGruppensProdukter(Produktgruppe produktgruppe) {
        return produktgruppe.getProdukter();
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
            if (dato.equals(s.getDato())) {
                if (s.getPrisliste().getArragementer().contains(arrangement) || arrangement == null) {
                    for (Produktgruppe p : storage.getProduktGrupper()) {
                        if (p == produktgruppe) {
                            for (Produkt pp : produktgruppe.getProdukter()) {
                                if (pp == produkt) {
                                    for (Ordrelinje o : s.getOrdrelinjer()) {
                                        totalSolgte += o.getAntal();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return totalSolgte;
    }

    public Map<Udlejning, Integer> antalIkkeAfleveredeProdukterPrUdlejning() {
        int totalMangel;
        HashMap<Udlejning, Integer> map = new HashMap<>();
        for (Udlejning u : storage.getUdlejnings()) {
            totalMangel = 0;
            if (u.isUdestående()) {
                for (Ordrelinje o : u.getOrdrelinjer()) {
                    totalMangel += o.getAntal();
                    map.put(u, totalMangel);
                }
            }
        }
        return map;
    }

    public StringBuilder getUdeståendeProdukterPåUdlejning(Udlejning udlejning){
        StringBuilder sb = new StringBuilder();
        for (Ordrelinje o : udlejning.getOrdrelinjer()){
            sb.append(o.getProdukt() + " Antal: " + o.getAntal() +  "\n");
        }
        return sb;
    }

    public StringBuilder getAlleUdeståendeProdukter(){
        StringBuilder sb = new StringBuilder();
        for (Udlejning u : getAktuelleUdlejninger()){
            sb.append(getUdeståendeProdukterPåUdlejning(u));
        }
        return sb;
    }


    public int antalBrugteKlip(LocalDate dato1, LocalDate dato2) {
        int antal = 0;

        for (Salg s : storage.getSalgs()) {
            if (s.getDato().isAfter(dato1) || s.getDato().equals(dato1) && s.getDato().isBefore(dato2) || s.getDato().isEqual(dato2)) {
                if (s.getBetalingsform() != null && s.getBetalingsform().getNavn().equals("Klippekort")) {
                    for (Ordrelinje o: s.getOrdrelinjer()) {
                       antal += o.getProdukt().getKlipPris() * o.getAntal();
                    }
                }
            }

        }
        return antal;
    }

    public int antalSolgteKlip(LocalDate dato1, LocalDate dato2) {
        int antal = 0;

        for (Salg s : storage.getSalgs()) {
            if (s.getDato().isAfter(dato1) || s.getDato().equals(dato1) && s.getDato().isBefore(dato2) || s.getDato().isEqual(dato2)) {
                for (Ordrelinje o: s.getOrdrelinjer()) {
                    if (o.getProdukt().getNavn().equals("Klippekort")){
                        antal += o.getAntal();
                    }
                }
            }

        }
        return antal;
    }

    private static void initStorage() {
        Controller controller = new Controller();
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl", "Bajer");
        Produktgruppe produktgruppe1 = controller.createProduktGruppe("Flaske", "Pant");
        Produktgruppe produktgruppe2 = controller.createProduktGruppe("Fustage", "asdasd");
        Produktgruppe produktgruppe3 = controller.createProduktGruppe("Gavekort","Det gaver");
        Produkt p1 = Controller.createProdukt("Klosterbryg", "Bajer", 1, 0, produktgruppe);
        Produkt p2 = Controller.createProdukt("Klosterbryg", "Pant", 2, 1, produktgruppe1);
        Produkt p3 = Controller.createProdukt("Klosterbryg", "asdasd", 0, 200, produktgruppe2);
        Produkt p4 = Controller.createProdukt("Klippekort","Nice",0,0,produktgruppe3);
        Prisliste pr1 = controller.createPrisliste("Fredagsbar");
        Prisliste pr2 = controller.createPrisliste("Butik");
        Prisliste pr3 = controller.createPrisliste("Udlejning");
        Controller.addProduktTilPrisliste(p1, 38.0, pr1);
        Controller.addProduktTilPrisliste(p2, 70.0, pr1);
        Controller.addProduktTilPrisliste(p3, 575, pr1);
        Controller.addProduktTilPrisliste(p1, 38.0, pr2);
        Controller.addProduktTilPrisliste(p2, 36.0, pr2);
        Controller.addProduktTilPrisliste(p3, 575, pr3);
        Controller.addProduktTilPrisliste(p4,650,pr1);
//        Controller.addProduktTilPrisliste(p3, 575, pr2);


        Salg s1 = controller.createSalgMedParm(LocalDate.now(), false, pr1);
        Salg s2 = controller.createSalgMedParm(LocalDate.now(), false, pr1);
        Salg s3 = controller.createSalgMedParm(LocalDate.now(), false, pr1);
        Salg s4 = controller.createSalgMedParm(LocalDate.now(), false, pr1);


        Udlejning u1 = controller.createUdlejning(LocalDate.now(),LocalDate.now().plusDays(5),null,pr1);

        Ordrelinje o1 = Controller.createOrdrelinjeSalg(p2, 2, s1);
        Ordrelinje o2 = Controller.createOrdrelinjeSalg(p1, 2, s2);
        Ordrelinje o3 = Controller.createOrdrelinjeSalg(p1, 2, s3);
        Ordrelinje o4 = Controller.createOrdrelinjeUdlejning(p1,4, u1);
        Ordrelinje o5 = Controller.createOrdrelinjeSalg(p4,5, s4);


        controller.createKunde("Kvickly", 121312312, "dyrt.dk");
        controller.createKunde("Fakta", 22223333, "BudgetKvickly.dk");

       Betalingsform b1 = controller.createBetalingsform("Mobilepay", "Online");
       Betalingsform b2 = controller.createBetalingsform("Mastercard", "Creditkort");
       Betalingsform b3 = controller.createBetalingsform("Bitcoin", "Crypto");
       Betalingsform b4 = controller.createBetalingsform("Klippekort", "Gavekort");

        s1.setBetalingsform(b4);
        s2.setBetalingsform(b1);
        s4.setBetalingsform(b2);
    }

    public static void init() {
        initStorage();
    }

    public static void main(String[] args) {

    }
}
