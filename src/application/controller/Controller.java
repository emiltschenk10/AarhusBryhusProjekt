package application.controller;

import application.model.Arrangement;
import application.model.Prisliste;
import application.model.Produktgruppe;
import storage.Storage;

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
}
