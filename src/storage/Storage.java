package storage;

import application.model.*;

import java.util.ArrayList;

public class Storage {

    private ArrayList<Salg> salgs = new ArrayList<>();
    private ArrayList<Udlejning> udlejninger = new ArrayList<>();
    private ArrayList<Prisliste> prislister = new ArrayList<>();
    private ArrayList<Arrangement> arrangementer = new ArrayList<>();
    private ArrayList<Produktgruppe> produktgrupper = new ArrayList<>();
    private ArrayList<Betalingsform> betalingsformer = new ArrayList<>();
    private ArrayList<Kunde> kunder = new ArrayList<>();
    private static Storage uniqueInstance;

    private Storage(){
    }

    public static synchronized Storage getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Storage();
        }
        return uniqueInstance;
    }


//-------------------------------------------------------------------------
    public ArrayList<Salg> getSalgs(){return new ArrayList<>(salgs);}

    public void addSalg(Salg salg){salgs.add(salg);}

    public void removeSalg(Salg salg){salgs.remove(salg);}

//-------------------------------------------------------------------------

    public ArrayList<Udlejning> getUdlejnings(){return new ArrayList<>(udlejninger);}

    public void addUdlejning(Udlejning udlejning){udlejninger.add(udlejning);}

    public void removeUdlejning(Udlejning udlejning){udlejninger.remove(udlejning);}

//-------------------------------------------------------------------------

    public ArrayList<Prisliste> getPrislister(){return new ArrayList<>(prislister);}

    public void addPrisliste(Prisliste prisliste){
        prislister.add(prisliste);}

    public void removePrisliste(Prisliste prisliste){
        prislister.remove(prisliste);}

//-------------------------------------------------------------------------

    public ArrayList<Arrangement> getArrangementer(){return new ArrayList<>(arrangementer);}

    public void addArrangement(Arrangement arrangement){
        arrangementer.add(arrangement);}

    public void removeArrangement(Arrangement arrangement){
        arrangementer.remove(arrangement);}


//-------------------------------------------------------------------------

    public ArrayList<Produktgruppe> getProduktGrupper(){return new ArrayList<>(produktgrupper);}

    public void addProduktGruppe(Produktgruppe produktgruppe){
        produktgrupper.add(produktgruppe);}

    public void removeProduktGruppe(Produktgruppe produktgruppe){
        produktgrupper.remove(produktgruppe);}

//-------------------------------------------------------------------------

    public ArrayList<Betalingsform> getBetalingsformer(){return new ArrayList<>(betalingsformer);}

    public void addBetalingsform(Betalingsform betalingsform){
        betalingsformer.add(betalingsform);}

    public void removeBetalingsform(Betalingsform betalingsform){
        betalingsformer.remove(betalingsform);}

//-------------------------------------------------------------------------

    public ArrayList<Kunde> getKunder(){return new ArrayList<>(kunder);}

    public void addKunde(Kunde kunde){
        kunder.add(kunde);}

    public void removeKunde(Kunde kunde){
        kunder.remove(kunde);}


}
