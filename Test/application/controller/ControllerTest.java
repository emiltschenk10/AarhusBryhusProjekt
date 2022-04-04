package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;

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
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl", "Øl");
        assertEquals(1, storage.getProduktGrupper().size());
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
        Arrangement arrangement = controller.createArrangement("Rundvisning", "guided tour", 100);
        assertEquals(1, storage.getArrangementer().size());
        assertTrue(storage.getArrangementer().contains(arrangement));
    }

    @Test
    void createSalgUdenParm() {
        Salg salg = controller.createSalgUdenParm();
        assertEquals(1, storage.getSalgs().size());
        assertTrue(storage.getSalgs().contains(salg));
    }

    @Test
    void createSalgMedParm() {
        Prisliste prisliste = controller.createPrisliste("Fredagsbar");
        Salg salg = controller.createSalgMedParm(LocalDate.now(), true, prisliste);
        assertEquals(1, storage.getSalgs().size());
        assertTrue(storage.getSalgs().contains(salg));
    }

    @Test
    void createUdlejning() {
        Prisliste prisliste = controller.createPrisliste("Fredagsbar");
        Kunde kunde = controller.createKunde("Jørgen", 2133213213, "asdsd@gmail.com");
        Udlejning udlejning = controller.createUdlejning(LocalDate.now().plusDays(2), LocalDate.now(), kunde, prisliste);
        assertEquals(1,  storage.getUdlejnings().size());
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
        Kunde kunde = controller.createKunde("Hanss", 213213213, "adsd@gmail.com");
        assertEquals(1,  storage.getKunder().size());
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
        Betalingsform betalingsform = controller.createBetalingsform("Kontant", "kontanter");
        assertEquals(1, storage.getBetalingsformer().size());
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
    }

    @Test
    void addProduktTilPrisliste() {
    }

    @Test
    void addArragementTilPrisliste() {
    }

    @Test
    void addOrdrelinjeTilSalg() {
    }

    @Test
    void setKundePåSalg() {
    }

    @Test
    void setBetalingsformPåSalg() {
    }

    @Test
    void setSalgSomBetalt() {
    }

    @Test
    void setUdlejningSomBetalt() {
    }

    @Test
    void setUdlejningSomUdestående() {
    }

    @Test
    void setSalgsDato() {
    }

    @Test
    void setAfleveringsDato() {
    }

    @Test
    void setUdleveringsDato() {
    }

    @Test
    void getArrangementer() {
    }

    @Test
    void beregnPris() {
    }

    @Test
    void getPrislisteFraSalg() {
    }

    @Test
    void getAllePrislister() {
    }

    @Test
    void getOrdrelinjer() {
    }

    @Test
    void getProduktGrupper() {

    }

    @Test
    void getProduktGruppensProdukter() {
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