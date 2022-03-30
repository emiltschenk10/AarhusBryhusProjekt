package gui;

import application.controller.Controller;
import application.model.Ordrelinje;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.Salg;
import com.sun.javafx.collections.MapListenerHelper;
import com.sun.javafx.css.StyleCache;
import javafx.beans.value.ChangeListener;
import javafx.collections.MapChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KonferencePane extends GridPane {

    private final ListView<ProduktListview> lvwPriser;
    private final ComboBox<Prisliste> prislisteComboBox;
    private final ListView<Ordrelinje> lvwIndkøbskurv;
    private final TextField txfAntal, txfRabat, txfSamletPris;
    private final CheckBox chkRabat;
    private final ToggleGroup rabat;
    private Salg salg;


    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();

        prislisteComboBox = new ComboBox<>();
        this.add(prislisteComboBox, 0, 0);
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


        chkRabat = new CheckBox("Rabat");
        this.add(chkRabat, 2, 5);


        rabat = new ToggleGroup();
        ArrayList<String> rabatter = new ArrayList<>();
        rabatter.add("Procent Rabat");
        rabatter.add("Aftalt tilbud");
        rabatter.add("Klippekort");
        for (int i = 0; i < rabatter.size(); i++) {
            RadioButton r = new RadioButton(rabatter.get(i));
            this.add(r, 2 + i, 6);
            GridPane.setValignment(r, VPos.TOP);
            GridPane.setHalignment(r, HPos.LEFT);
            r.setToggleGroup(rabat);
        }

        txfRabat = new TextField();
        this.add(txfRabat, 3, 6);
        txfRabat.setMaxWidth(40);

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
        Prisliste prisliste = prislisteComboBox.getSelectionModel().getSelectedItem();

        //skal sættes et andet sted
        this.salg = new Salg();
        this.salg.setPrisliste(prisliste);
        //lvwPriser.getItems().setAll(prisliste.getProduktpriser());
        ArrayList<ProduktListview> produktListviews = new ArrayList<>();
        for (Produkt produkt : prisliste.getProduktpriser().keySet()) {
            produktListviews.add(new ProduktListview(produkt,prisliste.getProduktpriser().get(produkt)));
        }
        lvwPriser.getItems().setAll(produktListviews);
    }

    public void tilføjTilKurvAction(){
        Controller controller = new Controller();
        Produkt produkt = lvwPriser.getSelectionModel().getSelectedItem().getProdukt();
        int antal = Integer.parseInt(txfAntal.getText().trim());
        double pris = lvwPriser.getSelectionModel().getSelectedItem().getPris();
        Controller.createOrdrelinjeSalg(produkt,antal ,pris, salg);
        lvwIndkøbskurv.getItems().setAll(controller.getOrdrelinjer(salg));
        txfAntal.clear();
        lvwPriser.getSelectionModel().clearSelection();
        txfSamletPris.setText(""+salg.beregnPris());
    }
}


/*
    private void opretKonferenceAction() {
        KonferenceWindow dia = new KonferenceWindow("Oprat konference");
        dia.showAndWait();

        // Wait for the modal dialog to close

        lvwPriser.getItems().setAll(Controller.getKonferencer());
        int index = lvwPriser.getItems().size() - 1;
        lvwPriser.getSelectionModel().select(index);
    }

    private void opdaterKonferenceAction() {
        Konference konference = lvwPriser.getSelectionModel().getSelectedItem();
        if (konference != null) {

            KonferenceWindow dia = new KonferenceWindow("Opdater konference", konference);
            dia.showAndWait();

            // Wait for the modal dialog to close

            int selectIndex = lvwPriser.getSelectionModel().getSelectedIndex();
            lvwPriser.getItems().setAll(Controller.getKonferencer());
            lvwPriser.getSelectionModel().select(selectIndex);
        }
    }

    private void opretUdflugtAction() {
        Konference konference = lvwPriser.getSelectionModel().getSelectedItem();
        if (konference != null) {

            UdflugtWindow dia1 = new UdflugtWindow("Opret udflugt");
            dia1.showAndWait();

            // Wait for the modal dialog to close

            int selectIndex = lvwUdflugter.getSelectionModel().getSelectedIndex();
            lvwUdflugter.getItems().setAll(konference.getUdflugter());
            lvwUdflugter.getSelectionModel().select(selectIndex);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------



    private void selectedUdflugtChanged() {
        this.updateUdflugtControls();
    }


    public void updateUdflugtControls() {

        Konference konference = lvwPriser.getSelectionModel().getSelectedItem();
        Udflugt udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
        if (udflugt != null) {
            StringBuilder sb2 = new StringBuilder();
            for (Ledsager l : udflugt.getLedsagere()) {
                sb2.append(l.getNavn() + "\n");
            }
            txaLedsagere.setText(sb2.toString());

        } else {
            txaLedsagere.clear();
        }
    }

}

 */
