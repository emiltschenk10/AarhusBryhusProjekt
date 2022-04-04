package modelTest;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UdlejningTest {


    private Kunde kunde;
    private Udlejning udlejning;

    @BeforeEach
    void setup(){
        Kunde kunde = new Kunde("Kim",123,"Deded");
        this.kunde = kunde;
        Udlejning udlejning = new Udlejning();
        this.udlejning = udlejning;
    }

    @Test
    void opretUdlejning(){
        Prisliste prisliste = new Prisliste("Fredagsbar");
        Udlejning udlejning1 = new Udlejning(LocalDate.of(2022,4,8),LocalDate.of(2022,4,4),kunde,prisliste);
        assertEquals(LocalDate.of(2022,4,8),udlejning1.getAfleveringsDato());
        assertEquals(LocalDate.of(2022,4,4),udlejning1.getUdleveringsDato());
        assertEquals(kunde,udlejning1.getKunde());
        assertEquals(prisliste,udlejning1.getPrisliste());

    }

    @Test
    void setKunde(){
        Udlejning udlejning = new Udlejning();
        Kunde kunde = new Kunde("Lars",1111,"Din");
        udlejning.setKunde(kunde);
        assertEquals(kunde,udlejning.getKunde());
    }

    @Test
    void setBetalingsform(){
        Udlejning udlejning = new Udlejning();
        Betalingsform betalingsform = new Betalingsform("Debit","Kort");
        udlejning.setBetalingsform(betalingsform);
        assertEquals(betalingsform,udlejning.getBetalingsform());

    }



    @Test
    void setAfleveringsdato(){
        Udlejning udlejning = new Udlejning();
        udlejning.setAfleveringsDato(LocalDate.of(2022,4,25));
        assertEquals(LocalDate.of(2022,4,25),udlejning.getAfleveringsDato());

    }

    @Test
    void setUdleveringsdato(){

    }


}
