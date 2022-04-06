package application.model;

public class Ordrelinje {
    private Produkt produkt;
    private int antal;
    private double pris;
    private Salg salg;
    private Udlejning udlejning;
    private Discount discount;


    Ordrelinje(Produkt produkt,int antal, double pris){
        this.produkt = produkt;
        this.antal = antal;
        this.pris = pris;
    }

    public int getAntal() {
        return antal;
    }


    public Produkt getProdukt() {
        return produkt;
    }

    public Salg getSalg() {
        return salg;
    }

    public Udlejning getUdlejning() {
        return udlejning;
    }

    public Discount getDiscount(){return discount;}

    public void setSalg(Salg salg) {
        this.salg = salg;
    }

    public void setUdlejning(Udlejning udlejning) {
        this.udlejning = udlejning;
    }

    public void setDiscount(Discount discount){
        this.discount = discount;
    }

    public double getPris(){
        double result = 0;
        if(udlejning!=null){
            result = produkt.getPant()*antal;
        }else if (discount!= null) {
            result = (discount.getDiscount(pris)+produkt.getPant()) * antal;
            if(discount.getDiscount(pris)==0){
                result = 0;
            }
        }else{
            result = (pris+produkt.getPant())*antal;
        }
        return result;
    }

    public double beregnUdlejningsPris(){
        if (discount != null){
            return discount.getDiscount(pris) * antal;
        }
        else {
            return pris * antal;
        }
    }

    public String toString(){
        return antal + " " + produkt + " " + getPris();
    }
}
