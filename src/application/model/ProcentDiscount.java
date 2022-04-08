package application.model;

import java.io.Serializable;

public class ProcentDiscount implements Discount, Serializable {

    private double procent;

    public ProcentDiscount(double procent){
        this.procent = 1-(procent/100);
    }

    @Override
    public double getDiscount(double pris) {
        return procent*pris;
    }
}
