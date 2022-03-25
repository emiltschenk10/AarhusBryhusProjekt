package application.model;

public class ProcentDiscount implements Discount {

    private String navn;
    private double procent;

    public ProcentDiscount(String navn){
        this.navn = navn;
    }

    public void setPris(double procent){
        this.procent = procent;
    }

    @Override
    public double getDiscount() {
        return 0;
    }
}
