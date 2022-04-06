package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProduktgruppeTest {

    private Produktgruppe produktgruppe;


    @BeforeEach
    void setUp(){
        produktgruppe = new Produktgruppe("Fadøl", "Øl på fad");
    }
    @Test
    void createProdukt() {
        Produkt produkt = produktgruppe.createProdukt("Klosterbryg", "Lys øl", 1, 0);
        assertTrue(produktgruppe.getProdukter().contains(produkt));
        assertEquals(produkt, produktgruppe.getProdukter().get(0));
    }

    @Test
    void getProdukter() {
        Produkt produkt = produktgruppe.createProdukt("Klosterbryg", "Lys øl", 1, 0);
        Produkt produkt2 = produktgruppe.createProdukt("Classic", "Klassisk", 1, 0);
        ArrayList<Produkt> result = new ArrayList<>();
        result.add(produkt);
        result.add(produkt2);
        assertEquals(result, produktgruppe.getProdukter());
    }


    @Test
    void removeProdukt() {
        Produkt produkt = produktgruppe.createProdukt("IPA", "Fancy øl", 1, 0);
        produktgruppe.removeProdukt(produkt);
        assertFalse(produktgruppe.getProdukter().contains(produkt));
    }
}