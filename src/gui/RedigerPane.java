package gui;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class RedigerPane extends GridPane {

    ListView<Salg> salgListView;
    ListView<Udlejning> udlejningListView;
    CheckBox chxSalgBetalt;
    DatePicker datePicker;
    Button btnRediger,btnGem;
    private int salgNr;

    public RedigerPane() {
        this.setPadding(new Insets(20));
        this.setHgap(15);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();


        Label lblSalg = new Label("Salg:");
        this.add(lblSalg, 0, 1);

        salgListView = new ListView<>();
        this.add(salgListView,0,2,1,1);
        salgListView.setPrefHeight(200);
        salgListView.setPrefWidth(300);
        salgListView.getItems().setAll(controller.getAktuelleSalg());
        ChangeListener<Salg> listener = (ov, gammelSalg, nySalg) -> this.selectedSalgChanged();
        salgListView.getSelectionModel().selectedItemProperty().addListener(listener);


        Label lblUdlejninger = new Label("Udlejninger");
        this.add(lblUdlejninger,1,1);

        udlejningListView = new ListView<>();
        this.add(udlejningListView,1,2);
        udlejningListView.setPrefHeight(200);
        udlejningListView.setPrefWidth(300);
        udlejningListView.getItems().setAll(controller.getAktuelleUdlejninger());


        chxSalgBetalt = new CheckBox("Betalt");
        this.add(chxSalgBetalt,0,3);
        chxSalgBetalt.setDisable(true);

        Label lblDato = new Label("Dato:");

        datePicker = new DatePicker();
        datePicker.setDisable(true);

        HBox hBox = new HBox(lblDato,datePicker);
        this.add(hBox,0,4);
        hBox.setSpacing(10);

        btnRediger = new Button("Rediger");
        btnRediger.setDisable(true);
        btnRediger.setOnAction(event -> btnRedigerAction());

        btnGem = new Button("Gem");
        btnGem.setDisable(true);

        HBox hBox1 = new HBox(btnRediger,btnGem);
        this.add(hBox1,0,5);
        hBox1.setSpacing(10);

    }

    public void selectedSalgChanged(){
        btnRediger.setDisable(false);
    }

    public void btnRedigerAction(){
        Salg salg = salgListView.getSelectionModel().getSelectedItem();
        datePicker.setDisable(false);
        btnGem.setDisable(false);
        if(!salg.isBetalt()){
            chxSalgBetalt.setDisable(false);
        }
        this.salgNr = salg.getSalgsNr();
        datePicker.setValue(salg.getDato());
    }

//    public void btnGemAction(){
//        Salg salg = salgListView.getSelectionModel().getSelectedItem();
//        if(salg.getSalgsNr() ==salgNr){
//            Controller.setSalgsDato(salg,datePicker.getValue());
//        }
//    }


}
