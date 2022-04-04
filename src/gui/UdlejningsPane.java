package gui;

import application.controller.Controller;
import application.model.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class UdlejningsPane extends GridPane {

    private final ListView<ProduktListview> lvwPriser;
    private final ListView<Ordrelinje> lvwIndkøbskurv;
    private final TextField txfAntal, txfRabat, txfRestPris, txfPantPris;
    private final CheckBox chkRabat;
    private final ToggleGroup rabat;
    private final Button btnKøb, btnCancel, btnTilføjTilKurv, btnOpretUdlejning;
    private RadioButton r1,r2;
    private Controller controller = new Controller();
    private Udlejning udlejning;
    //private Udlejning udlejning;


    public UdlejningsPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();

        /*
        this.udlejning = new Udlejning();
        Prisliste prisliste = controller.getAllePrislister().get(2);

        udlejning.setPrisliste(prisliste);
*/
        Prisliste prisliste = controller.getAllePrislister().get(2);
        Label lblPriser = new Label("Udlejningspriser:");
        this.add(lblPriser, 0, 1);

        lvwPriser = new ListView<>();
        this.add(lvwPriser, 0, 2, 1, 5);
        lvwPriser.setPrefWidth(200);
        lvwPriser.setPrefHeight(200);
        ArrayList<ProduktListview> produktListviews = new ArrayList<>();
        for (Produkt produkt : prisliste.getProduktpriser().keySet()) {
            produktListviews.add(new ProduktListview(produkt,prisliste.getProduktpriser().get(produkt)));
        }
        lvwPriser.getItems().setAll(produktListviews);
        lvwPriser.setDisable(true);
        //lvwPriser.getItems().setAll(Controller.getKonferencer());



        Label lblAntal = new Label("Antal:");
        this.add(lblAntal, 2, 3);
        GridPane.setValignment(lblAntal, VPos.BOTTOM);


        txfAntal = new TextField();
        this.add(txfAntal, 2, 4);
        GridPane.setValignment(txfAntal, VPos.TOP);
        txfAntal.setMaxWidth(40);
        txfAntal.setDisable(true);


        chkRabat = new CheckBox("Rabat");
        this.add(chkRabat, 2, 5);
        chkRabat.setDisable(true);

        chkRabat.setOnAction(event -> chkboxrabatAction());

        rabat = new ToggleGroup();
        ArrayList<String> rabatter = new ArrayList<>();
        rabatter.add("Procent Rabat");
        rabatter.add("Aftalt tilbud");


        r1 = new RadioButton("Procent rabat");
        r2 = new RadioButton("Aftalt tilbud");

        r1.setAlignment(Pos.TOP_CENTER);
        r2.setAlignment(Pos.TOP_CENTER);

        r1.setToggleGroup(rabat);
        r2.setToggleGroup(rabat);


        r1.setDisable(true);
        r2.setDisable(true);

        HBox hBox = new HBox(r1,r2);
        this.add(hBox,2,6,2,1);


        txfRabat = new TextField();
        this.add(txfRabat, 3, 6);
        txfRabat.setMaxWidth(40);
        txfRabat.setDisable(true);

        Label lblRabat = new Label("Angiv rabat:");
        this.add(lblRabat,2 , 6);




        Label lblKurv = new Label("Indkøbskurv:");
        this.add(lblKurv, 5, 1);

        lvwIndkøbskurv = new ListView<>();
        this.add(lvwIndkøbskurv, 5, 2, 1, 5);
        lvwIndkøbskurv.setPrefWidth(200);
        lvwIndkøbskurv.setPrefHeight(200);
        lvwIndkøbskurv.setDisable(true);


        Label lblRestPris = new Label("Rest pris:");
        this.add(lblRestPris, 4, 8);
        txfRestPris = new TextField();
        this.add(txfRestPris, 5, 8);
        txfRestPris.setEditable(false);
        txfRestPris.setDisable(true);

        Label lblPantPris = new Label("Pant pris:");
        this.add(lblPantPris, 4, 7);

        txfPantPris = new TextField();
        this.add(txfPantPris, 5, 7);
        txfPantPris.setDisable(true);
        txfPantPris.setEditable(false);
/*
        ChangeListener<Prisliste> listener1 = (ov, gammelPrisListe, nyPrisListe) -> this.selectedPrislisteChanged();
        prislisteComboBox.getSelectionModel().selectedItemProperty().addListener(listener1);
*/


        btnTilføjTilKurv = new Button("Tilføj til kurv");
        this.add(btnTilføjTilKurv, 3, 4);
        GridPane.setValignment(btnTilføjTilKurv, VPos.TOP);
        btnTilføjTilKurv.setOnAction(event -> this.tilføjTilKurvAction());
        btnTilføjTilKurv.setDisable(true);

        if (lvwPriser.getItems().size() > 0) {
            lvwPriser.getSelectionModel().select(0);
        }

        btnKøb = new Button("Køb");
        btnKøb.setOnAction(event ->købBtnAction());
        btnKøb.setDisable(true);

        btnCancel = new Button("Cancel");
        btnCancel.setDisable(true);
        btnCancel.setOnAction(event -> this.cancelAction());

        btnOpretUdlejning = new Button("Opret Udlejning");
        this.add(btnOpretUdlejning, 0, 1);
        GridPane.setHalignment(btnOpretUdlejning, HPos.RIGHT);
        btnOpretUdlejning.setOnAction(event -> this.opretUdlejningsAction());


        HBox hBox1 = new HBox(btnCancel, btnKøb);
        this.add(hBox1,5,9);
        hBox1.setSpacing(10);
    }


    // -----------------------------------------------------------------------------------------------------------------

    public void opretUdlejningsAction(){
        this.udlejning = controller.createUdlejningUdenParm();
        Prisliste prisliste = controller.getAllePrislister().get(2);
        udlejning.setPrisliste(prisliste);
        lvwPriser.setDisable(false);
        lvwIndkøbskurv.setDisable(false);
        txfRestPris.setDisable(false);
        txfPantPris.setDisable(false);
        txfAntal.setDisable(false);
        chkRabat.setDisable(false);
        btnKøb.setDisable(false);
        btnCancel.setDisable(false);
        btnTilføjTilKurv.setDisable(false);
        btnOpretUdlejning.setDisable(true);
    }


    public void chkboxrabatAction(){
        if(chkRabat.isSelected()){
            r1.setDisable(false);
            r2.setDisable(false);
            txfRabat.setDisable(false);
        }else{
            r1.setDisable(true);
            r2.setDisable(true);

        }
    }

    public void tilføjTilKurvAction(){
        //TODO

        Produkt produkt = lvwPriser.getSelectionModel().getSelectedItem().getProdukt();
        int antal = Integer.parseInt(txfAntal.getText().trim());
        double pris = lvwPriser.getSelectionModel().getSelectedItem().getPris();
        Ordrelinje ordrelinje = Controller.createOrdrelinjeUdlejning(produkt, antal, udlejning);

        if(chkRabat.isSelected() && rabat.getSelectedToggle() != null){
            if(rabat.getSelectedToggle()==r1 && !txfRabat.getText().equals("")){
                ProcentDiscount discount = new ProcentDiscount("");
                discount.setProcent(Double.parseDouble(txfRabat.getText()));
                ordrelinje.setDiscount(discount);
            }else if (rabat.getSelectedToggle()==r2 && !txfRabat.getText().equals("")){
                AftaltDiscount discount = new AftaltDiscount("");
                discount.setPris(Double.parseDouble(txfRabat.getText()));
                ordrelinje.setDiscount(discount);
            }

        }
        txfPantPris.setText("" + udlejning.beregnPris());
        txfRestPris.setText(""+udlejning.beregnRestPris());
        lvwIndkøbskurv.getItems().setAll(Controller.getOrdrelinjePåUdlejning(udlejning));
        txfAntal.clear();
        txfRabat.clear();
        chkRabat.setSelected(false);
        chkboxrabatAction();
        rabat.selectToggle(null);
        txfRabat.setDisable(true);
        lvwPriser.getSelectionModel().clearSelection();
       // txfSamletPris.setText(""+salg.beregnPris());
    }

    public void købBtnAction(){
        KundeWindowUdlejning dia = new KundeWindowUdlejning("Betalingsvindue", udlejning);
        dia.showAndWait();
        this.udlejning = null;
        lvwIndkøbskurv.getItems().clear();
        txfPantPris.clear();
        txfRabat.clear();
        txfAntal.clear();
        txfRestPris.clear();
        lvwPriser.setDisable(true);
        lvwIndkøbskurv.setDisable(true);
        txfRestPris.setDisable(true);
        txfPantPris.setDisable(true);
        txfAntal.setDisable(true);
        btnKøb.setDisable(true);
        btnCancel.setDisable(true);
        btnTilføjTilKurv.setDisable(true);
        btnOpretUdlejning.setDisable(false);
    }

    public void cancelAction(){
        lvwPriser.setDisable(true);
        lvwIndkøbskurv.setDisable(true);
        txfRestPris.setDisable(true);
        txfPantPris.setDisable(true);
        txfAntal.setDisable(true);
        btnKøb.setDisable(true);
        btnCancel.setDisable(true);
        btnTilføjTilKurv.setDisable(true);
        btnOpretUdlejning.setDisable(false);
        txfRabat.clear();
        txfPantPris.clear();
        txfRestPris.clear();
        txfAntal.clear();
        txfRabat.setDisable(true);
        lvwIndkøbskurv.getItems().clear();
        chkRabat.setDisable(true);
        controller.removeUdlejning(this.udlejning);
    }


}

