package application.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Prisliste {
    private String navn;

    private ArrayList<Arrangement> arragementer = new ArrayList<Arrangement>();

    private HashMap<Produkt, Double> produktpriser = new HashMap<>();


    public Prisliste(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void addProdukt(Produkt produkt, double pris){
    produktpriser.put(produkt,pris);
    }

    public void removeProdukt(Produkt produkt){

        if (produktpriser.containsKey(produkt)){
            produktpriser.remove(produkt);
        }
    }

    public void addArragement(Arrangement arrangement){
        if (!arragementer.contains(arrangement)){
            arragementer.add(arrangement);
        }
    }
}
