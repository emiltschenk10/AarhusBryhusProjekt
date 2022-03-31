package gui;

import application.controller.Controller;
import application.model.Produkt;
import application.model.Produktgruppe;
import application.model.Salg;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RedigerPane extends GridPane {

    ListView<Salg> salgListView;

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
        salgListView.setPrefHeight(300);
        salgListView.setPrefWidth(300);
        //TODO Vi skal lave en metode til kun at vise salg der ikke er betalt
        salgListView.getItems().setAll(controller.getAktuelleSalg());

    }

}
