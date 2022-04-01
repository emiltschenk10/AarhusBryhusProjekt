package gui;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class RedigerPane extends GridPane {

    ListView<Salg> salgListView;
    ListView<Udlejning> udlejningListView;
    CheckBox chxSalgBetalt,chxUdestående,chxUdlejBetalt;
    DatePicker datePicker,udleveringsDatePick,afleveringsDatePick;
    Button btnRediger,btnGem,btnRediger2,btnGem2;
    private int salgNr;
    private int udlejningsNr;
    private Controller controller = new Controller();

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
        ChangeListener<Udlejning> listener1 = (ov, gammelUdlejning, nyUdlejning) -> this.selectedUdlejningChanged();
        udlejningListView.getSelectionModel().selectedItemProperty().addListener(listener1);



        chxSalgBetalt = new CheckBox("Betalt");
        this.add(chxSalgBetalt,0,3);
        chxSalgBetalt.setDisable(true);

        chxUdlejBetalt = new CheckBox("Betalt");
        this.add(chxUdlejBetalt,1,3);
        chxUdlejBetalt.setDisable(true);

        chxUdestående = new CheckBox("Udestående");
        this.add(chxUdestående,1,4);
        chxUdestående.setDisable(true);

        Label lblDato = new Label("Dato:");

        datePicker = new DatePicker();
        datePicker.setDisable(true);

        HBox hBox = new HBox(lblDato,datePicker);
        this.add(hBox,0,4);
        hBox.setSpacing(10);

        Label lblUdleveringsDato = new Label("Udleverings dato:");

        udleveringsDatePick = new DatePicker();
        udleveringsDatePick.setDisable(true);

        HBox hBox1 = new HBox(lblUdleveringsDato,udleveringsDatePick);
        this.add(hBox1,1,5);
        hBox1.setSpacing(10);

        Label lblAfleveringsDato = new Label("Afleverings dato:");

        afleveringsDatePick = new DatePicker();
        afleveringsDatePick.setDisable(true);

        HBox hBox2 = new HBox(lblAfleveringsDato,afleveringsDatePick);
        this.add(hBox2,1,6);
        hBox2.setSpacing(10);


        btnRediger = new Button("Rediger salg");
        btnRediger.setDisable(true);
        btnRediger.setOnAction(event -> btnRedigerAction());

        btnGem = new Button("Gem");
        btnGem.setDisable(true);


        HBox hBox3 = new HBox(btnRediger,btnGem);
        this.add(hBox3,0,5);
        hBox3.setSpacing(10);

        btnRediger2 = new Button("Rediger Udlejning");
        btnRediger2.setDisable(true);
        btnRediger2.setOnAction(event -> btnRediger2Action());

        btnGem2 = new Button("Gem");
        btnGem2.setDisable(true);

        HBox hBox4 = new HBox(btnRediger2,btnGem2);
        this.add(hBox4,1,7);
        hBox4.setSpacing(10);

    }

    public void selectedSalgChanged(){
        btnRediger.setDisable(false);;
    }

    public void selectedUdlejningChanged(){
        btnRediger2.setDisable(false);
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

    public void btnGemAction(){
        Salg salg = salgListView.getSelectionModel().getSelectedItem();
        if(salg.getSalgsNr() ==salgNr){
            Controller.setSalgsDato(salg,datePicker.getValue());
            if(chxSalgBetalt.isSelected()){
                //TODO lav en set salg som betalt i controlleren
            }
        }
    }

    public void btnRediger2Action(){
        Udlejning udlejning = udlejningListView.getSelectionModel().getSelectedItem();
        udleveringsDatePick.setDisable(false);
        afleveringsDatePick.setDisable(false);
        btnGem2.setDisable(false);
        if(!udlejning.isBetalt()){
            chxUdlejBetalt.setDisable(false);
        }else if(udlejning.isUdestående()){
            chxUdestående.setDisable(false);
            chxUdestående.setSelected(true);
        }
        this.udlejningsNr = udlejning.getUdlejningsNr();
        udleveringsDatePick.setValue(udlejning.getUdleveringsDato());
        afleveringsDatePick.setValue(udlejning.getAfleveringsDato());
    }

    public void btnGem2Action(){
        Udlejning udlejning = udlejningListView.getSelectionModel().getSelectedItem();
        if(udlejningsNr == udlejning.getUdlejningsNr()){
            Controller.setUdleveringsDato(udlejning,udleveringsDatePick.getValue());
            Controller.setAfleveringsDato(udlejning,afleveringsDatePick.getValue());
            //TODO Betalt og udestående
        }
    }

    public void updateLists(){
        salgListView.getItems().setAll(controller.getAktuelleSalg());
        udlejningListView.getItems().setAll(controller.getAktuelleUdlejninger());
    }
}
