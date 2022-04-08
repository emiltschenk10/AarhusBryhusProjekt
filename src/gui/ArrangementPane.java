package gui;

import application.controller.Controller;
import application.model.Arrangement;
import application.model.Udlejning;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Calendar;

public class ArrangementPane extends GridPane {

    private final ListView<Arrangement> lvwArrangementer;
    private final TextField txfnavn, txfbeskrivelse, txfPris;
    private Controller controller = new Controller();
    private TextArea arrangementer = new TextArea();
    private  DatePicker datePicker;


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
        this.add(btnFjern, 3, 8);
        GridPane.setHalignment(btnFjern, HPos.LEFT);
        btnFjern.setOnAction(event -> this.fjernAction());

        arrangementer = new TextArea();
        this.add(arrangementer,5,2);
        GridPane.setValignment(arrangementer, VPos.TOP);
        arrangementer.setMaxWidth(300);
        arrangementer.setEditable(false);
        for (Arrangement a:controller.getArrangementer()) {
            if (LocalDate.now().equals(a.getDate())){
                arrangementer.setText(a.toString());
            }
        }




        Label lblArragementerr = new Label("Vælg dato for at se arragementer");
        this.add(lblArragementerr,4,1);

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


        datePicker = new DatePicker(LocalDate.now());
        this.add(datePicker,4,2);
        GridPane.setValignment(datePicker, VPos.BOTTOM);
        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.setOnAction(event -> visArrangementerForDag());
    }

    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
        public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
                @Override public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    for (Arrangement a: controller.getArrangementer()) {
                        if (LocalDate.from(item).equals(a.getDate())) {
                        setStyle("-fx-background-color:#38ee00;");
                        }
                    }
                }
            };
        }
    };

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
        LocalDate date = LocalDate.now();
        controller.createArrangement(navn, beskrivelse, pris, date);
        lvwArrangementer.getItems().setAll(controller.getArrangementer());
      //  controller1.createProduktGruppe(navn, beskrivelse);
     //   lvwArrangementer.getItems().setAll(controller1.getProduktGrupper());
        txfnavn.clear();
        txfbeskrivelse.clear();
        txfPris.clear();
    }

   /** public void visArrangementerForDag() {
        for (Arrangement a : controller.getArrangementer()) {
            if (datePicker.getValue().equals(a.getDate())) {
                arrangementer.setText(controller.arragementerForDag(a.getDate()).toString());
                break;
            } else {
                arrangementer.setText("Ingen arragementer denne dag");
            }
        }
    }**/

    public void visArrangementerForDag(){
        int i = 0;
        boolean go = true;
        String result = "Ingen arragementer denne dag";

        while (go && i < controller.getArrangementer().size()){
            if(datePicker.getValue().equals(controller.getArrangementer().get(i).getDate())){
                result = controller.arragementerForDag(controller.getArrangementer().get(i).getDate()).toString();
                go = false;
            } else {
                i++;
            }
        }
        arrangementer.setText(result);
    }
}

