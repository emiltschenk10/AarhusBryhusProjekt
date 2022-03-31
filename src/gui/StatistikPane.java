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


public class StatistikPane extends GridPane {

    private DatePicker datePickerSalgForDag, datePickerSalgForDag2;;

    private final ComboBox<Produkt> cbProdukt;
    private final ComboBox<Produktgruppe> cbProduktGruppe;
    private final ComboBox<Arrangement> cbArragement;


    private Label lblDagsIntjeneste, lblProduktgruppe, lblProdukt, lblArragement, lblDato, lblAntalProdukterSolgt;


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
        this.add(lblAntalProdukterSolgt, 5, 3);
        GridPane.setValignment(lblAntalProdukterSolgt, VPos.BOTTOM);


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


        cbArragement = new ComboBox<>();
        this.add(cbArragement, 5, 5);
        GridPane.setValignment(cbArragement, VPos.TOP);
        GridPane.setHalignment(cbArragement, HPos.RIGHT);
        cbArragement.setMaxWidth(180);
        cbArragement.setOnAction(event -> this.selectedProduktArragement());

        Button btnSalgForDato = new Button("Indtjeneste for dato: ");
        this.add(btnSalgForDato, 3, 2);
        GridPane.setValignment(btnSalgForDato, VPos.TOP);
        btnSalgForDato.setOnAction(event -> this.salgForDato());

        Button btnSalgForDato2 = new Button("Antal solgte af produkt: ");
        this.add(btnSalgForDato2, 5, 2);
        GridPane.setValignment(btnSalgForDato2, VPos.TOP);
        btnSalgForDato2.setOnAction(event -> this.antalSolgteProdukt());
    }

    public void salgForDato(){
        Controller controller = new Controller();

        String indtjeneste = (controller.salgForDato(datePickerSalgForDag.getValue()) + " Kr.");
        lblDagsIntjeneste.setText("Indtjeneste for dato: " + controller.salgForDato(datePickerSalgForDag.getValue()) + " Kr.");
    }

    public void antalSolgteProdukt(){
        Controller controller = new Controller();

        String antal = controller.salgForProduktogProduktgruppe(cbProduktGruppe.getValue(),cbProdukt.getValue(),datePickerSalgForDag2.getValue(),cbArragement.getValue()) + "";

        lblAntalProdukterSolgt.setText(antal);
    }

    public void selectedProduktgruppeProdukt(){
        Controller controller = new Controller();
        Produktgruppe produktgruppe = cbProduktGruppe.getSelectionModel().getSelectedItem();
        cbProdukt.getItems().setAll(produktgruppe.getProdukter());

    }

   public void selectedProduktArragement(){
        Controller controller = new Controller();
       Arrangement arragement = cbArragement.getSelectionModel().getSelectedItem();
        cbArragement.getItems().setAll(controller.);
    }

}
