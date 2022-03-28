package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Controller {

    public static Produktgruppe createProduktGruppe(String navn, String beskrivelse){
        Produktgruppe produktgruppe = new Produktgruppe(navn, beskrivelse);
        Storage.addProduktGruppe(produktgruppe);
        return produktgruppe;
    }

    public static Prisliste createPrisliste(String navn){
        Prisliste prisliste = new Prisliste(navn);
        Storage.addPrisliste(prisliste);
        return prisliste;
    }

    public static Arrangement createArrangement(String navn, String beskrivelse, double pris){
        Arrangement arrangement = new Arrangement(navn, beskrivelse, pris);
        Storage.addArrangement(arrangement);
        return arrangement;
    }

    public static Salg createSalgUdenParm(){
        Salg salg = new Salg();
        Storage.addSalg(salg);
        return salg;
    }

    public static Salg createSalgMedParm(LocalDateTime dato, boolean betalt, Prisliste prisliste){
        Salg salg = new Salg(dato, betalt, prisliste);
        Storage.addSalg(salg);
        return salg;
    }

    public static Udlejning createUdlejning(LocalDate afleveringsdato, LocalDateTime udlejningdato, Kunde kunde, Prisliste prisliste){
        Udlejning udlejning = new Udlejning(afleveringsdato, udlejningdato, kunde, prisliste);
        Storage.addUdlejning(udlejning);
        return udlejning;
    }

    public static Kunde createKunde(String navn, int tlf, String email){
        Kunde kunde = new Kunde(navn, tlf, email);
        Storage.addKunde(kunde);
        return kunde;
    }

    public static Betalingsform createBetalingsform(String navn, String type){
        Betalingsform betalingsform = new Betalingsform(navn, type);
        Storage.addBetalingsform(betalingsform);
        return betalingsform;
    }

}
