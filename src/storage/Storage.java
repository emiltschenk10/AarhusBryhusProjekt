package storage;

import application.model.*;

import java.util.ArrayList;

public class Storage {

    private static ArrayList<Salg> salgs;
    private static ArrayList<Udlejning> udlejninger;
    private static ArrayList<Prisliste> prislister;
    private static ArrayList<Arrangement> arrangementer;
    private static ArrayList<Produktgruppe> produktgrupper;
    private static ArrayList<Betalingsform> betalingsformer;
    private static ArrayList<Kunde> kunder;


//-------------------------------------------------------------------------
    public static ArrayList<Salg> getSalgs(){return new ArrayList<>(salgs);}

    public static void addSalg(Salg salg){salgs.add(salg);}

    public static void removeSalg(Salg salg){salgs.remove(salg);}

//-------------------------------------------------------------------------

    public static ArrayList<Udlejning> getUdlejnings(){return new ArrayList<>(udlejninger);}

    public static void addUdlejning(Udlejning udlejning){udlejninger.add(udlejning);}

    public static void removeUdlejning(Udlejning udlejning){udlejninger.remove(udlejning);}

//-------------------------------------------------------------------------

    public static ArrayList<Prisliste> getPrislister(){return new ArrayList<>(prislister);}

    public static void addPrisliste(Prisliste prisliste){
        prislister.add(prisliste);}

    public static void removePrisliste(Prisliste prisliste){
        prislister.remove(prisliste);}

//-------------------------------------------------------------------------

    public static ArrayList<Arrangement> getArrangementer(){return new ArrayList<>(arrangementer);}

    public static void addArrangement(Arrangement arrangement){
        arrangementer.add(arrangement);}

    public static void removeArrangement(Arrangement arrangement){
        arrangementer.remove(arrangement);}


//-------------------------------------------------------------------------

    public static ArrayList<Produktgruppe> getProduktGrupper(){return new ArrayList<>(produktgrupper);}

    public static void addProduktGruppe(Produktgruppe produktgruppe){
        produktgrupper.add(produktgruppe);}

    public static void removeProduktGruppe(Produktgruppe produktgruppe){
        produktgrupper.remove(produktgruppe);}

//-------------------------------------------------------------------------

    public static ArrayList<Betalingsform> getBetalingsformer(){return new ArrayList<>(betalingsformer);}

    public static void addBetalingsform(Betalingsform betalingsform){
        betalingsformer.add(betalingsform);}

    public static void removeBetalingsform(Betalingsform betalingsform){
        betalingsformer.remove(betalingsform);}

//-------------------------------------------------------------------------

    public static ArrayList<Kunde> getKunder(){return new ArrayList<>(kunder);}

    public static void addKunde(Kunde kunde){
        kunder.add(kunde);}

    public static void removeKunde(Kunde kunde){
        kunder.remove(kunde);}


}
