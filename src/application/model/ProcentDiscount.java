package application.model;

public class ProcentDiscount implements Discount {

    private String navn;
    private double procent;

    public ProcentDiscount(String navn){
        this.navn = navn;
    }

    public void setProcent(double procent){
        this.procent = procent;
    }

    @Override
    public double getDiscount(double pris) {
        return procent;
    }
}
