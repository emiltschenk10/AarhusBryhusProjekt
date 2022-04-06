package application.model;

import application.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SalgTest {

    private Prisliste prisliste;
    private Kunde kunde;

    @BeforeEach
    void setUp() {
        Controller controller = new Controller();
        Storage storage = Storage.getInstance();
        Produktgruppe produktgruppe = new Produktgruppe("Fadøl","Bajer");
        Produktgruppe produktgruppe1 = new Produktgruppe("Flaske","Pant");
        Produktgruppe produktgruppe2 = new Produktgruppe("Fustage","asdasd");
        Produkt p1 = produktgruppe.createProdukt("Klosterbryg","Bajer",1,0);
        Produkt p2 = produktgruppe1.createProdukt("Klosterbryg","Pant",2,1 );
        Produkt p3 = produktgruppe2.createProdukt("Klosterbryg","asdasd",0, 200);
        prisliste = new Prisliste("Fredagsbar");

        prisliste.addProdukt(p1,38);
        prisliste.addProdukt(p2,70);
        prisliste.addProdukt(p3,575);
        kunde = new Kunde("Lars",213123123,"ASDasd");

//        Ordrelinje o1 = new Ordrelinje(p1,4,fredagsbar.getProduktpriser().get(p1));
//        Ordrelinje o2 = new Ordrelinje(p2,3,fredagsbar.getProduktpriser().get(p2));
//        Ordrelinje o3 = new Ordrelinje(p3,1,fredagsbar.getProduktpriser().get(p3));
//        s1.addOrdrelinje(o1);
//        s1.addOrdrelinje(o2);
//        u1.addOrdrelinje(o3);
//        ProcentDiscount procentDiscount = new ProcentDiscount("Procent");
    }

    @Test
    void Salg(){
        Salg salg = new Salg();
        assertEquals(LocalDate.now(), salg.getDato());
        assertFalse(salg.isBetalt());
        assertNull(salg.getKunde());
        assertNull(salg.getPrisliste());
    }

    @Test
    void Salg(LocalDate dato, boolean betalt, Prisliste prisliste){
        Salg salg = new Salg(LocalDate.now(), false, prisliste);
    }

    @Test
    void setBetalt() {
        Salg salg = new Salg(LocalDate.now(), false, prisliste);
    }

    @Test
    void getOrdrelinjer() {
    }

    @Test
    void getSalgsNr() {
    }

    @Test
    void getKunde() {
    }

    @Test
    void getDato() {
    }

    @Test
    void getPrisliste() {
    }

    @Test
    void isBetalt() {
    }

    @Test
    void setPrisliste() {
    }

    @Test
    void setSalgsDato() {
    }

    @Test
    void setKunde() {
    }

    @Test
    void setBetalingsform() {
    }

    @Test
    void getBetalingsform() {
    }

    @Test
    void addOrdrelinje() {
    }

    @Test
    void removeOrdrelinje() {
    }

    @Test
    void beregnPris() {
    }

    @Test
    void createOrdrelinje() {
    }

    @Test
    void testToString() {
    }

    @Test
    void salgsInfoDag() {
    }
}