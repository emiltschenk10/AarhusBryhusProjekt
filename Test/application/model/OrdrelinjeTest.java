package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdrelinjeTest {

    private Produkt produkt;
    private Prisliste prisliste;

    @BeforeEach
    void setup(){
        Produktgruppe produktgruppe = new Produktgruppe("Øl","Bajer");
        Produkt produkt = produktgruppe.createProdukt("Pils","god",2,1);
        this.produkt=produkt;
        Prisliste prisliste = new Prisliste("Øl prisliste");
        prisliste.addProdukt(produkt,40);
        this.prisliste = prisliste;
    }

    @Test
    void setSalg() {
        Salg salg = new Salg(LocalDate.now(),true,prisliste);
        Ordrelinje ordrelinje = new Ordrelinje(produkt,2,40);
        ordrelinje.setSalg(salg);
        assertEquals(salg,ordrelinje.getSalg());

    }

    @Test
    void setUdlejning() {
        Udlejning udlejning = new Udlejning();
        Ordrelinje ordrelinje = new Ordrelinje(produkt,20,10);
        ordrelinje.setUdlejning(udlejning);
        assertEquals(udlejning,ordrelinje.getUdlejning());
    }

    @Test
    void setDiscount() {
        Ordrelinje ordrelinje = new Ordrelinje(produkt,2,30);
        ProcentDiscount procentDiscount = new ProcentDiscount("Wuuuh");
        ordrelinje.setDiscount(procentDiscount);
        assertEquals(procentDiscount,ordrelinje.getDiscount());
    }

    @Test
    void getPris() {
        Salg salg = new Salg(LocalDate.now(),true,prisliste);
        Ordrelinje ordrelinje = salg.createOrdrelinje(produkt,2);
        ProcentDiscount procentDiscount = new ProcentDiscount("Disc");
        assertEquals(82,ordrelinje.getPris());
        procentDiscount.setProcent(50);
        ordrelinje.setDiscount(procentDiscount);
        assertEquals(42,ordrelinje.getPris());
        AftaltDiscount aftaltDiscount = new AftaltDiscount("Aftalt");
        aftaltDiscount.setPris(10);
        ordrelinje.setDiscount(aftaltDiscount);
        assertEquals(22,ordrelinje.getPris());
        KlipDiscount klipDiscount = new KlipDiscount("Snip snip");
        ordrelinje.setDiscount(klipDiscount);
        assertEquals(0,ordrelinje.getPris());

    }

    @Test
    void beregnUdlejningsPris() {
        Produktgruppe produktgruppe = new Produktgruppe("Fustage","Meget øl");
        Produkt produkt1 = produktgruppe.createProdukt("Carlsberg","Noice",0,200);
        prisliste.addProdukt(produkt1,565);
        Udlejning udlejning = new Udlejning(LocalDate.now(),LocalDate.now().plusDays(2) ,null ,prisliste);
        Ordrelinje ordrelinje = udlejning.createOrdrelinje(produkt1,2);
        assertEquals(1130,ordrelinje.beregnUdlejningsPris());


    }
}