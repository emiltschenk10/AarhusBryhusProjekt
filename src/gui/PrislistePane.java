package gui;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.Produktgruppe;
import application.model.Udlejning;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class PrislistePane extends GridPane {

    private final ListView<Prisliste> lvwPrislister;
    private final ListView<ProduktListview> lvwProdukter;
    private final TextField txfNavn, txfPris;
    private Controller controller = new Controller();
    private ComboBox<Produkt> tilgængeligeProdukter;
    private Button btnFjern;


    public PrislistePane() {
        this.setPadding(new Insets(20));
        this.setHgap(15);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();


        Label lblPrislister = new Label("Prislister:");
        this.add(lblPrislister, 0, 1);

        lvwPrislister = new ListView<>();
        this.add(lvwPrislister, 0, 2, 1, 5);
        lvwPrislister.setPrefWidth(200);
        lvwPrislister.setPrefHeight(200);
        lvwPrislister.getItems().setAll(controller.getAllePrislister());

        Label lblNavn = new Label("Navn:");
        this.add(lblNavn, 0, 8);

        txfNavn = new TextField();
        this.add(txfNavn, 0, 9);

        Button btnOpretPrisliste = new Button("Opret Prisliste");
        this.add(btnOpretPrisliste, 0, 10);
        btnOpretPrisliste.setOnAction(event -> this.opretPrislisteAction());

        Label lblProdukter = new Label("Produkter:");
        this.add(lblProdukter, 3, 1);
        lvwProdukter = new ListView<>();
        this.add(lvwProdukter, 3, 2, 2, 5);
        lvwProdukter.setPrefWidth(200);
        lvwProdukter.setPrefHeight(200);

        Label lblTilføjTilPrisliste = new Label("Tilføj til prisliste:");
        this.add(lblTilføjTilPrisliste, 3, 8);

        tilgængeligeProdukter = new ComboBox<>();
        this.add(tilgængeligeProdukter, 3, 9);

        Label lblPris = new Label("Pris:");
        this.add(lblPris, 3, 10);

        txfPris = new TextField();
        this.add(txfPris, 3, 11);

        Button btnTilføj = new Button("Tilføj produkt");
        this.add(btnTilføj, 3, 12);
        btnTilføj.setOnAction(event -> this.tilføjAction());

        btnFjern = new Button("Fjern produkt");
        this.add(btnFjern, 6, 5);
        btnFjern.setDisable(true);
        btnFjern.setOnAction(event -> this.fjernAction());



        ChangeListener<Prisliste> listener1 = (ov, gammelPrisListe, nyPrisListe) -> this.selectedPrislisteChanged();
        lvwPrislister.getSelectionModel().selectedItemProperty().addListener(listener1);

        ChangeListener<ProduktListview> listener2 = (ov, gammelProdukt, nyProdukt) -> this.selectedProduktChanged();
        lvwProdukter.getSelectionModel().selectedItemProperty().addListener(listener2);
    }


    public void selectedPrislisteChanged(){
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        if (prisliste != null) {
            ArrayList<ProduktListview> produktListviews = new ArrayList<>();
            for (Produkt produkt : prisliste.getProduktpriser().keySet()) {
                produktListviews.add(new ProduktListview(produkt, prisliste.getProduktpriser().get(produkt)));
            }
            lvwProdukter.getItems().setAll(produktListviews);
            tilgængeligeProdukter.getItems().setAll(controller.TilgængeligeProdukterTilPrisliste(prisliste));
        }
    }

    public void opretPrislisteAction(){
        String navn = txfNavn.getText();
        controller.createPrisliste(navn);
        lvwPrislister.getItems().setAll(controller.getAllePrislister());
        txfNavn.clear();
    }

    public void tilføjAction(){
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        Produkt produkt = tilgængeligeProdukter.getSelectionModel().getSelectedItem();
        double pris = Double.parseDouble(txfPris.getText());
        prisliste.addProdukt(produkt, pris);
        txfPris.clear();
        selectedPrislisteChanged();
    }

    public void selectedProduktChanged(){
        btnFjern.setDisable(false);
    }

    public void fjernAction(){
        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem().getProdukt();
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        prisliste.removeProdukt(produkt);
        selectedPrislisteChanged();
    }
}
