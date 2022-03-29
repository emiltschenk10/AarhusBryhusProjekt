package salgTest;

import application.controller.Controller;
import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

class SalgTest {
    private Salg salg = new Salg();
    private Controller controller;
    private Storage storage;

    @BeforeEach
    void setup(){

    }

    @Test
    void createSalg(){
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl","Bajer");
        Produktgruppe produktgruppe1 = controller.createProduktGruppe("Flaske","Pant");
        Produktgruppe produktgruppe2 = controller.createProduktGruppe("Fustage","asdasd");
        Produkt p1 = produktgruppe.createProdukt("Klosterbryg","Bajer",1,0);
        Produkt p2 = produktgruppe1.createProdukt("Klosterbryg","Pant",2,1 );
        Produkt p3 = produktgruppe2.createProdukt("Klosterbryg","asdasd",0, 200);
        Prisliste fredagsbar = controller.createPrisliste("Fredagsbar");

        fredagsbar.addProdukt(p1,38);
        fredagsbar.addProdukt(p2,70);
        fredagsbar.addProdukt(p3,575);
        Kunde kunde = new Kunde("Lars",213123123,"ASDasd");
        Salg s1 = new Salg(LocalDateTime.now(),false,fredagsbar);
        Udlejning u1 = new Udlejning(LocalDate.now(),LocalDateTime.now(),kunde,fredagsbar);
//        Ordrelinje o1 = new Ordrelinje(p1,4,fredagsbar.getProduktpriser().get(p1));
//        Ordrelinje o2 = new Ordrelinje(p2,3,fredagsbar.getProduktpriser().get(p2));
//        Ordrelinje o3 = new Ordrelinje(p3,1,fredagsbar.getProduktpriser().get(p3));
//        s1.addOrdrelinje(o1);
//        s1.addOrdrelinje(o2);
//        u1.addOrdrelinje(o3);
//        ProcentDiscount procentDiscount = new ProcentDiscount("Procent");

        HashMap<Produkt,Integer> map = new HashMap<>();
        map.put(p1, 500);

        for(Produkt produkt : map.keySet()){
            produkt.getNavn();
            map.get(produkt);

        }




    }


}
