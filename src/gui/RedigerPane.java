package gui;

import application.controller.Controller;
import application.model.Produkt;
import application.model.Produktgruppe;
import application.model.Salg;
import application.model.Udlejning;
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
    ListView<Udlejning> udlejningListView;


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

        Label lblUdlejninger = new Label("Udlejninger");
        this.add(lblUdlejninger,1,1);

        udlejningListView = new ListView<>();
        this.add(udlejningListView,1,2);
        udlejningListView.setPrefHeight(200);
        udlejningListView.setPrefWidth(300);
//        udlejningListView.getItems().setAll()




    }

}
