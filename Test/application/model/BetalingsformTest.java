package application.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetalingsformTest {

    @Test
    void Betalingsform(){
        Betalingsform betalingsform = new Betalingsform("Rubler", "Kontant");
        assertEquals("Rubler", betalingsform.getNavn());
        assertEquals("Kontant", betalingsform.getType());
    }

    @Test
    void setType() {
        Betalingsform betalingsform = new Betalingsform("Rubler", "Kontant");
        betalingsform.setType("Kort");
        assertEquals("Kort", betalingsform.getType());
    }
}