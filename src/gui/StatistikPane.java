package gui;

import application.controller.Controller;
import application.model.Arrangement;
import application.model.Produkt;
import application.model.Produktgruppe;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class StatistikPane extends GridPane {

    private DatePicker datePickerSalgForDag, datePickerSalgForDag2;

    private final ComboBox<Produkt> cbProdukt;
    private final ComboBox<Produktgruppe> cbProduktGruppe;
    private final ComboBox<Arrangement> cbArragement;


    private Label lblDagsIntjeneste;


    public StatistikPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();


        datePickerSalgForDag = new DatePicker();
        this.add(datePickerSalgForDag, 3, 1);

        datePickerSalgForDag2 = new DatePicker();
        this.add(datePickerSalgForDag, 4, 6);

        lblDagsIntjeneste = new Label();
        this.add(lblDagsIntjeneste, 3, 3);
        GridPane.setValignment(lblDagsIntjeneste, VPos.BOTTOM);


        cbProduktGruppe = new ComboBox<>();
        this.add(cbProduktGruppe, 4, 3);
        GridPane.setValignment(cbProduktGruppe, VPos.TOP);
        GridPane.setHalignment(cbProduktGruppe, HPos.RIGHT);
        cbProduktGruppe.setMaxWidth(50);

        cbProdukt = new ComboBox<>();
        this.add(cbProdukt,4,4);
        GridPane.setValignment(cbProdukt, VPos.TOP);
        GridPane.setHalignment(cbProdukt, HPos.RIGHT);
        cbProdukt.setMaxWidth(50);


        cbArragement = new ComboBox<>();
        this.add(cbArragement, 4, 5);
        GridPane.setValignment(cbArragement, VPos.TOP);
        GridPane.setHalignment(cbArragement, HPos.RIGHT);
        cbArragement.setMaxWidth(50);

        Button btnSalgForDato = new Button("Indtjeneste for dato: ");
        this.add(btnSalgForDato, 3, 2);
        GridPane.setValignment(btnSalgForDato, VPos.TOP);
        btnSalgForDato.setOnAction(event -> this.salgForDato());

        Button btnSalgForDato2 = new Button("Antal solgte af produkt: ");
        this.add(btnSalgForDato, 4, 2);
        GridPane.setValignment(btnSalgForDato, VPos.TOP);
        btnSalgForDato.setOnAction(event -> this.salgForDato());
    }

    public void salgForDato(){
        Controller controller = new Controller();

        String indtjeneste = (controller.salgForDato(datePickerSalgForDag.getValue()) + " Kr.");
        lblDagsIntjeneste.setText("Indtjeneste for dag = " + controller.salgForDato(datePickerSalgForDag.getValue()) + " Kr.");
    }

}
