package application.model;

public class KlipDiscount implements Discount {



    public KlipDiscount(){}

    @Override
    public double getDiscount(double pris) {
        return 0;
    }
}
