package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KundeTest {

    private Kunde kunde;

    @BeforeEach
    void setUp(){
        kunde = new Kunde("Hans", 12345678, "hans.pr@gmail.com");
    }

    @Test
    void addSalg() {
        ArrayList<Salg> expected = new ArrayList<>();
        Salg salg = new Salg();
        expected.add(salg);
        kunde.addSalg(salg);
        assertEquals(expected, kunde.getSalgArrayList());
    }

    @Test
    void addUdlejning() {
        ArrayList<Udlejning> expected = new ArrayList<>();
        Udlejning udlejning = new Udlejning();
        expected.add(udlejning);
        kunde.addUdlejning(udlejning);
        assertEquals(expected, kunde.getUdlejningArrayList());
    }

    @Test
    void removeUdlejning() {
        ArrayList<Udlejning> expected = new ArrayList<>();
        Udlejning udlejning = new Udlejning();
        expected.add(udlejning);
        kunde.addUdlejning(udlejning);
        Udlejning udlejning2 = new Udlejning();
        kunde.addUdlejning(udlejning2);
        kunde.removeUdlejning(udlejning2);
        assertEquals(expected, kunde.getUdlejningArrayList());
        assertNull(udlejning2.getKunde());
    }

    @Test
    void removeSalg() {
        ArrayList<Salg> expected = new ArrayList<>();
        Salg salg = new Salg();
        expected.add(salg);
        kunde.addSalg(salg);
        Salg salg2 = new Salg();
        kunde.addSalg(salg2);
        kunde.removeSalg(salg2);
        assertEquals(expected, kunde.getSalgArrayList());
        assertNull(salg2.getKunde());
    }

    @Test
    void getUdlejningArrayList() {
        ArrayList<Udlejning> expected = new ArrayList<>();
        Udlejning udlejning = new Udlejning();
        expected.add(udlejning);
        kunde.addUdlejning(udlejning);
        assertEquals(expected, kunde.getUdlejningArrayList());
    }
}