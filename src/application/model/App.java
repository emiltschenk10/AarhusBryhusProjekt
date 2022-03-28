package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        Produktgruppe produktgruppe = new Produktgruppe("Fadøl","Bajer");
        Produktgruppe produktgruppe1 = new Produktgruppe("Flaske","Pant");
        Produktgruppe produktgruppe2 = new Produktgruppe("Fustage","asdasd");
        Produkt p1 = produktgruppe.createProdukt("Klosterbryg","Bajer",1,0);
        Produkt p2 = produktgruppe1.createProdukt("Klosterbryg","Pant",2,1 );
        Produkt p3 = produktgruppe2.createProdukt("Klosterbryg","asdasd",0, 200);
        Prisliste fredagsbar = new Prisliste("Fredagsbar");
        fredagsbar.addProdukt(p1,38);
        fredagsbar.addProdukt(p2,70);
        fredagsbar.addProdukt(p3,575);
        Kunde kunde = new Kunde("Lars",213123123,"ASDasd");
        Salg s1 = new Salg(LocalDateTime.now(),false,fredagsbar);
        Udlejning u1 = new Udlejning(LocalDate.now(),LocalDateTime.now(),kunde,fredagsbar);
        Ordrelinje o1 = new Ordrelinje(p1,4,fredagsbar.getProduktpriser().get(p1));
        Ordrelinje o2 = new Ordrelinje(p2,3,fredagsbar.getProduktpriser().get(p2));
        Ordrelinje o3 = new Ordrelinje(p3,1,fredagsbar.getProduktpriser().get(p3));
        s1.addOrdrelinje(o1);
        s1.addOrdrelinje(o2);
        u1.addOrdrelinje(o3);
        ProcentDiscount procentDiscount = new ProcentDiscount("Procent");

        procentDiscount.setProcent(0.5);
        o1.setDiscount(procentDiscount);
        System.out.println(s1.beregnPris());
        System.out.println(o1.getPris());
        System.out.println(o2.getPris());
        System.out.println(u1.beregnPris());
    }
}
