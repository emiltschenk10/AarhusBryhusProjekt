package gui;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.Udlejning;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SortimentPane extends GridPane {
    private final ListView<ProduktListview> lvwProduktGrupper;
    //private final ListView<Ordrelinje> lvwIndkøbskurv;
    //private final TextField txfAntal, txfRabat, txfSamletPris;
    private Udlejning udlejning;
    private RadioButton r1, r2, r3;
    private Controller controller;
    private DatePicker datePickerAfleveringsDato, datePickerUdleveringsDato;


    public SortimentPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();


        Label lblPriser = new Label("Udlejningspriser:");
        this.add(lblPriser, 0, 1);

        lvwProduktGrupper = new ListView<>();
        this.add(lvwProduktGrupper, 0, 2, 1, 5);
        lvwProduktGrupper.setPrefWidth(200);
        lvwProduktGrupper.setPrefHeight(200);

    }
}
