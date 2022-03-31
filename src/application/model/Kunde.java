package application.model;

import java.util.ArrayList;

public class Kunde {

    private String navn;

    private int tlfNummer;

    private String email;

    private ArrayList<Salg> salgArrayList = new ArrayList<>();

    private ArrayList<Udlejning> udlejningArrayList = new ArrayList<Udlejning>();


    public Kunde(String navn, int tlfNummer, String email){
        this.navn = navn;
        this.tlfNummer = tlfNummer;
        this.email = email;
    }

    public String getNavn() {
        return navn;
    }

    public String getEmail() {
        return email;
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
        }
    }

    public void removeSalg(Salg salg){
        if (salgArrayList.contains(salg)) {
            salgArrayList.remove(salg);
            salg.setKunde(null);
        }
    }

    public String toString(){
        return navn + "   " + tlfNummer + "   " + email;
    }
}
