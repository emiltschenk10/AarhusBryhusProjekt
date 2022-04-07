package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;
    private Storage storage;

    @BeforeEach
    void setUp(){
        this.controller = new Controller();
        this.storage = Storage.getInstance();
    }

    @Test
    void createProduktGruppe() {
        int size = controller.getProduktGrupper().size();
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl", "Øl");
        assertEquals(size + 1, controller.getProduktGrupper().size());
        assertTrue(storage.getProduktGrupper().contains(produktgruppe));
    }

    @Test
    void createPrisliste() {
        int size = storage.getPrislister().size();
        Prisliste prisliste = controller.createPrisliste("Koncert");
        assertEquals(size + 1, storage.getPrislister().size());
        assertTrue(storage.getPrislister().contains(prisliste));
    }

    @Test
    void createArrangement() {
        int size = controller.getArrangementer().size();
        Arrangement arrangement = controller.createArrangement("Rundvisning", "guided tour", 100);
        assertEquals(size + 1, controller.getArrangementer().size());
        assertTrue(storage.getArrangementer().contains(arrangement));
    }

    @Test
    void createSalgUdenParm() {
        int size = storage.getSalgs().size();
        Salg salg = controller.createSalgUdenParm();
        assertEquals(size + 1, storage.getSalgs().size());
        assertTrue(storage.getSalgs().contains(salg));
    }

    @Test
    void createSalgMedParm() {
        int size = storage.getSalgs().size();
        Prisliste prisliste = controller.createPrisliste("Fredagsbar");
        Salg salg = controller.createSalgMedParm(LocalDate.now(), true, prisliste);
        assertEquals(size + 1, storage.getSalgs().size());
        assertTrue(storage.getSalgs().contains(salg));
    }

    @Test
    void createUdlejning() {
        int size = storage.getUdlejnings().size();
        Prisliste prisliste = controller.createPrisliste("Fredagsbar");
        Kunde kunde = controller.createKunde("Jørgen", 2133213213, "asdsd@gmail.com");
        Udlejning udlejning = controller.createUdlejning(LocalDate.now().plusDays(2), LocalDate.now(), kunde, prisliste);
        assertEquals(size + 1,  storage.getUdlejnings().size());
        assertTrue(storage.getUdlejnings().contains(udlejning));
    }

    @Test
    void createUdlejningUdenParm() {
        Udlejning udlejning = controller.createUdlejningUdenParm();
        assertEquals(1, storage.getUdlejnings().size());
        assertTrue(storage.getUdlejnings().contains(udlejning));
    }

    @Test
    void createKunde() {
        int size = storage.getKunder().size();
        Kunde kunde = controller.createKunde("Hanss", 213213213, "adsd@gmail.com");
        assertEquals(size + 1,  storage.getKunder().size());
        assertTrue(storage.getKunder().contains(kunde));
    }

    @Test
    void setKundePåUdlejning() {
        Kunde kunde = controller.createKunde("Hanss", 213213213, "adsd@gmail.com");
        Udlejning udlejning = controller.createUdlejningUdenParm();
        Controller.setKundePåUdlejning(kunde, udlejning);
        assertEquals(kunde, udlejning.getKunde());
        assertTrue(kunde.getUdlejningArrayList().contains(udlejning));
    }

    @Test
    void createBetalingsform() {
        int size = storage.getBetalingsformer().size();
        Betalingsform betalingsform = controller.createBetalingsform("Kontant", "kontanter");
        assertEquals(size + 1, storage.getBetalingsformer().size());
        assertTrue(storage.getBetalingsformer().contains(betalingsform));

    }

    @Test
    void setBetalingsformPåUdlejning() {
        Betalingsform betalingsform = controller.createBetalingsform("Kontant", "Kontanter");
        Kunde kunde = controller.createKunde("Hanss", 213213213, "adsd@gmail.com");
        Prisliste prisliste = controller.createPrisliste("Fredagsbar");
        Udlejning udlejning = controller.createUdlejningUdenParm();
        Udlejning udlejning2 = controller.createUdlejning(LocalDate.now().plusDays(2), LocalDate.now(), kunde ,prisliste);
        udlejning.setBetalingsform(betalingsform);
        udlejning2.setBetalingsform(betalingsform);
        assertEquals(betalingsform, udlejning.getBetalingsform());
        assertEquals(betalingsform, udlejning2.getBetalingsform());
    }

    @Test
    void createProdukt() {
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl", "sadsa");
        Produkt produkt = Controller.createProdukt("Classic", "asd", 1, 1, produktgruppe);
        assertEquals(1, produktgruppe.getProdukter().size());
        assertTrue(produktgruppe.getProdukter().contains(produkt));
    }

    @Test
    void createOrdrelinjeSalg() {
        Prisliste prisliste = controller.createPrisliste("Fredagsbar");
        Salg salg = controller.createSalgMedParm(LocalDate.now(), false, prisliste);
        Salg salg2 = controller.createSalgUdenParm();
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl", "asdsd");
        Produkt produkt = Controller.createProdukt("Classic", "sdsad", 1, 0, produktgruppe);
        Controller.addProduktTilPrisliste(produkt, 40, prisliste);
        Controller.setPrislistePåSalg(salg2, prisliste);
        Ordrelinje ordrelinje = Controller.createOrdrelinjeSalg(produkt, 5, salg);
        Ordrelinje ordrelinje2 = Controller.createOrdrelinjeSalg(produkt, 4, salg2);

        assertEquals(1, controller.getOrdrelinjer(salg).size());
        assertTrue(controller.getOrdrelinjer(salg).contains(ordrelinje));

        assertEquals(1, controller.getOrdrelinjer(salg2).size());
        assertTrue(controller.getOrdrelinjer(salg2).contains(ordrelinje2));

    }

    @Test
    void createOrdrelinjeUdlejning() {
        Kunde kunde = controller.createKunde("Hanss", 213213213, "adsd@gmail.com");
        Prisliste prisliste = controller.createPrisliste("Udlejning");
        Udlejning udlejning = controller.createUdlejning(LocalDate.now().plusDays(2), LocalDate.now(),kunde ,prisliste);
        Udlejning udlejning2 = controller.createUdlejningUdenParm();
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fustage", "asd");
        Produkt produkt = Controller.createProdukt("Klosterbryg", "asd", 0, 200, produktgruppe);
        Controller.addProduktTilPrisliste(produkt, 575, prisliste);
        //TODO LAV setPrislistePåUdlejning() i controller
        udlejning2.setPrisliste(prisliste);
        Ordrelinje ordrelinje = Controller.createOrdrelinjeUdlejning(produkt, 2, udlejning);
        Ordrelinje ordrelinje2 = Controller.createOrdrelinjeUdlejning(produkt, 3, udlejning2);

        assertEquals(1, Controller.getOrdrelinjePåUdlejning(udlejning).size());
        assertTrue(Controller.getOrdrelinjePåUdlejning(udlejning).contains(ordrelinje));

        assertEquals(1, Controller.getOrdrelinjePåUdlejning(udlejning2).size());
        assertTrue(Controller.getOrdrelinjePåUdlejning(udlejning2).contains(ordrelinje2));


    }

    @Test
    void getOrdrelinjePåUdlejning() {
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl", "Øl");
        Produkt produkt = Controller.createProdukt("Klosterbryg", "Lys øl", 1, 0, produktgruppe);
        Prisliste prisliste = controller.createPrisliste("Fredagsbar");
        Controller.addProduktTilPrisliste(produkt, 38, prisliste);
        Udlejning udlejning = controller.createUdlejningUdenParm();
        Controller.setPrislistePåUdlejning(udlejning, prisliste);
        Ordrelinje result = Controller.createOrdrelinjeUdlejning(produkt, 4, udlejning);
        Ordrelinje result2 = Controller.createOrdrelinjeUdlejning(produkt, 2, udlejning);
        ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();
        ordrelinjer.add(result);
        ordrelinjer.add(result2);


        assertEquals(ordrelinjer, Controller.getOrdrelinjePåUdlejning(udlejning));
        assertTrue(Controller.getOrdrelinjePåUdlejning(udlejning).contains(result));
        assertTrue(Controller.getOrdrelinjePåUdlejning(udlejning).contains(result2));
    }

    @Test
    void addProduktTilPrisliste() {
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl", "Øl");
        Produkt produkt3 = Controller.createProdukt("Klosterbryg", "Lys øl", 1, 0, produktgruppe);
        Prisliste prisliste1 = controller.createPrisliste("Fredagsbar");
        Controller.addProduktTilPrisliste(produkt3, 38, prisliste1);

        //assertEquals(1, prisliste1.getProduktpriser().size());
        assertTrue(prisliste1.getProduktpriser().containsKey(produkt3));

    }

    @Test
    void addArragementTilPrisliste() {
        Arrangement arrangement = controller.createArrangement("Rundvisning", "Guided tour", 100);
        Prisliste prisliste = controller.createPrisliste("Butik");
        Controller.addArragementTilPrisliste(arrangement, prisliste);
        assertEquals(arrangement, prisliste.getArragementer().get(0));
        assertTrue(prisliste.getArragementer().contains(arrangement));
        assertTrue(storage.getArrangementer().contains(arrangement));
    }

    @Test
    void addOrdrelinjeTilSalg() {
        //Skal måske fjernes da den ikke bruges

    }

    @Test
    void setKundePåSalg() {
        Salg salg = controller.createSalgUdenParm();
        Kunde k = controller.createKunde("Hans", 21231231, "Hans.p@gmail.com");
        Controller.setKundePåSalg(k, salg);
        assertEquals(k, salg.getKunde());
        assertEquals(salg, k.getSalgArrayList().get(0));
        assertTrue(k.getSalgArrayList().contains(salg));
    }

    @Test
    void setBetalingsformPåSalg() {
        Salg salg = controller.createSalgUdenParm();
        Betalingsform betalingsform = controller.createBetalingsform("Euro", "Udenlandsk valuta");
        Controller.setBetalingsformPåSalg(betalingsform, salg);
        assertEquals(betalingsform, salg.getBetalingsform());
    }

    @Test
    void setSalgSomBetalt() {
        Prisliste prisliste = controller.createPrisliste("Fredagsbar");
        Salg salg = controller.createSalgMedParm(LocalDate.now(), false, prisliste);
        Controller.setSalgSomBetalt(salg, true);
        assertTrue(salg.isBetalt());
    }

    @Test
    void setUdlejningSomBetalt() {
        Kunde k = controller.createKunde("Hans", 213213323, "asd@gmail.com");
        Prisliste p = controller.createPrisliste("Fredagsbar");
        Udlejning udlejning = controller.createUdlejning(LocalDate.now(), LocalDate.now().minusDays(2), k, p);
        Controller.setUdlejningSomBetalt(udlejning, true);
        assertTrue(udlejning.isBetalt());
    }

    @Test
    void setUdlejningSomUdestående() {
        Udlejning udlejning = new Udlejning();
        Controller.setUdlejningSomUdestående(udlejning, true);
        assertTrue(udlejning.isUdestående());
        Controller.setUdlejningSomUdestående(udlejning, false);
        assertFalse(udlejning.isUdestående());
    }

    @Test
    void setSalgsDato() {
        Salg salg = controller.createSalgUdenParm();
        Controller.setSalgsDato(salg, LocalDate.now());
        assertEquals(LocalDate.now(), salg.getDato());
    }

    @Test
    void setAfleveringsDato() {
        Udlejning udlejning = controller.createUdlejningUdenParm();
        Controller.setAfleveringsDato(udlejning, LocalDate.now());
        assertEquals(LocalDate.now(), udlejning.getAfleveringsDato());
    }

    @Test
    void setUdleveringsDato() {
        Udlejning udlejning = controller.createUdlejningUdenParm();
        Controller.setUdleveringsDato(udlejning, LocalDate.now());
        assertEquals(LocalDate.now(), udlejning.getUdleveringsDato());
    }

    @Test
    void beregnPris() {
    }


    @Test
    void getAktuelleSalg() {
    }

    @Test
    void getAktuelleUdlejninger() {
    }

    @Test
    void alleProdukter() {
    }

    @Test
    void tilgængeligeProdukterTilPrisliste() {
    }

    @Test
    void salgForDato() {
    }

    @Test
    void salgForProduktogProduktgruppe() {

        Produktgruppe produktgruppe = controller.createProduktGruppe("Mad","Det mad");
        Produkt produkt = Controller.createProdukt("Aftensmad","Det mad",2,2,produktgruppe);
        Prisliste prisliste = new Prisliste("Madvarer");
        Salg salg = controller.createSalgMedParm(LocalDate.now(), false, prisliste);
        prisliste.addProdukt(produkt,100);
        Controller.setPrislistePåSalg(salg,prisliste);
        Ordrelinje ordrelinje = Controller.createOrdrelinjeSalg(produkt,2,salg);
        Arrangement arrangement = controller.createArrangement("Fredagsbar", "Bar", 50);
        Controller.addArragementTilPrisliste(arrangement, prisliste);

        assertTrue(salg.getPrisliste().getArragementer().contains(arrangement));
        assertEquals(2,controller.salgForProduktogProduktgruppe(produktgruppe,produkt,salg.getDato(),arrangement));
        assertEquals(0,controller.salgForProduktogProduktgruppe(produktgruppe,produkt,LocalDate.of(1100,11,11),arrangement));
        assertEquals(2,controller.salgForProduktogProduktgruppe(produktgruppe,produkt,salg.getDato(),null));
    }

    @Test
    void antalIkkeAfleveredeProdukterPrUdlejning() {
    }

    @Test
    void getUdeståendeProdukterPåUdlejning() {
    }

    @Test
    void getAlleUdeståendeProdukter() {
    }

    @Test
    void antalBrugteKlip() {
    }

    @Test
    void antalSolgteKlip() {
    }
}