package application.model;

public class Ordrelinje {
    private Produkt produkt;
    private int antal;
    private double pris;
    private Salg salg;
    private Udlejning udlejning;


    public Ordrelinje(Produkt produkt,int antal, double pris){
        this.produkt = produkt;
        this.antal = antal;
        this.pris = pris;
    }

    public int getAntal() {
        return antal;
    }


    public Produkt getProdukt() {
        return produkt;
    }

    public Salg getSalg() {
        return salg;
    }

    public Udlejning getUdlejning() {
        return udlejning;
    }

    public void setSalg(Salg salg) {
        this.salg = salg;
    }

    public void setUdlejning(Udlejning udlejning) {
        this.udlejning = udlejning;
    }
}