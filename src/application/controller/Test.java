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
        Prisliste pr1 = controller.createPrisliste("Fredagsbar");
        Prisliste pr2 = controller.createPrisliste("Butik");
        Controller.addProduktTilPrisliste(p1, 38.0, pr1);
        Controller.addProduktTilPrisliste(p2, 70.0, pr1);
        Controller.addProduktTilPrisliste(p3, 575, pr1);
        Controller.addProduktTilPrisliste(p1, 38.0, pr2);
        Controller.addProduktTilPrisliste(p2, 36.0, pr2);
//        Controller.addProduktTilPrisliste(p3, 575, pr2);

        Salg s1 = controller.createSalgMedParm(LocalDateTime.now(),true,pr1);
        Salg s2 = controller.createSalgMedParm(LocalDateTime.now(),true,pr1);
        Salg s3 = controller.createSalgMedParm(LocalDateTime.now(),true,pr1);
        Ordrelinje o1 = Controller.createOrdrelinjeSalg(p1,2,70,s1);
        Ordrelinje o2 = Controller.createOrdrelinjeSalg(p1,2,70,s2);
        Ordrelinje o3 = Controller.createOrdrelinjeSalg(p1,2,70,s3);
        Controller.addOrdrelinjeTilSalg(o1,s1);
        Controller.addOrdrelinjeTilSalg(o2,s1);
        Controller.addOrdrelinjeTilSalg(o3,s1);

        System.out.println(s1.beregnPris());

        System.out.println(controller.salgForDato(LocalDate.now()));

        System.out.println(controller.salgForProduktogProduktgruppe(produktgruppe,p1, LocalDate.now()));
    }
}
