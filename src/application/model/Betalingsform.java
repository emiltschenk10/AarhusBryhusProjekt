package application.model;

import java.io.Serializable;

public class Betalingsform implements Serializable {
    private String navn;
    private String type;

    public Betalingsform(String navn, String type){
        this.navn = navn;
        this.type = type;
    }


    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        return navn + "   " + type;
    }
}
