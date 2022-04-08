package application.model;

import java.io.Serializable;

public class KlipDiscount implements Discount, Serializable {



    public KlipDiscount(){}

    @Override
    public double getDiscount(double pris) {
        return 0;
    }
}
