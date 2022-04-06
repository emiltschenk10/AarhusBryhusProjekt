package gui;

import application.controller.Controller;
import application.model.Arrangement;
import application.model.Udlejning;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ArrangementPane extends GridPane {

    private final ListView<Arrangement> lvwArrangementer;
    private final TextField txfnavn, txfbeskrivelse, txfPris;
    private Udlejning udlejning;
    private RadioButton r1, r2, r3;
    private Controller controller = new Controller();
    private DatePicker datePickerAfleveringsDato, datePickerUdleveringsDato;
    private Button btnGem, btnOpretProdukt;


    public ArrangementPane() {
        this.setPadding(new Insets(20));
        this.setHgap(15);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();


        Label lblArrangementer = new Label("Arrangementer:");
        this.add(lblArrangementer, 0, 1);

        lvwArrangementer = new ListView<>();
        this.add(lvwArrangementer, 0, 2, 1, 5);
        lvwArrangementer.setPrefWidth(200);
        lvwArrangementer.setPrefHeight(200);
        lvwArrangementer.getItems().setAll(controller.getArrangementer());




        Button btnFjern = new Button("Fjern");
        this.add(btnFjern, 3, 4);
        GridPane.setHalignment(btnFjern, HPos.LEFT);
        btnFjern.setOnAction(event -> this.fjernAction());




        Label lblOpretProduktgruppe = new Label("Opret et nyt arrangement:");
        this.add(lblOpretProduktgruppe, 0, 8);

        Label lblPGnavn = new Label("Navn:");
        this.add(lblPGnavn, 0, 9);
        GridPane.setHalignment(lblPGnavn, HPos.LEFT);

        txfnavn = new TextField();
        this.add(txfnavn, 0, 10);
        GridPane.setHalignment(txfnavn, HPos.RIGHT);
        txfnavn.setPrefWidth(50);

        Label lblPGbeskrivelse = new Label("Beskrivelse:");
        this.add(lblPGbeskrivelse, 0, 11);

        txfbeskrivelse = new TextField();
        this.add(txfbeskrivelse, 0, 12);

        Label lblPris = new Label("Pris:");
        this.add(lblPris, 0, 13);

        txfPris = new TextField();
        this.add(txfPris, 0, 14);

        Button btnOpretProduktGruppe = new Button("Opret Arrangement");
        this.add(btnOpretProduktGruppe, 0, 15);
        GridPane.setHalignment(btnOpretProduktGruppe, HPos.LEFT);
        btnOpretProduktGruppe.setOnAction(event -> opretArrangementAction());


    }

    public void fjernAction(){
        Arrangement arrangement = lvwArrangementer.getSelectionModel().getSelectedItem();
        if (arrangement != null) {
            controller.removeArrangement(arrangement);
            lvwArrangementer.getItems().setAll(controller.getArrangementer());
        }
    }


    public void opretArrangementAction(){
        String navn = txfnavn.getText();
        String beskrivelse = txfbeskrivelse.getText();
        double pris = Double.parseDouble(txfPris.getText());
        controller.createArrangement(navn, beskrivelse, pris);
        lvwArrangementer.getItems().setAll(controller.getArrangementer());
      //  controller1.createProduktGruppe(navn, beskrivelse);
     //   lvwArrangementer.getItems().setAll(controller1.getProduktGrupper());
        txfnavn.clear();
        txfbeskrivelse.clear();
        txfPris.clear();
    }
}

