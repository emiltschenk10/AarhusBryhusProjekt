package application.model;

public class AftaltDiscount implements Discount{

    private double pris;

    public AftaltDiscount(double pris){
        this.pris=pris;
    }

    @Override
    public double getDiscount(double pris) {
        return this.pris;
    }
}
