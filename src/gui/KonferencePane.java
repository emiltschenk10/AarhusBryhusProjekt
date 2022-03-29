package gui;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Produkt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Map;

public class KonferencePane extends GridPane {
    private final TextField txfNavn, txfAdresse, txfstartDato, txfSlutDato, txfPris;
    private final TextArea txaBeskrivelse, txaLedsagere, txaHoteller, txaDeltager;
    private final ListView<Map<Produkt, Double>> lvwPriser;
    private final ComboBox<Prisliste> prislisteComboBox;
    //private final ListView<Udflugt> lvwUdflugter;

    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        //Controller controller = new Controller();

        prislisteComboBox = new ComboBox<>();
        this.add(prislisteComboBox, 0, 0);


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

       // ChangeListener<Konference> listener = (ov, gammelKonference, nyKonference) -> this.selectedKonferenceChanged();
       // lvwPriser.getSelectionModel().selectedItemProperty().addListener(listener);

       // ChangeListener<Udflugt> listener2 = (ov, gammelUdflugt, nyUdflugt) -> this.selectedUdflugtChanged();
       // lvwUdflugter.getSelectionModel().selectedItemProperty().addListener(listener2);

        Label lblNavn = new Label("Navn:");
        this.add(lblNavn, 1, 1);
        GridPane.setHalignment(lblNavn, HPos.RIGHT);

        txfNavn = new TextField();
        this.add(txfNavn, 2, 1);
        txfNavn.setEditable(false);

        Label lblAdresse = new Label("Adresse:");
        this.add(lblAdresse, 1, 2);
        GridPane.setHalignment(lblAdresse, HPos.RIGHT);

        txfAdresse = new TextField();
        this.add(txfAdresse, 2, 2);
        txfAdresse.setEditable(false);

        Label lblBeskrivelse = new Label("Beskrivelse:");
        this.add(lblBeskrivelse, 1, 3);
        GridPane.setValignment(lblBeskrivelse, VPos.BASELINE);
        lblBeskrivelse.setPadding(new Insets(4, 0, 4, 0));
        GridPane.setHalignment(lblBeskrivelse, HPos.RIGHT);

        txaBeskrivelse = new TextArea();
        this.add(txaBeskrivelse, 2, 3);
        txaBeskrivelse.setPrefWidth(200);
        txaBeskrivelse.setPrefHeight(100);
        txaBeskrivelse.setEditable(false);

        Label lblStartDato = new Label("Start dato:");
        this.add(lblStartDato, 1, 4);
        GridPane.setHalignment(lblStartDato, HPos.RIGHT);

        txfstartDato = new TextField();
        this.add(txfstartDato, 2, 4);
        txfstartDato.setEditable(false);

        Label lblSlutDato = new Label("Slut dato:");
        this.add(lblSlutDato, 1, 5);
        GridPane.setHalignment(lblSlutDato, HPos.RIGHT);

        txfSlutDato = new TextField();
        this.add(txfSlutDato, 2, 5);
        txfSlutDato.setEditable(false);

        Label lblPris = new Label("Pris:");
        this.add(lblPris, 1, 6);
        GridPane.setHalignment(lblPris, HPos.RIGHT);

        txfPris = new TextField();
        this.add(txfPris, 2, 6);
        txfPris.setEditable(false);

        Label lblDeltagere = new Label("Deltagere:");
        this.add(lblDeltagere, 1, 8);
        GridPane.setValignment(lblDeltagere, VPos.BASELINE);
        lblDeltagere.setPadding(new Insets(4, 0, 4, 0));

        txaDeltager = new TextArea();
        this.add(txaDeltager, 1, 9);
        txaDeltager.setPrefWidth(200);
        txaDeltager.setPrefHeight(100);
        txaDeltager.setEditable(false);

        Label lblHoteller = new Label("Hoteller: ");
        this.add(lblHoteller, 2, 8);
        GridPane.setValignment(lblHoteller, VPos.BASELINE);
        lblHoteller.setPadding(new Insets(4, 0, 4, 0));

        txaHoteller = new TextArea();
        this.add(txaHoteller, 2, 9);
        txaHoteller.setPrefWidth(200);
        txaHoteller.setPrefHeight(100);
        txaHoteller.setEditable(false);

        Label lblLedsagere = new Label("Ledsagere:");
        this.add(lblLedsagere, 0, 10);

        txaLedsagere = new TextArea();
        this.add(txaLedsagere, 0, 11);
        txaLedsagere.setPrefWidth(200);
        txaLedsagere.setPrefHeight(100);
        txaLedsagere.setEditable(false);

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
    }

    // -----------------------------------------------------------------------------------------------------------------
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

    public void selectedKonferenceChanged() {
        this.updateControls();
    }

    private void selectedUdflugtChanged() {
        this.updateUdflugtControls();
    }

    //TODO
    public void updateControls() {
        Konference konference = lvwPriser.getSelectionModel().getSelectedItem();
        if (konference != null) {
            txfNavn.setText(konference.getNavn());
            txfAdresse.setText(konference.getAdresse());
            txaBeskrivelse.setText(konference.getBeskrivelse());
            txfstartDato.setText(konference.getStartDato().toString());
            txfSlutDato.setText(konference.getSlutDato().toString());
            txfPris.setText("" + konference.getPrisPrDag());
            StringBuilder sb = new StringBuilder();
            for (Tilmelding t : konference.getTilmeldinger()) {
                sb.append(t.getDeltager() + "\n");
            }
            txaDeltager.setText(sb.toString());

            StringBuilder sb1 = new StringBuilder();
            for (Hotel hotel : konference.getHoteller()) {
                sb1.append(hotel.getNavn() + "\n");
            }
            txaHoteller.setText(sb1.toString());
            lvwUdflugter.getItems().setAll(konference.getUdflugter());
        } else {
            txfNavn.clear();
            txfAdresse.clear();
            txaBeskrivelse.clear();
            txfstartDato.clear();
            txfSlutDato.clear();
            txfPris.clear();
        }

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
