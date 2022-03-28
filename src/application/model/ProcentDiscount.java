package application.model;

public class ProcentDiscount implements Discount {

    private String navn;
    private double procent;

    public ProcentDiscount(String navn){
        this.navn = navn;
    }

    public void setProcent(double procent){
        this.procent = 1-(procent/100);
    }

    @Override
    public double getDiscount(double pris) {
        return procent*pris;
    }
}
