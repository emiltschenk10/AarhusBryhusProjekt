package application.model;

import application.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SalgTest {

    private Prisliste fredagsbar;
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
        fredagsbar = new Prisliste("Fredagsbar");

        fredagsbar.addProdukt(p1,38);
        fredagsbar.addProdukt(p2,70);
        fredagsbar.addProdukt(p3,575);
        kunde = new Kunde("Kim",123456,"Vimmersvej 54");

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
    void SalgMedParm(){
        Salg salg = new Salg(LocalDate.of(2022, 4, 4), false, fredagsbar);
        assertEquals(LocalDate.of(2022, 4, 4), salg.getDato());
        assertFalse(salg.isBetalt());
        assertEquals(fredagsbar, salg.getPrisliste());
    }

    @Test
    void setBetalt() {
        Salg salg = new Salg(LocalDate.now(), false, fredagsbar);
        Exception exception = assertThrows(NullPointerException.class, () -> salg.setBetalt(null));
        NullPointerException nullPointerException = new NullPointerException();
        assertEquals(nullPointerException.getMessage(), exception.getMessage());

        salg.setBetalt(false);
        assertFalse(salg.isBetalt());

        salg.setBetalt(true);
        assertTrue(salg.isBetalt());
    }

    @Test
    void getOrdrelinjer() {
    }

    @Test
    void getSalgsNr() {
    }

    @Test
    void getKunde() {
        Salg salg = new Salg();
        salg.setKunde(kunde);
        assertEquals(kunde, salg.getKunde());
    }

    @Test
    void getDato() {
        Salg salg = new Salg(LocalDate.of(2022, 4, 5), false, null);
        assertEquals(LocalDate.of(2022, 4, 5), salg.getDato());
    }

    @Test
    void getPrisliste() {
        Salg salg = new Salg(LocalDate.now(), false, fredagsbar);
        assertEquals(fredagsbar, salg.getPrisliste());
    }

    @Test
    void isBetalt() {
        Salg salg = new Salg(LocalDate.now(), true, fredagsbar);
        assertTrue(salg.isBetalt());
    }

    @Test
    void setPrisliste() {
        Salg salg = new Salg(LocalDate.now(), false, null);
        salg.setPrisliste(fredagsbar);
        assertEquals(fredagsbar, salg.getPrisliste());
    }

    @Test
    void setSalgsDato() {
        Salg salg = new Salg();
        salg.setSalgsDato(LocalDate.of(2022, 4, 9));
        assertEquals(LocalDate.of(2022, 4, 9), salg.getDato());
    }

    @Test
    void setKunde() {
        Salg salg = new Salg();
        salg.setKunde(kunde);
        assertEquals(kunde, salg.getKunde());

        Kunde kunde2 = new Kunde("Lars", 89076, "Skolegade");
        salg.setKunde(kunde2);
        assertEquals(kunde2, salg.getKunde());
        assertFalse(kunde.getSalgArrayList().contains(salg));
    }

    @Test
    void setBetalingsform() {
        Salg salg = new Salg();
        Betalingsform Mastercard = new Betalingsform("Mastercard", "Kort");
        salg.setBetalingsform(Mastercard);
        assertEquals(Mastercard, salg.getBetalingsform());
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