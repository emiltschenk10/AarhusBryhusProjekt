package gui;
/*
import application.controller.Controller;
import application.model.Konference;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class KonferenceWindow extends Stage {
    private final Konference konference;

    public KonferenceWindow(String title, Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.konference = konference;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public KonferenceWindow(String title) {
        this(title, null);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private TextField txfNavn, txfAdresse, txfPrisPrDag;
    private TextArea txaBeskrivelse;
    private DatePicker startDatoDatePicker, slutDatoDatePicker;
    private Label lblError;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0, 0);

        txfNavn = new TextField();
        pane.add(txfNavn, 0, 1);
        txfNavn.setPrefWidth(200);

        Label lblAdresse = new Label("Adresse");
        pane.add(lblAdresse, 0, 2);

        txfAdresse = new TextField();
        pane.add(txfAdresse, 0, 3);
        txfAdresse.setPrefWidth(200);

        Label lblBeskrivelse = new Label("Beskrivelse");
        pane.add(lblBeskrivelse, 0, 4);

        txaBeskrivelse = new TextArea();
        pane.add(txaBeskrivelse, 0, 5);
        txaBeskrivelse.setPrefWidth(200);
        txaBeskrivelse.setPrefHeight(100);

        Label lblStartDato = new Label("Start dato (yyyy-mm-dd)");
        pane.add(lblStartDato, 0, 6);

        startDatoDatePicker = new DatePicker();
        pane.add(startDatoDatePicker, 0, 7);
        startDatoDatePicker.setEditable(false);

        Label lblSlutDato = new Label("Slut dato (yyyy-mm-dd)");
        pane.add(lblSlutDato, 0, 8);

        slutDatoDatePicker = new DatePicker();
        pane.add(slutDatoDatePicker, 0, 9);
        slutDatoDatePicker.setEditable(false);

        Label lblPrisPrDag = new Label("Pris pr. Dag");
        pane.add(lblPrisPrDag, 0, 10);
        txfPrisPrDag = new TextField();
        pane.add(txfPrisPrDag, 0, 11);

        Button btnAnuller = new Button("Anuller");
        pane.add(btnAnuller, 0, 12);
        GridPane.setHalignment(btnAnuller, HPos.LEFT);
        btnAnuller.setOnAction(event -> this.afbrydAction());

        Button btnGem = new Button("Gem");
        pane.add(btnGem, 0, 12);
        GridPane.setHalignment(btnGem, HPos.RIGHT);
        btnGem.setOnAction(event -> this.okAction());

        lblError = new Label();
        pane.add(lblError, 0, 13);
        lblError.setStyle("-fx-text-fill: red");

        this.initControls();
    }

    private void initControls() {
        if (konference != null) {
            txfNavn.setText(konference.getNavn());
            txfAdresse.setText(konference.getAdresse());
            txaBeskrivelse.setText(konference.getBeskrivelse());
            startDatoDatePicker.setValue(konference.getStartDato());
            slutDatoDatePicker.setValue(konference.getSlutDato());
            txfPrisPrDag.setText("" + konference.getPrisPrDag());
        } else {
            txfNavn.clear();
            txfAdresse.clear();
            txaBeskrivelse.clear();
            txfPrisPrDag.clear();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void afbrydAction() {
        this.hide();
    }

    //TODO
    private void okAction() {
        String navn = txfNavn.getText().trim();
        if (navn.length() == 0) {
            lblError.setText("Navn er tomt");
        } else {
            String adresse = txfAdresse.getText().trim();
            if (adresse.length() == 0) {
                lblError.setText("Adresse er tomt");
            } else {
                String beskrivelse = txaBeskrivelse.getText().trim();
                if (beskrivelse.length() == 0) {
                    lblError.setText("Beskrivelse er tomt");
                } else {
                    LocalDate startDato = null;
                    try {
                        startDato = startDatoDatePicker.getValue();
                    } catch (DateTimeException ex) {

                    }
                    if (startDato == null) {
                        lblError.setText("Start dato er tomt");
                    } else {
                        LocalDate slutDato = null;
                        try {
                            slutDato = slutDatoDatePicker.getValue();
                        } catch (DateTimeException ex) {

                        }
                        if (slutDato == null || ChronoUnit.DAYS.between(startDato, slutDato) < 0) {
                            lblError.setText("Slut dato er tomt");
                        } else {
                            int prisPrDag = -1;
                            try {
                                prisPrDag = Integer.parseInt(txfPrisPrDag.getText().trim());
                            } catch (NumberFormatException ex) {
                                // Do noting
                            }
                            if (prisPrDag < 0) {
                                lblError.setText("Pris pr dag er tomt");
                            } else {
                                if (konference != null) {
                                    Controller.updateKonference(konference, navn, adresse,
                                            beskrivelse, startDato, slutDato, prisPrDag);
                                } else {
                                    Controller.createKonference(navn, adresse, beskrivelse,
                                            startDato, slutDato, prisPrDag);
                                }
                                this.hide();
                            }
                        }
                    }
                }
            }
        }
    }
}
*/
