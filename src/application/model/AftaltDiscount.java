package application.model;

import java.io.Serializable;

public class AftaltDiscount implements Discount, Serializable {

    private double pris;

    public AftaltDiscount(double pris){
        this.pris=pris;
    }

    @Override
    public double getDiscount(double pris) {
        return this.pris;
    }
}
