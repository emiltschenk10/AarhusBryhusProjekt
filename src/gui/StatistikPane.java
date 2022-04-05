package gui;

import application.controller.Controller;
import application.model.Arrangement;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.Produktgruppe;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;


public class StatistikPane extends GridPane {

    private DatePicker datePickerSalgForDag, datePickerSalgForDag2, datePickerKlip1, datePickerKlip2;;

    private final ComboBox<Produkt> cbProdukt;
    private final ComboBox<Produktgruppe> cbProduktGruppe;
    private final ComboBox<Arrangement> cbArragement;

    private final Button btnSalgForDato2, btnKlipDate;


    private Label lblDagsIntjeneste, lblProduktgruppe, lblProdukt, lblArragement, lblDato, lblAntalProdukterSolgt, lblKlipDate1,lblKlipDate2,lblKlipDate3;

    private Controller controller = new Controller();

    public StatistikPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();


        datePickerSalgForDag = new DatePicker();
        this.add(datePickerSalgForDag, 3, 1);

        datePickerSalgForDag2 = new DatePicker();
        this.add(datePickerSalgForDag2, 5, 6);
        datePickerSalgForDag2.setDisable(true);
        datePickerSalgForDag2.setOnAction(event -> enableBtn());

        datePickerKlip1  = new DatePicker();
        this.add(datePickerKlip1,5,7);

        datePickerKlip2  = new DatePicker();
        this.add(datePickerKlip2,6,7);

        lblDagsIntjeneste = new Label("Indtjeneste for dato: ");
        this.add(lblDagsIntjeneste, 3, 3);
        GridPane.setValignment(lblDagsIntjeneste, VPos.BOTTOM);

        lblProduktgruppe = new Label("Produktgruppe: ");
        this.add(lblProduktgruppe, 4, 3);
        GridPane.setValignment(lblProduktgruppe, VPos.BOTTOM);

        lblProdukt = new Label("Produkt: ");
        this.add(lblProdukt, 4, 4);
        GridPane.setValignment(lblProdukt, VPos.BOTTOM);

        lblArragement = new Label("Arragement: ");
        this.add(lblArragement, 4, 5);
        GridPane.setValignment(lblArragement, VPos.BOTTOM);

        lblDato = new Label("Dato: ");
        this.add(lblDato, 4, 6);
        GridPane.setValignment(lblDato, VPos.BOTTOM);

        lblAntalProdukterSolgt = new Label("Antal solgt: ");
        this.add(lblAntalProdukterSolgt, 6, 2);
        GridPane.setValignment(lblAntalProdukterSolgt, VPos.BOTTOM);

        lblKlipDate1 = new Label("Klip brugt mellem: ");
        this.add(lblKlipDate1,4,7);
        GridPane.setValignment(lblKlipDate1, VPos.BOTTOM);

        lblKlipDate2 = new Label("Antal brugte klip: ");
        this.add(lblKlipDate2,5,8);
        GridPane.setValignment(lblKlipDate2, VPos.BOTTOM);

        lblKlipDate3 = new Label("Antal solgte klippekort: ");
        this.add(lblKlipDate3,6,8);
        GridPane.setValignment(lblKlipDate3, VPos.BOTTOM);

        btnKlipDate = new Button("Klip statistik for periode: ");
        this.add(btnKlipDate, 4, 8);
        GridPane.setValignment(btnKlipDate, VPos.TOP);
        btnKlipDate.setOnAction(event -> antalklipIPeriode());


        cbProduktGruppe = new ComboBox<>();
        this.add(cbProduktGruppe, 5, 3);
        GridPane.setValignment(cbProduktGruppe, VPos.TOP);
        GridPane.setHalignment(cbProduktGruppe, HPos.RIGHT);
        cbProduktGruppe.setMaxWidth(180);
        cbProduktGruppe.getItems().setAll(controller.getProduktGrupper());
        cbProduktGruppe.setOnAction(event -> this.selectedProduktgruppeProdukt());

        cbProdukt = new ComboBox<>();
        this.add(cbProdukt,5,4);
        GridPane.setValignment(cbProdukt, VPos.TOP);
        GridPane.setHalignment(cbProdukt, HPos.RIGHT);
        cbProdukt.setMaxWidth(180);
        cbProdukt.setDisable(true);
        cbProdukt.setOnAction(event -> enableDate());


        cbArragement = new ComboBox<>();
        this.add(cbArragement, 5, 5);
        GridPane.setValignment(cbArragement, VPos.TOP);
        GridPane.setHalignment(cbArragement, HPos.RIGHT);
        cbArragement.setMaxWidth(180);
        cbArragement.getItems().setAll(controller.getArrangementer());
        cbArragement.getItems().add(null);

        Button btnSalgForDato = new Button("Indtjeneste for dato: ");
        this.add(btnSalgForDato, 3, 2);
        GridPane.setValignment(btnSalgForDato, VPos.TOP);
        btnSalgForDato.setOnAction(event -> this.salgForDato());

        btnSalgForDato2 = new Button("Antal solgte af produkt: ");
        this.add(btnSalgForDato2, 5, 2);
        GridPane.setValignment(btnSalgForDato2, VPos.TOP);
        btnSalgForDato2.setDisable(true);
        btnSalgForDato2.setOnAction(event -> this.antalSolgteProdukt());
    }

    public void salgForDato(){

        String indtjeneste = (controller.salgForDato(datePickerSalgForDag.getValue()) + " Kr.");
        lblDagsIntjeneste.setText("Indtjeneste for dato: " + indtjeneste);
    }

    public void antalSolgteProdukt(){

        String antal = controller.salgForProduktogProduktgruppe(cbProduktGruppe.getValue(),cbProdukt.getValue(),datePickerSalgForDag2.getValue(),cbArragement.getValue())+ "";

        lblAntalProdukterSolgt.setText("Antal solgt: " + antal);
    }

    public void selectedProduktgruppeProdukt(){
        Produktgruppe produktgruppe = cbProduktGruppe.getSelectionModel().getSelectedItem();
        cbProdukt.getItems().setAll(produktgruppe.getProdukter());
        cbProdukt.setDisable(false);

    }

    public void antalklipIPeriode(){
      String antal = controller.antalBrugteKlip(datePickerKlip1.getValue(),datePickerKlip2.getValue()) + "";
      lblKlipDate2.setText("Antal brugte klip: " + antal);

      String antal2 = controller.antalSolgteKlip(datePickerKlip1.getValue(),datePickerKlip2.getValue()) + ""
;      lblKlipDate3.setText("Antal solgte klippekort: " + antal2);
    }

    public void enableBtn(){
        btnSalgForDato2.setDisable(false);
    }

    public void enableDate(){
        datePickerSalgForDag2.setDisable(false);
    }
}
