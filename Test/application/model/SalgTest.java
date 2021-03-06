package application.model;

import application.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SalgTest {

    private Prisliste fredagsbar;
    private Kunde kunde;
    private Produkt p1, p2, p3;

    @BeforeEach
    void setUp() {

        Produktgruppe produktgruppe = new Produktgruppe("Fadøl","Bajer");
        Produktgruppe produktgruppe1 = new Produktgruppe("Flaske","Pant");
        Produktgruppe produktgruppe2 = new Produktgruppe("Fustage","asdasd");
        p1 = produktgruppe.createProdukt("Klosterbryg","Bajer",1,0);
        p2 = produktgruppe1.createProdukt("Klosterbryg","Pant",2,1 );
        p3 = produktgruppe2.createProdukt("Klosterbryg","asdasd",0, 200);
        fredagsbar = new Prisliste("Fredagsbar");

        fredagsbar.addProdukt(p1,38);
        fredagsbar.addProdukt(p2,70);
        fredagsbar.addProdukt(p3,575);
        kunde = new Kunde("Kim",123456,"Vimmersvej 54");

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
        Salg salg = new Salg(LocalDate.now(), false, fredagsbar);
        Ordrelinje ordrelinje = salg.createOrdrelinje(p3, 4);
        Ordrelinje ordrelinje2 = salg.createOrdrelinje(p2, 1);
        ArrayList<Ordrelinje> expected = new ArrayList<>();
        expected.add(ordrelinje);
        expected.add(ordrelinje2);

        assertEquals(expected, salg.getOrdrelinjer());
    }

    @Test
    void getSalgsNr() {
        Salg salg = new Salg();
        assertEquals(3, salg.getSalgsNr());
    }

    @Test
    void getKunde() {
        Salg salg = new Salg();
        salg.setKunde(kunde);
        assertEquals(kunde, salg.getKunde());
        assertTrue(kunde.getSalgArrayList().contains(salg));
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
        Betalingsform mastercard = new Betalingsform("Mastercard", "Kort");
        salg.setBetalingsform(mastercard);
        assertEquals(mastercard, salg.getBetalingsform());
    }

    @Test
    void getBetalingsform() {
        Salg salg = new Salg();
        Betalingsform voucher = new Betalingsform("Voucher", "Kupon");
        salg.setBetalingsform(voucher);
        assertEquals(voucher, salg.getBetalingsform());
    }


    @Test
    void removeOrdrelinje() {
        Salg salg = new Salg(LocalDate.now(), false, fredagsbar);
        Ordrelinje ordrelinje = salg.createOrdrelinje(p1, 4);
        assertTrue(salg.getOrdrelinjer().contains(ordrelinje));

        salg.removeOrdrelinje(ordrelinje);
        assertEquals(0, salg.getOrdrelinjer().size());
        assertFalse(salg.getOrdrelinjer().contains(ordrelinje));
    }

    @Test
    void beregnPris() {
        Salg salg = new Salg(LocalDate.now(),false,fredagsbar);
        salg.createOrdrelinje(p1,1);
        salg.createOrdrelinje(p2,1);
        salg.createOrdrelinje(p3,1);
        assertEquals(884,salg.beregnPris());

    }

    @Test
    void createOrdrelinje() {
        Salg salg = new Salg(LocalDate.now(), false, fredagsbar);
        Ordrelinje ordrelinje = salg.createOrdrelinje(p1, 3);

        assertEquals(salg, ordrelinje.getSalg());
        assertTrue(salg.getOrdrelinjer().contains(ordrelinje));

    }

    @Test
    void testToString() {
        Salg salg = new Salg(LocalDate.now(), false, fredagsbar);
        int nr = salg.getSalgsNr();
        String expected = "Salgs Nr: " + nr + "  Dato: " + LocalDate.now() + "  Betalt: " + false;

        assertEquals(expected, salg.toString());
        salg.setKunde(kunde);
        expected = "Salgs Nr: " + nr + "  Kunde: " + kunde.getNavn() + "  Dato: " + LocalDate.now() + "  Betalt: " + false;
        assertEquals(expected, salg.toString());
    }

    @Test
    void salgsInfoDag() {
        Salg salg = new Salg(LocalDate.now(), false, fredagsbar);
        Ordrelinje or1 = salg.createOrdrelinje(p1, 4);
        Ordrelinje or2 = salg.createOrdrelinje(p2, 2);
        StringBuilder expected = new StringBuilder();
        expected.append("Produkt: " + or1.getProdukt() + " Antal: " + or1.getAntal() + " Pris: " + or1.getPris() + " Samlet pris: " + salg.beregnPris() + " Betalingsform: " + salg.getBetalingsform() + "\n");
        expected.append("Produkt: " + or2.getProdukt() + " Antal: " + or2.getAntal() + " Pris: " + or2.getPris() + " Samlet pris: " + salg.beregnPris() + " Betalingsform: " + salg.getBetalingsform() + "\n");

        assertEquals(expected.toString(), salg.salgsInfoDag().toString());

    }

    @Test
    void salgSituation() {
        Salg salg= new Salg(LocalDate.now(), false, fredagsbar);
        Arrangement arrangement = new Arrangement("Rundvisning", "Tour", 100,LocalDate.now());
        Arrangement arrangement2 = new Arrangement("Fredagsbar", "Bar", 50,LocalDate.now());
        fredagsbar.addArragement(arrangement);

        assertTrue(salg.salgSituation(LocalDate.now(), null));


        assertTrue(salg.salgSituation(LocalDate.now(), arrangement));
        assertFalse(salg.salgSituation(LocalDate.now(), arrangement2));
    }
}