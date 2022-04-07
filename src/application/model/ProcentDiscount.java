package application.model;

public class ProcentDiscount implements Discount {

    private double procent;

    public ProcentDiscount(double procent){
        this.procent = 1-(procent/100);
    }

    @Override
    public double getDiscount(double pris) {
        return procent*pris;
    }
}
