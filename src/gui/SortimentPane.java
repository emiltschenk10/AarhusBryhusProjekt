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



public class SortimentPane extends GridPane {
    private final ListView<Produktgruppe> lvwProduktGrupper;
    private final ListView<Produkt> lvwProdukter;
    private final TextField txfNavn, txfBeskrivelse, txfKlipPris, txfPant, txfPGnavn, txfPGbeskrivelse;
    private Button btnGem, btnOpretProdukt;


    public SortimentPane() {
        this.setPadding(new Insets(20));
        this.setHgap(15);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = Controller.getInstance();

        Label lblPriser = new Label("Produkt Grupper:");
        this.add(lblPriser, 0, 1);

        lvwProduktGrupper = new ListView<>();
        this.add(lvwProduktGrupper, 0, 2, 1, 5);
        lvwProduktGrupper.setPrefWidth(200);
        lvwProduktGrupper.setPrefHeight(200);
        lvwProduktGrupper.getItems().setAll(controller.getProduktGrupper());


        Label lblProdukter = new Label("Produkter:");
        this.add(lblProdukter, 3, 1);
        lvwProdukter = new ListView<>();
        this.add(lvwProdukter, 3, 2, 2, 5);
        lvwProdukter.setPrefWidth(200);
        lvwProdukter.setPrefHeight(200);


        Button btnRediger = new Button("Rediger produkt");
        this.add(btnRediger, 4, 1);
        GridPane.setHalignment(btnRediger, HPos.LEFT);
        btnRediger.setOnAction(event -> this.redigerAction());


        Label lblNytProdukt = new Label("Tilføj et nyt produkt:");
        this.add(lblNytProdukt, 5, 0);
        GridPane.setHalignment(lblNytProdukt, HPos.CENTER);

        Label lblNavn = new Label("Navn:");
        this.add(lblNavn, 5, 2);
        txfNavn = new TextField();
        this.add(txfNavn, 6, 2);


        Label lblBeskrivelse = new Label("Beskrivelse:");
        this.add(lblBeskrivelse, 5, 3);
        txfBeskrivelse = new TextField();
        this.add(txfBeskrivelse, 6, 3);

        Label lblKlipPris = new Label("Klippris:");
        this.add(lblKlipPris, 5, 4);
        txfKlipPris = new TextField();
        this.add(txfKlipPris, 6, 4);

        Label lblPant = new Label("Pant:");
        this.add(lblPant, 5, 5);
        txfPant = new TextField();
        this.add(txfPant, 6, 5);


        Label lblOpretProduktgruppe = new Label("Opret en ny produktgruppe:");
        this.add(lblOpretProduktgruppe, 0, 8);

        Label lblPGnavn = new Label("Navn:");
        this.add(lblPGnavn, 0, 9);
        GridPane.setHalignment(lblPGnavn, HPos.LEFT);

        txfPGnavn = new TextField();
        this.add(txfPGnavn, 0, 10);
        GridPane.setHalignment(txfPGnavn, HPos.RIGHT);
        txfPGnavn.setPrefWidth(50);

        Label lblPGbeskrivelse = new Label("Beskrivelse:");
        this.add(lblPGbeskrivelse, 0, 11);

        txfPGbeskrivelse = new TextField();
        this.add(txfPGbeskrivelse, 0, 12);

        Button btnOpretProduktGruppe = new Button("Opret produktgruppe");
        this.add(btnOpretProduktGruppe, 0, 13);
        GridPane.setHalignment(btnOpretProduktGruppe, HPos.LEFT);
        btnOpretProduktGruppe.setOnAction(event -> opretProduktgruppeAction());

        btnOpretProdukt = new Button("Opret Produkt");
        this.add(btnOpretProdukt, 6, 6);
        GridPane.setHalignment(btnOpretProdukt, HPos.LEFT);
        btnOpretProdukt.setOnAction(event -> this.opretAction());

        btnGem = new Button("Gem");
        this.add(btnGem, 6, 6);
        GridPane.setHalignment(btnGem, HPos.RIGHT);
        btnGem.setDisable(true);
        btnGem.setOnAction(event -> this.gemAction());

        ChangeListener<Produktgruppe> listener1 = (ov, gammelPrisListe, nyPrisListe) -> this.selectedProduktGruppeChanged();
        lvwProduktGrupper.getSelectionModel().selectedItemProperty().addListener(listener1);

    }
    Controller controller1 = Controller.getInstance();
    public void selectedProduktGruppeChanged(){
        Produktgruppe produktgruppe = lvwProduktGrupper.getSelectionModel().getSelectedItem();
        if (produktgruppe != null) {
            lvwProdukter.getItems().setAll(produktgruppe.getProdukter());
        }
    }

    public void opretAction(){
        String navn = txfNavn.getText().trim();
        String beskrivelse = txfBeskrivelse.getText();
        int klip = Integer.parseInt(txfKlipPris.getText());
        double pant = Double.parseDouble(txfPant.getText());
        Produktgruppe produktgruppe = lvwProduktGrupper.getSelectionModel().getSelectedItem();
        if (produktgruppe != null) {
            produktgruppe.createProdukt(navn, beskrivelse, klip, pant);
            lvwProdukter.getItems().setAll(produktgruppe.getProdukter());
            txfPant.clear();
            txfNavn.clear();
            txfKlipPris.clear();
            txfBeskrivelse.clear();
        }
    }
    public void redigerAction(){
        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
        txfNavn.setText(produkt.getNavn());
        txfBeskrivelse.setText(produkt.getBeskrivelse());
        txfKlipPris.setText(""+produkt.getKlipPris());
        txfPant.setText(""+ produkt.getPant());
        btnGem.setDisable(false);
        btnOpretProdukt.setDisable(true);
    }


    public void gemAction(){
        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
        produkt.setNavn(txfNavn.getText());
        produkt.setBeskrivelse(txfBeskrivelse.getText());
        produkt.setKlipPris(Integer.parseInt(txfKlipPris.getText()));
        produkt.setPant(Double.parseDouble(txfPant.getText()));
        txfPant.clear();
        txfBeskrivelse.clear();
        txfKlipPris.clear();
        txfNavn.clear();
        this.selectedProduktGruppeChanged();
    }

    public void opretProduktgruppeAction(){
        String navn = txfPGnavn.getText();
        String beskrivelse = txfPGbeskrivelse.getText();
        controller1.createProduktGruppe(navn, beskrivelse);
        lvwProduktGrupper.getItems().setAll(controller1.getProduktGrupper());
        txfPGnavn.clear();
        txfPGbeskrivelse.clear();
    }

}
