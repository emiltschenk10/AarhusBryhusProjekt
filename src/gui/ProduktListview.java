package gui;

import application.model.Produkt;

public class ProduktListview {

    private Produkt produkt;
    private double pris;

    public ProduktListview(Produkt produkt,double pris){
        this.produkt = produkt;
        this.pris=pris;
    }



    public String toString(){
        return produkt + " " + pris;
    }


}
