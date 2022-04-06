package application.model;

import java.util.ArrayList;

public class Kunde {

    private String navn;

    private int tlfNummer;

    private String adresse;

    private ArrayList<Salg> salgArrayList = new ArrayList<>();

    private ArrayList<Udlejning> udlejningArrayList = new ArrayList<>();


    public Kunde(String navn, int tlfNummer, String adresse){
        this.navn = navn;
        this.tlfNummer = tlfNummer;
        this.adresse = adresse;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getTlfNummer() {
        return tlfNummer;
    }

    public void addSalg(Salg salg){
        if (!salgArrayList.contains(salg)){
            salgArrayList.add(salg);
            salg.setKunde(this);
        }
    }

    public void addUdlejning(Udlejning udlejning){
        if (!udlejningArrayList.contains(udlejning)){
            udlejningArrayList.add(udlejning);
            udlejning.setKunde(this);
        }
    }

    public void removeUdlejning(Udlejning udlejning){
        if (udlejningArrayList.contains(udlejning)){
            udlejningArrayList.remove(udlejning);
            udlejning.setKunde(null);
        }
    }

    public void removeSalg(Salg salg){
        if (salgArrayList.contains(salg)) {
            salgArrayList.remove(salg);
            salg.setKunde(null);
        }
    }

    public ArrayList<Udlejning> getUdlejningArrayList() {
        return new ArrayList<>(udlejningArrayList);
    }

    public ArrayList<Salg> getSalgArrayList() {
        return new ArrayList<>(salgArrayList);
    }

    public String toString(){
        return navn + "   " + tlfNummer + "   " + adresse;
    }
}
