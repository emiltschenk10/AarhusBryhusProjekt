package gui;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Produkt;
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
    //private final ListView<Udflugt> lvwUdflugter;

    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();

        prislisteComboBox = new ComboBox<>();
        this.add(prislisteComboBox, 0, 0);
        prislisteComboBox.getItems().setAll(controller.getAllePrislister());


        Label lblPriser = new Label("Priser");
        this.add(lblPriser, 0, 1);

        lvwPriser = new ListView<>();
        this.add(lvwPriser, 0, 2, 1, 5);
        lvwPriser.setPrefWidth(200);
        lvwPriser.setPrefHeight(200);
        //lvwPriser.getItems().setAll(Controller.getKonferencer());


        Label lblUdflugter = new Label("Udflugter:");
        this.add(lblUdflugter, 0, 8);
        GridPane.setValignment(lblUdflugter, VPos.BASELINE);
        lblUdflugter.setPadding(new Insets(4, 0, 4, 0));

        //lvwUdflugter = new ListView<>();
        // this.add(lvwUdflugter, 0, 9, 1, 1);
        // lvwUdflugter.setPrefWidth(200);
        // lvwUdflugter.setPrefHeight(100);


        ChangeListener<Prisliste> listener1 = (ov, gammelPrisListe, nyPrisListe) -> this.selectedPrislisteChanged();
        prislisteComboBox.getSelectionModel().selectedItemProperty().addListener(listener1);


        // ChangeListener<Udflugt> listener2 = (ov, gammelUdflugt, nyUdflugt) -> this.selectedUdflugtChanged();
        // lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener2);


        Button btnOpretKonference = new Button("Opret konference");
        this.add(btnOpretKonference, 0, 0);
        GridPane.setHalignment(btnOpretKonference, HPos.RIGHT);
        //btnOpretKonference.setOnAction(event -> this.opretKonferenceAction());

        Button btnOpretUdflugt = new Button("Opret udflugt");
        this.add(btnOpretUdflugt, 0, 8);
        GridPane.setHalignment(btnOpretUdflugt, HPos.RIGHT);
        //btnOpretUdflugt.setOnAction(event -> this.opretUdflugtAction());

        Button btnOpdaterKonference = new Button("Opdater konference");
        this.add(btnOpdaterKonference, 2, 0);
        GridPane.setHalignment(btnOpdaterKonference, HPos.LEFT);
        //btnOpdaterKonference.setOnAction(event -> this.opdaterKonferenceAction());

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
        //lvwPriser.getItems().setAll(prisliste.getProduktpriser());
        ArrayList<ProduktListview> produktListviews = new ArrayList<>();
        for (Produkt produkt : prisliste.getProduktpriser().keySet()) {
            produktListviews.add(new ProduktListview(produkt,prisliste.getProduktpriser().get(produkt)));
        }
        lvwPriser.getItems().setAll(produktListviews);
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
