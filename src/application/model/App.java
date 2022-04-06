package application.model;

import java.time.LocalDate;

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
        Salg s1 = new Salg(LocalDate.now(),false,fredagsbar);
        Udlejning u1 = new Udlejning(LocalDate.now(),LocalDate.now(),kunde,fredagsbar);
        Ordrelinje o1 = s1.createOrdrelinje(p1,4);
        Ordrelinje o2 = s1.createOrdrelinje(p2,3);
        Ordrelinje o3 = u1.createOrdrelinje(p3,1);

        ProcentDiscount procentDiscount = new ProcentDiscount("Procent");

        procentDiscount.setProcent(0.5);
        o1.setDiscount(procentDiscount);
        System.out.println(s1.beregnPris());
        System.out.println(o1.getPris());
        System.out.println(o2.getPris());
        System.out.println(u1.beregnPantPris());


    }
}
