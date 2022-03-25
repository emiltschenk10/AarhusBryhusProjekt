package application.model;

public class KlipDiscount implements Discount {

    private String navn;

    public KlipDiscount(String navn){
        this.navn = navn;
    }
    @Override
    public double getDiscount(double pris) {
        return 0;
    }
}
