package gui;

import application.controller.Controller;
import application.model.Betalingsform;
import application.model.Kunde;
import application.model.Salg;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.Storage;

import java.time.LocalDate;

public class AlleSalgWindow extends Stage {

    private Controller controller;

    public AlleSalgWindow(String title){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);


        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public TextArea getTxfAlleSalg() {
        return txfAlleSalg;
    }

    //--------------------------------------------------------------------------------------
    private TextArea txfAlleSalg;
    private Label lblSalg;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(800);
        pane.setVgap(50);
        pane.setGridLinesVisible(false);

        lblSalg = new Label("Dagens salg");
        lblSalg.setFont(Font.font("Verdana", FontWeight.BOLD,30))
        ;
        pane.add(lblSalg, 0,0);

        txfAlleSalg = new TextArea();
        pane.add(txfAlleSalg, 0, 1,2,3);
        GridPane.setValignment(txfAlleSalg, VPos.TOP);
        txfAlleSalg.setMaxWidth(800);
        txfAlleSalg.setEditable(false);

    }

}
