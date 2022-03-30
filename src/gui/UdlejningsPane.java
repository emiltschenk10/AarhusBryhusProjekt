package gui;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Date;

public class UdlejningsPane extends GridPane {

    private final ListView<ProduktListview> lvwPriser;
    private final ListView<Ordrelinje> lvwIndkøbskurv;
    private final TextField txfAntal, txfRabat, txfSamletPris;
    private final CheckBox chkRabat;
    private final ToggleGroup rabat;
    private Udlejning udlejning;
    private RadioButton r1,r2,r3;
    private Controller controller;
    private DatePicker datePickerAfleveringsDato, datePickerUdleveringsDato;


    public UdlejningsPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();



        Label lblPriser = new Label("Udlejningspriser:");
        this.add(lblPriser, 0, 1);

        lvwPriser = new ListView<>();
        this.add(lvwPriser, 0, 2, 1, 5);
        lvwPriser.setPrefWidth(200);
        lvwPriser.setPrefHeight(200);
        Prisliste prisliste = controller.getAllePrislister().get(2);
        ArrayList<ProduktListview> produktListviews = new ArrayList<>();
        for (Produkt produkt : prisliste.getProduktpriser().keySet()) {
            produktListviews.add(new ProduktListview(produkt,prisliste.getProduktpriser().get(produkt)));
        }
        lvwPriser.getItems().setAll(produktListviews);
        //lvwPriser.getItems().setAll(Controller.getKonferencer());



        Label lblAntal = new Label("Antal:");
        this.add(lblAntal, 2, 3);
        GridPane.setValignment(lblAntal, VPos.BOTTOM);


        txfAntal = new TextField();
        this.add(txfAntal, 2, 4);
        GridPane.setValignment(txfAntal, VPos.TOP);
        txfAntal.setMaxWidth(40);


        chkRabat = new CheckBox("Rabat");
        this.add(chkRabat, 2, 5);

        chkRabat.setOnAction(event -> chkboxrabatAction());

        rabat = new ToggleGroup();
        ArrayList<String> rabatter = new ArrayList<>();
        rabatter.add("Procent Rabat");
        rabatter.add("Aftalt tilbud");
        rabatter.add("Klippekort");

        r1 = new RadioButton("Procent rabat");
        r2 = new RadioButton("Aftalt tilbud");
        r3 = new RadioButton("Klippekort");
        r1.setAlignment(Pos.TOP_CENTER);
        r2.setAlignment(Pos.TOP_CENTER);
        r3.setAlignment(Pos.TOP_CENTER);
        r1.setToggleGroup(rabat);
        r2.setToggleGroup(rabat);
        r3.setToggleGroup(rabat);

        r1.setDisable(true);
        r2.setDisable(true);
        r3.setDisable(true);
        HBox hBox = new HBox(r1,r2,r3);
        this.add(hBox,2,6,2,1);


        txfRabat = new TextField();
        this.add(txfRabat, 3, 6);
        txfRabat.setMaxWidth(40);

        Label lblRabat = new Label("Angiv rabat:");
        this.add(lblRabat,2 , 6);

        Label lblUdleveringsDato = new Label("Udlevering:");
        this.add(lblUdleveringsDato, 2, 1);

        datePickerUdleveringsDato = new DatePicker();
        this.add(datePickerUdleveringsDato, 3, 1);



        Label lblAflevering = new Label("Aflevering:");
        this.add(lblAflevering, 2, 2);

        datePickerAfleveringsDato = new DatePicker();
        this.add(datePickerAfleveringsDato, 3, 2);


        Label lblKurv = new Label("Indkøbskurv:");
        this.add(lblKurv, 5, 1);

        lvwIndkøbskurv = new ListView<>();
        this.add(lvwIndkøbskurv, 5, 2, 1, 5);
        lvwIndkøbskurv.setPrefWidth(200);
        lvwIndkøbskurv.setPrefHeight(200);


