package application.model;

public class AftaltDiscount implements Discount{

    private String navn;
    private double pris;

    public AftaltDiscount(String navn){
        this.navn = navn;
    }

    public void setPris(double pris){
        this.pris = pris;
    }

    @Override
    public double getDiscount(double pris) {
        return this.pris;
    }
}
