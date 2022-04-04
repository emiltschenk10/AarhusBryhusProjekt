package modelTest;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UdlejningTest {

    private Kunde kunde;
    @BeforeEach
    void setup(){
        Kunde kunde = new Kunde("Kim",123,"Deded");
        this.kunde = kunde;
    }

    @Test
    void opretUdlejning(){
        Prisliste prisliste = new Prisliste("Fredagsbar");
        Udlejning udlejning = new Udlejning(LocalDate.of(2022,4,8),LocalDate.of(2022,4,4),kunde,prisliste);
        assertEquals(LocalDate.of(2022,4,8),udlejning.getAfleveringsDato());
        assertEquals(LocalDate.of(2022,4,4),udlejning.getUdleveringsDato());
        //TODO
    }

    @Test
    void setKunde(){

    }


}
