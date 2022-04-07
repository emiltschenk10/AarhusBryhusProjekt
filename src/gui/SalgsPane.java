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

public class SalgsPane extends GridPane {

    private final ListView<ProduktListview> lvwPriser;
    private final ComboBox<Prisliste> prislisteComboBox;
    private final ListView<Ordrelinje> lvwIndkøbskurv;
    private final TextField txfAntal, txfRabat, txfSamletPris;
    private final CheckBox chkRabat;
    private final ToggleGroup rabat;
    private Salg salg;
    private RadioButton r1,r2,r3;
    private Controller controller = new Controller();
    private Button btnOpretSalg,btnTilføjTilKurv,btnKøb,btnAfbrydSalg;


    public SalgsPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        prislisteComboBox = new ComboBox<>();
        this.add(prislisteComboBox, 0, 0);
//        prislisteComboBox.setOnMouseClicked(event -> this.visPrisliste());
        prislisteComboBox.getItems().setAll(controller.getAllePrislister());

        Label lblPriser = new Label("Priser:");
        this.add(lblPriser, 0, 1);

        lvwPriser = new ListView<>();
        this.add(lvwPriser, 0, 2, 1, 5);
        lvwPriser.setPrefWidth(200);
        lvwPriser.setPrefHeight(200);
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
        this.add(hBox,2,6, 2, 1);



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


        Label lblSamletPris = new Label("Samlet pris:");
        this.add(lblSamletPris, 4, 7);
        txfSamletPris = new TextField();
        this.add(txfSamletPris, 5, 7);
        txfSamletPris.setEditable(false);

        ChangeListener<Prisliste> listener1 = (ov, gammelPrisListe, nyPrisListe) -> this.selectedPrislisteChanged();
        prislisteComboBox.getSelectionModel().selectedItemProperty().addListener(listener1);





        btnKøb = new Button("Køb");
        btnKøb.setOnAction(event ->købBtnAction());
        btnKøb.setDisable(true);

        btnAfbrydSalg = new Button("Afbryd Salg");
        btnAfbrydSalg.setDisable(true);
        btnAfbrydSalg.setOnAction(event -> afbrydSalgAction());


        HBox hBox1 = new HBox(btnAfbrydSalg, btnKøb);
        this.add(hBox1,5,8);
        hBox1.setSpacing(10);

        btnTilføjTilKurv = new Button("Tilføj til kurv");
        this.add(btnTilføjTilKurv, 3, 6);
        GridPane.setValignment(btnTilføjTilKurv, VPos.BOTTOM);
        btnTilføjTilKurv.setOnAction(event -> this.tilføjTilKurvAction());
        btnTilføjTilKurv.setDisable(true);

        if (lvwPriser.getItems().size() > 0) {
            lvwPriser.getSelectionModel().select(0);
        }

        btnOpretSalg = new Button("Opret Salg");
        this.add(btnOpretSalg, 0, 1);
        GridPane.setHalignment(btnOpretSalg, HPos.RIGHT);
        btnOpretSalg.setOnAction(event -> this.opretSalgAction());
        btnOpretSalg.setDisable(true);

        // if (lvwUdflugter.getItems().size() > 0) {
        //   lvwUdflugter.getSelectionModel().select(0);
    }


    // -----------------------------------------------------------------------------------------------------------------


    public void selectedPrislisteChanged() {
        this.updateControls();
    }

    public void afbrydSalgAction(){
        controller.removeSalg(this.salg);
        txfAntal.setDisable(true);
        chkRabat.setDisable(true);
        txfRabat.setDisable(true);
        btnOpretSalg.setDisable(false);
        btnTilføjTilKurv.setDisable(true);
        btnAfbrydSalg.setDisable(true);
        prislisteComboBox.setDisable(false);
        lvwIndkøbskurv.getItems().clear();
        txfSamletPris.clear();
    }

    public void opretSalgAction(){
        txfAntal.setDisable(false);
        chkRabat.setDisable(false);
        txfRabat.setDisable(false);
        btnOpretSalg.setDisable(true);
        btnTilføjTilKurv.setDisable(false);
        btnAfbrydSalg.setDisable(false);
        Prisliste prisliste = prislisteComboBox.getSelectionModel().getSelectedItem();
        this.salg = controller.createSalgUdenParm();
        this.salg.setPrisliste(prisliste);
        prislisteComboBox.setDisable(true);
    }


    public void updateControls() {
        Prisliste prisliste = prislisteComboBox.getSelectionModel().getSelectedItem();
        //TODO der kommer en nulpointer når man skal skifte prisliste
        //lvwPriser.getItems().setAll(prisliste.getProduktpriser());
        ArrayList<ProduktListview> produktListviews = new ArrayList<>();
        for (Produkt produkt : prisliste.getProduktpriser().keySet()) {
            produktListviews.add(new ProduktListview(produkt,prisliste.getProduktpriser().get(produkt)));
        }
        lvwPriser.getItems().setAll(produktListviews);
        btnOpretSalg.setDisable(false);
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
        Ordrelinje ordrelinje = Controller.createOrdrelinjeSalg(produkt,antal, salg);
        if(chkRabat.isSelected() && rabat.getSelectedToggle() != null){
            if(rabat.getSelectedToggle()==r1 && !txfRabat.getText().equals("")){
                ProcentDiscount discount = controller.createProcentDiscount(Double.parseDouble(txfRabat.getText()));
                Controller.setDiscount(ordrelinje,discount);
            }else if(rabat.getSelectedToggle()==r2 && !txfRabat.getText().equals("")){
                AftaltDiscount discount = controller.createAftaltDiscount(Double.parseDouble(txfRabat.getText()));
                Controller.setDiscount(ordrelinje,discount);
            }else{
                Discount discount = controller.createKlipDiscount();
                Controller.setDiscount(ordrelinje,discount);
            }
        }
        lvwIndkøbskurv.getItems().setAll(controller.getOrdrelinjer(salg));
        txfAntal.clear();
        lvwPriser.getSelectionModel().clearSelection();
        txfSamletPris.setText(""+salg.beregnPris());
        btnKøb.setDisable(false);
    }

    public void købBtnAction(){
        KundeWindow dia = new KundeWindow("Betalingsvindue",salg);
        dia.showAndWait();
        lvwIndkøbskurv.getItems().clear();
        txfSamletPris.clear();
        txfAntal.clear();
        txfRabat.clear();
        btnOpretSalg.setDisable(false);
        txfAntal.setDisable(true);
        chkRabat.setDisable(true);
        txfRabat.setDisable(true);
        btnOpretSalg.setDisable(false);
        btnTilføjTilKurv.setDisable(true);
        btnAfbrydSalg.setDisable(true);
        prislisteComboBox.setDisable(false);
        btnKøb.setDisable(true);
    }

}
