package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {




    private Storage storage = Storage.getInstance();

    public Produktgruppe createProduktGruppe(String navn, String beskrivelse){
        Produktgruppe produktgruppe = new Produktgruppe(navn, beskrivelse);
        storage.addProduktGruppe(produktgruppe);
        return produktgruppe;
    }

    public Prisliste createPrisliste(String navn){
        Prisliste prisliste = new Prisliste(navn);
        storage.addPrisliste(prisliste);
        return prisliste;
    }

    public Arrangement createArrangement(String navn, String beskrivelse, double pris){
        Arrangement arrangement = new Arrangement(navn, beskrivelse, pris);
        storage.addArrangement(arrangement);
        return arrangement;
    }

    public Salg createSalgUdenParm(){
        Salg salg = new Salg();
        storage.addSalg(salg);
        return salg;
    }

    public Salg createSalgMedParm(LocalDateTime dato, boolean betalt, Prisliste prisliste){
        Salg salg = new Salg(dato, betalt, prisliste);
        storage.addSalg(salg);
        return salg;
    }

    public Udlejning createUdlejning(LocalDate afleveringsdato, LocalDateTime udlejningdato, Kunde kunde, Prisliste prisliste){
        Udlejning udlejning = new Udlejning(afleveringsdato, udlejningdato, kunde, prisliste);
        storage.addUdlejning(udlejning);
        return udlejning;
    }

    public Kunde createKunde(String navn, int tlf, String email){
        Kunde kunde = new Kunde(navn, tlf, email);
        storage.addKunde(kunde);
        return kunde;
    }

    public Betalingsform createBetalingsform(String navn, String type){
        Betalingsform betalingsform = new Betalingsform(navn, type);
        storage.addBetalingsform(betalingsform);
        return betalingsform;
    }

    public static Produkt createProdukt(String navn, String beskrivelse, int klipPris, double pant, Produktgruppe produktgruppe){
        Produkt produkt = produktgruppe.createProdukt(navn, beskrivelse, klipPris, pant);
        return produkt;
    }

    public static Ordrelinje createOrdrelinjeSalg(Produkt produkt, int antal, double pris, Salg salg){
        Ordrelinje ordrelinje = salg.createOrdrelinje(produkt, antal);
        return ordrelinje;
    }

    public static Ordrelinje createOrdrelinjeUdlejning(Produkt produkt, int antal, double pris, Udlejning udlejning){
        Ordrelinje ordrelinje = udlejning.createOrdrelinje(produkt, antal, pris);
        return ordrelinje;
    }

    //Metoder til prisliste
    public static void addProduktTilPrisliste(Produkt produkt,double pris, Prisliste prisliste){
        prisliste.addProdukt(produkt,pris);
    }

    public static void addArragementTilPrisliste(Arrangement arrangement, Prisliste prisliste){
        prisliste.addArragement(arrangement);
    }

    //Metoder til salg
    public static void addOrdrelinjeTilSalg(Ordrelinje ordrelinje, Salg salg){
        salg.addOrdrelinje(ordrelinje);
    }

    public static void setKundePåSalg(Kunde kunde, Salg salg){
        salg.setKunde(kunde);
    }

    public static void setBetalingsformPåSalg(Betalingsform betalingsform, Salg salg){
        salg.setBetalingsform(betalingsform);
    }

    public static void beregnPris(Salg salg){
        beregnPris(salg);
    }

    //Get metoder

    public Prisliste getPrislisteFraSalg(Salg salg){
        return salg.getPrisliste();
    }

    public ArrayList<Prisliste> getAllePrislister(){
        return Storage.getInstance().getPrislister();
    }

    public ArrayList<Ordrelinje> getOrdrelinjer(Salg salg){return salg.getOrdrelinjer();}

    private static void initStorage() {
        Controller controller = new Controller();
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl","Bajer");
        Produktgruppe produktgruppe1 = controller.createProduktGruppe("Flaske","Pant");
        Produktgruppe produktgruppe2 = controller.createProduktGruppe("Fustage","asdasd");
        Produkt p1 = Controller.createProdukt("Klosterbryg","Bajer",1,0, produktgruppe);
        Produkt p2 = Controller.createProdukt("Klosterbryg","Pant",2,1, produktgruppe1);
        Produkt p3 = Controller.createProdukt("Klosterbryg","asdasd",0, 200, produktgruppe2);
        Prisliste pr1 = controller.createPrisliste("Fredagsbar");
        Prisliste pr2 = controller.createPrisliste("Butik");
        Controller.addProduktTilPrisliste(p1, 38.0, pr1);
        Controller.addProduktTilPrisliste(p2, 70.0, pr1);
        Controller.addProduktTilPrisliste(p3, 575, pr1);
        Controller.addProduktTilPrisliste(p1, 38.0, pr2);
        Controller.addProduktTilPrisliste(p2, 36.0, pr2);
//        Controller.addProduktTilPrisliste(p3, 575, pr2);

    }

    public static void init(){initStorage();}
}
