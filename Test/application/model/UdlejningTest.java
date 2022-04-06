package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UdlejningTest {

    private Udlejning udlejning;
    private Produkt produkt, produkt2;
    private Prisliste prisliste;

    @BeforeEach
    void setUp() {
        Kunde k = new Kunde("Hans", 21212313, "sadsad@gmail.com");
        prisliste = new Prisliste("Fredagsbar");
        Produktgruppe pg = new Produktgruppe("Fadøl", "Fad");
        produkt = pg.createProdukt("Klosterbryg", "Lys øl", 1, 1);
        produkt2 = pg.createProdukt("IPA", "God øl", 2, 200);
        prisliste.addProdukt(produkt, 38);
        prisliste.addProdukt(produkt2, 500);

        udlejning = new Udlejning(LocalDate.now().plusDays(3), LocalDate.now(),k , prisliste);

    }

    @Test
    void createOrdrelinje() {
        Ordrelinje ordrelinje = udlejning.createOrdrelinje(produkt, 2);
        assertEquals(produkt, ordrelinje.getProdukt());
        assertEquals(2, ordrelinje.getAntal());
        assertEquals(udlejning, ordrelinje.getUdlejning());
    }

    @Test
    void addOrdrelinje() {
        Ordrelinje ordrelinje = new Ordrelinje(produkt, 4, 38);
        udlejning.addOrdrelinje(ordrelinje);
        assertTrue(udlejning.getOrdrelinjer().contains(ordrelinje));
        assertSame(ordrelinje.getUdlejning(), udlejning);
    }

    @Test
    void removeOrdrelinje() {
        ArrayList<Ordrelinje> expected = new ArrayList<>();
        Ordrelinje ordrelinje = udlejning.createOrdrelinje(produkt, 2);
        udlejning.removeOrdrelinje(ordrelinje);
        assertEquals(expected, udlejning.getOrdrelinjer());
    }

    @Test
    void beregnPantPris() {
        udlejning.createOrdrelinje(produkt, 2);
        udlejning.createOrdrelinje(produkt2, 2);
        double expected = (produkt.getPant() * 2) +
                produkt2.getPant() * 2;
        assertEquals(expected, udlejning.beregnPantPris());
    }

    @Test
    void beregnRestPris() {
        udlejning.createOrdrelinje(produkt, 2);
        udlejning.createOrdrelinje(produkt2, 2);
        double expected = (prisliste.getProduktpriser().get(produkt) * 2) +
                prisliste.getProduktpriser().get(produkt2) * 2;
        assertEquals(expected, udlejning.beregnRestPris());
    }
}