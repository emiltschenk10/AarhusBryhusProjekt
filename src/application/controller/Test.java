package application.controller;

import application.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {

        Controller controller = new Controller();
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl","Bajer");
        Produktgruppe produktgruppe1 = controller.createProduktGruppe("Flaske","Pant");
        Produktgruppe produktgruppe2 = controller.createProduktGruppe("Fustage","asdasd");
        Produkt p1 = Controller.createProdukt("Klosterbryg","Bajer",1,0, produktgruppe);
        Produkt p2 = Controller.createProdukt("Klosterbryg","Pant",2,1, produktgruppe1);
        Produkt p3 = Controller.createProdukt("Klosterbryg","asdasd",0, 200, produktgruppe2);
        Arrangement a1 = controller.createArrangement("Fredagsbar","En fredagsbar", 0);
        Prisliste pr1 = controller.createPrisliste("Fredagsbar");
        Prisliste pr2 = controller.createPrisliste("Butik");
        Controller.addArragementTilPrisliste(a1,pr1);
        Controller.addProduktTilPrisliste(p1, 38.0, pr1);
        Controller.addProduktTilPrisliste(p2, 70.0, pr1);
        Controller.addProduktTilPrisliste(p3, 575, pr1);
        Controller.addProduktTilPrisliste(p1, 38.0, pr2);
        Controller.addProduktTilPrisliste(p2, 36.0, pr2);
//        Controller.addProduktTilPrisliste(p3, 575, pr2);

        Salg s1 = controller.createSalgMedParm(LocalDate.now(),true,pr1);
        Salg s2 = controller.createSalgMedParm(LocalDate.now(),true,pr1);
        Salg s3 = controller.createSalgMedParm(LocalDate.now(),true,pr1);
        Ordrelinje o1 = Controller.createOrdrelinjeSalg(p1,2, s1);
        Ordrelinje o2 = Controller.createOrdrelinjeSalg(p1,2, s2);
        Ordrelinje o3 = Controller.createOrdrelinjeSalg(p1,2, s3);

        System.out.println(s1.beregnPris());

        System.out.println(controller.salgForDato(LocalDate.now()));

        System.out.println(controller.salgForProduktogProduktgruppe(produktgruppe,p1, LocalDate.now(),a1));

        System.out.println(controller.salgForProduktogProduktgruppe(produktgruppe,p1, LocalDate.now(),null));

        Kunde k1 = new Kunde("Hans",124124,"Hej");


       Udlejning u1 = controller.createUdlejning(LocalDate.of(2021,2,2),LocalDate.of(2021,1,2),k1,pr1);
        Ordrelinje o4 = Controller.createOrdrelinjeUdlejning(p1,3, u1);
        Ordrelinje o5 = Controller.createOrdrelinjeUdlejning(p1,3, u1);
        Ordrelinje o6 = Controller.createOrdrelinjeUdlejning(p1,3, u1);

        Udlejning u2 = controller.createUdlejning(LocalDate.of(2021,2,2),LocalDate.of(2021,1,2),k1,pr1);
        Ordrelinje o7 = Controller.createOrdrelinjeUdlejning(p1,3, u2);
        Ordrelinje o8 = Controller.createOrdrelinjeUdlejning(p1,3, u2);
        Ordrelinje o9 = Controller.createOrdrelinjeUdlejning(p1,3, u2);


        Udlejning u3 = controller.createUdlejning(LocalDate.of(2021,2,2),LocalDate.of(2021,1,2),k1,pr1);
        Ordrelinje o10 = Controller.createOrdrelinjeUdlejning(p1,3, u3);
        Ordrelinje o11 = Controller.createOrdrelinjeUdlejning(p1,3, u3);
        Ordrelinje o12 = Controller.createOrdrelinjeUdlejning(p1,10,u3);



        System.out.println(controller.salgForDagen());
    }
}
