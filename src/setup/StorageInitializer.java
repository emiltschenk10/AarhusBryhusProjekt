package setup;

import application.controller.Controller;
import application.model.*;

import java.time.LocalDate;

public class StorageInitializer {
    public static void main(String[] args) {
        initStorage();
    }


    private static void initStorage(){
        Controller controller = Controller.getInstance();
        Produktgruppe produktgruppe = controller.createProduktGruppe("Fadøl", "Bajer");
        Produktgruppe produktgruppe1 = controller.createProduktGruppe("Flaske", "Pant");
        Produktgruppe produktgruppe2 = controller.createProduktGruppe("Fustage", "asdasd");
        Produktgruppe produktgruppe3 = controller.createProduktGruppe("Gavekort", "Det gaver");
        Produkt p1 = controller.createProdukt("Klosterbryg", "Bajer", 1, 0, produktgruppe);
        Produkt p2 = controller.createProdukt("Klosterbryg", "Pant", 2, 1, produktgruppe1);
        Produkt p3 = controller.createProdukt("Klosterbryg", "asdasd", 0, 200, produktgruppe2);
        Produkt p4 = controller.createProdukt("Klippekort", "Nice", 0, 0, produktgruppe3);
        Prisliste pr1 = controller.createPrisliste("Fredagsbar");
        Prisliste pr2 = controller.createPrisliste("Butik");
        Prisliste pr3 = controller.createPrisliste("Udlejning");
        controller.addProduktTilPrisliste(p1, 38.0, pr1);
        controller.addProduktTilPrisliste(p2, 70.0, pr1);
        controller.addProduktTilPrisliste(p3, 575, pr1);
        controller.addProduktTilPrisliste(p1, 38.0, pr2);
        controller.addProduktTilPrisliste(p2, 36.0, pr2);
        controller.addProduktTilPrisliste(p3, 575, pr3);
        controller.addProduktTilPrisliste(p4, 650, pr1);

        controller.createArrangement("Lols", "Nice", 100, LocalDate.now());

        controller.createArrangement("Rundvisning", "Nice", 100, LocalDate.now().plusDays(5));

        controller.createArrangement("Pizza og film", "Gratis", 0, LocalDate.now().plusDays(5));
//        Controller.addProduktTilPrisliste(p3, 575, pr2);


        Salg s1 = controller.createSalgMedParm(LocalDate.now(), false, pr1);
        Salg s2 = controller.createSalgMedParm(LocalDate.now(), false, pr1);
        Salg s3 = controller.createSalgMedParm(LocalDate.of(2022, 1, 10), false, pr1);
        Salg s4 = controller.createSalgMedParm(LocalDate.now(), false, pr1);


        Udlejning u1 = controller.createUdlejning(LocalDate.now().plusDays(5), LocalDate.now(), null, pr1);

        Ordrelinje o1 = controller.createOrdrelinjeSalg(p2, 2, s1);
        Ordrelinje o2 = controller.createOrdrelinjeSalg(p1, 2, s2);
        Ordrelinje o3 = controller.createOrdrelinjeSalg(p1, 2, s3);
        Ordrelinje o4 = controller.createOrdrelinjeUdlejning(p1, 4, u1);
        Ordrelinje o5 = controller.createOrdrelinjeSalg(p4, 5, s4);


        controller.createKunde("Kvickly", 121312312, "dyrt.dk");
        controller.createKunde("Fakta", 22223333, "BudgetKvickly.dk");

        Betalingsform b1 = controller.createBetalingsform("Mobilepay", "Online");
        Betalingsform b2 = controller.createBetalingsform("Mastercard", "Creditkort");
        Betalingsform b3 = controller.createBetalingsform("Bitcoin", "Crypto");
        Betalingsform b4 = controller.createBetalingsform("Klippekort", "Gavekort");

        s1.setBetalingsform(b4);
        s2.setBetalingsform(b1);
        s4.setBetalingsform(b2);
        controller.saveStorage();
    }
}
