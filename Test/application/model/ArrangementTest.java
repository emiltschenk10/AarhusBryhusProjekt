package application.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementTest {

    @Test
    void Arrangement(){
        Arrangement arrangement = new Arrangement("Rundvisning", "tour", 100, LocalDate.now());
        assertEquals("Rundvisning", arrangement.getNavn());
        assertEquals("tour", arrangement.getBeskrivelse());
        assertEquals(100, arrangement.getPris());
    }

}