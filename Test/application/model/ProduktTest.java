package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProduktTest {

    private Produkt produkt;
    private Produktgruppe produktgruppe;

    @BeforeEach
    void setUp(){
        produktgruppe = new Produktgruppe("Fadøl", "Øl på fad");
        produkt = produktgruppe.createProdukt("Klosterbryg", "Lys øl", 1, 0);
    }

    @Test
    void getProduktgruppe() {
        assertEquals(produktgruppe, produkt.getProduktgruppe());
    }

    @Test
    void setKlipPris() {
        produkt.setKlipPris(2);
        assertEquals(2, produkt.getKlipPris());
    }

    @Test
    void setPant() {
        produkt.setPant(1);
        assertEquals(1, produkt.getPant());
    }
}