        Label lblSamletPris = new Label("Samlet pris:");
        this.add(lblSamletPris, 4, 7);
        txfSamletPris = new TextField();
        this.add(txfSamletPris, 5, 7);
        txfSamletPris.setEditable(false);
/*
        ChangeListener<Prisliste> listener1 = (ov, gammelPrisListe, nyPrisListe) -> this.selectedPrislisteChanged();
        prislisteComboBox.getSelectionModel().selectedItemProperty().addListener(listener1);
*/

        // ChangeListener<Udflugt> listener2 = (ov, gammelUdflugt, nyUdflugt) -> this.selectedUdflugtChanged();
        // lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener2);

/*
        Button btnOpretKonference = new Button("Opret konference");
        this.add(btnOpretKonference, 0, 0);
        GridPane.setHalignment(btnOpretKonference, HPos.RIGHT);
        //btnOpretKonference.setOnAction(event -> this.opretKonferenceAction());
*/
        /*
        Button btnOpretUdflugt = new Button("Opret udflugt");
        this.add(btnOpretUdflugt, 0, 8);
        GridPane.setHalignment(btnOpretUdflugt, HPos.RIGHT);
        //btnOpretUdflugt.setOnAction(event -> this.opretUdflugtAction());
*/
        Button btnTilføjTilKurv = new Button("Tilføj til kurv");
        this.add(btnTilføjTilKurv, 3, 7);
        GridPane.setValignment(btnTilføjTilKurv, VPos.TOP);
        btnTilføjTilKurv.setOnAction(event -> this.tilføjTilKurvAction());

        if (lvwPriser.getItems().size() > 0) {
            lvwPriser.getSelectionModel().select(0);
        }

        // if (lvwUdflugter.getItems().size() > 0) {
        //   lvwUdflugter.getSelectionModel().select(0);
    }


    // -----------------------------------------------------------------------------------------------------------------


    public void selectedPrislisteChanged() {
        this.updateControls();
    }


    public void updateControls() {
        Prisliste prisliste = controller.getAllePrislister().get(2);

        //skal sættes et andet sted
        //this.salg.setPrisliste(prisliste);
        //lvwPriser.getItems().setAll(prisliste.getProduktpriser());
        ArrayList<ProduktListview> produktListviews = new ArrayList<>();
        for (Produkt produkt : prisliste.getProduktpriser().keySet()) {
            produktListviews.add(new ProduktListview(produkt,prisliste.getProduktpriser().get(produkt)));
        }
        lvwPriser.getItems().setAll(produktListviews);
    }

    public void chkboxrabatAction(){
        if(chkRabat.isSelected()){
            r1.setDisable(false);
            r2.setDisable(false);
            r3.setDisable(false);
        }else{
            r1.setDisable(true);
            r2.setDisable(true);
            r3.setDisable(true);
        }
    }

    public void tilføjTilKurvAction(){
        //TODO
        Controller controller = new Controller();
        Produkt produkt = lvwPriser.getSelectionModel().getSelectedItem().getProdukt();
        int antal = Integer.parseInt(txfAntal.getText().trim());
        double pris = lvwPriser.getSelectionModel().getSelectedItem().getPris();
       // Ordrelinje ordrelinje = Controller.createOrdrelinjeSalg(produkt,antal ,pris, salg);
        if(chkRabat.isSelected() && rabat.getSelectedToggle() != null){
            if(rabat.getSelectedToggle()==r1 && !txfRabat.getText().equals("")){
                ProcentDiscount discount = new ProcentDiscount("");
                discount.setProcent(Double.parseDouble(txfRabat.getText()));
              //  ordrelinje.setDiscount(discount);
            }else if(rabat.getSelectedToggle()==r2 && !txfRabat.getText().equals("")){
                AftaltDiscount discount = new AftaltDiscount("");
                discount.setPris(Double.parseDouble(txfRabat.getText()));
               // ordrelinje.setDiscount(discount);
            }else{
                Discount discount = new KlipDiscount("");
              //  ordrelinje.setDiscount(discount);
            }
        }
       // lvwIndkøbskurv.getItems().setAll(controller.getOrdrelinjer(salg));
        txfAntal.clear();
        lvwPriser.getSelectionModel().clearSelection();
       // txfSamletPris.setText(""+salg.beregnPris());
    }
}
