package application.controller;

import application.model.Arrangement;
import application.model.Prisliste;
import application.model.Produktgruppe;
import application.model.Salg;
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
        controller = new Controller();
        storage = Storage.getInstance();
    }

    @Test
    void createProduktGruppe() {
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl", "Øl");
        assertEquals(1, storage.getProduktGrupper().size());
        assertTrue(storage.getProduktGrupper().contains(produktgruppe));
    }

    @Test
    void createPrisliste() {
        Prisliste prisliste = controller.createPrisliste("Koncert");
        assertEquals(1, storage.getPrislister().size());
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

    }

    @Test
    void createUdlejningUdenParm() {
    }

    @Test
    void createKunde() {
    }

    @Test
    void setKundePåUdlejning() {
    }

    @Test
    void createBetalingsform() {
    }

    @Test
    void setBetalingsformPåUdlejning() {
    }

    @Test
    void createProdukt() {
    }

    @Test
    void createOrdrelinjeSalg() {
    }

    @Test
    void createOrdrelinjeUdlejning() {
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