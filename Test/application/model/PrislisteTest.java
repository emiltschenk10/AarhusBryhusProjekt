package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PrislisteTest {

    private ArrayList<Produkt> produkter = new ArrayList<>();
    private Prisliste prisliste;

    @BeforeEach
    void setUp(){
        Produktgruppe produktgruppe = new Produktgruppe("Fadøl", "Øl på fad");
        Produkt produkt = produktgruppe.createProdukt("Klosterbryg", "God", 1, 0);
        Produkt produkt2 = produktgruppe.createProdukt("Klosterbryg", "God", 1, 0);
        Produktgruppe produktgruppe2 = new Produktgruppe("Flaskeøl", "Øl på flaske");
        Produkt produkt3 = produktgruppe2.createProdukt("IPA", "Nice", 2, 1);
        produkter.add(produkt);
        produkter.add(produkt2);
        produkter.add(produkt3);
        prisliste = new Prisliste("Fredagsbar");
    }
    @Test
    void addProdukt() {
        HashMap<Produkt, Double> result = new HashMap<>();
        result.put(produkter.get(0), 38.0);
        prisliste.addProdukt(produkter.get(0), 38.0);
        assertEquals(result, prisliste.getProduktpriser());
    }

    @Test
    void removeProdukt() {
        HashMap<Produkt, Double> result = new HashMap<>();
        prisliste.addProdukt(produkter.get(0), 38.0);
        prisliste.removeProdukt(produkter.get(0));
        assertEquals(result, prisliste.getProduktpriser());
    }


    @Test
    void getProduktpriser() {
        HashMap<Produkt, Double> result = new HashMap<>();
        result.put(produkter.get(0), 38.0);
        result.put(produkter.get(1), 38.0);
        result.put(produkter.get(2), 70.0);
        prisliste.addProdukt(produkter.get(0), 38.0);
        prisliste.addProdukt(produkter.get(1), 38.0);
        prisliste.addProdukt(produkter.get(2), 70.0);
        assertEquals(result, prisliste.getProduktpriser());
    }


    @Test
    void addArragement() {
        ArrayList<Arrangement> result = new ArrayList<>();
        Arrangement arrangement = new Arrangement("Rundvisning", "Guided  tour", 100);
        result.add(arrangement);
        prisliste.addArragement(arrangement);
        assertEquals(result, prisliste.getArragementer());
    }
}