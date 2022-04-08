package gui;

import application.controller.Controller;
import application.model.Betalingsform;
import application.model.Kunde;
import application.model.Salg;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.Storage;

import java.time.LocalDate;

public class KundeWindow extends Stage {

    public KundeWindow(String title, Salg salg){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.salg=salg;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------------------------------------------

    private TextField txfName,txfAdresse,txfTlfNr;
    private ListView<Kunde> kundeListView;
    private RadioButton rbNyKunde,rbTidligereKunde,rbBaren;
    private ToggleGroup group = new ToggleGroup();
    private DatePicker afhentPicker;
    private ComboBox<Betalingsform> cbxBetalingsform;
    private Storage storage = Storage.getInstance();
    private Salg salg;

    private void initContent(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        rbNyKunde = new RadioButton("Ny kunde");
        rbNyKunde.setToggleGroup(group);
        rbNyKunde.setOnAction(event -> nyKundeAction());
        rbNyKunde.setSelected(true);

        rbTidligereKunde = new RadioButton("Tidligere kunde");
        rbTidligereKunde.setToggleGroup(group);
        rbTidligereKunde.setOnAction(event -> showKunderAction());

        rbBaren = new RadioButton("Baren");
        rbBaren.setToggleGroup(group);
        rbBaren.setOnAction(event -> barenRbAction());



        HBox hBox = new HBox(rbNyKunde,rbTidligereKunde,rbBaren);
        pane.add(hBox,1,0);
        hBox.setSpacing(5);



        Label lblName = new Label("Name:");

        txfName = new TextField();

        Label lblAdresse = new Label("Adresse:");

        txfAdresse = new TextField();

        Label lblTlfNr = new Label("Tlf.nr:");

        txfTlfNr = new TextField();


        Label lblAfhentning = new Label("Dato:");

        afhentPicker = new DatePicker();

        cbxBetalingsform = new ComboBox<>();
        cbxBetalingsform.getItems().setAll(storage.getBetalingsformer());


        VBox vBox = new VBox(lblName,lblAdresse,lblTlfNr,lblAfhentning);
        pane.add(vBox,0,1);
        vBox.setSpacing(20);

        VBox vBox1 = new VBox(txfName,txfAdresse,txfTlfNr,afhentPicker,cbxBetalingsform);
        pane.add(vBox1,1,1);
        vBox1.setSpacing(10);

        kundeListView = new ListView<>();
        pane.add(kundeListView,2,1);
        kundeListView.setPrefWidth(200);
        kundeListView.setPrefHeight(200);


        Button btnOk = new Button("OK");
        pane.add(btnOk,1,4);
        btnOk.setOnAction(event -> btnOkAction());


        //---------------------------------------------------------------------------------------------
    }



    private void showKunderAction(){
        kundeListView.getItems().setAll(storage.getKunder());
        txfName.clear();
        txfAdresse.clear();
        txfTlfNr.clear();
        txfName.setEditable(false);
        txfAdresse.setEditable(false);
        txfTlfNr.setEditable(false);
    }

    private void nyKundeAction(){
        kundeListView.getItems().clear();
        txfName.setEditable(true);
        txfAdresse.setEditable(true);
        txfTlfNr.setEditable(true);
    }

    private void barenRbAction(){
        txfName.clear();
        txfAdresse.clear();
        txfTlfNr.clear();
        txfName.setEditable(false);
        txfAdresse.setEditable(false);
        txfTlfNr.setEditable(false);
        afhentPicker.setDisable(true);
    }

    private void btnOkAction(){
        //TODO Vi skal have lavet en setKunde i controller til salg
        Controller controller = Controller.getInstance();
        if(rbNyKunde.isSelected()){
            Kunde kunde = controller.createKunde(txfName.getText(),Integer.parseInt(txfTlfNr.getText()),txfAdresse.getText());
            controller.setSalgsDato(salg,afhentPicker.getValue());
            controller.setKundePåSalg(kunde,salg);
            controller.setBetalingsformPåSalg(cbxBetalingsform.getSelectionModel().getSelectedItem(),salg);
        }else if(rbTidligereKunde.isSelected() && kundeListView.getSelectionModel().getSelectedItem()!=null){
            controller.setKundePåSalg(kundeListView.getSelectionModel().getSelectedItem(),salg);
            controller.setSalgsDato(salg,afhentPicker.getValue());
            controller.setBetalingsformPåSalg(cbxBetalingsform.getSelectionModel().getSelectedItem(),salg);
        }else{
            controller.setKundePåSalg(null,salg);
            controller.setSalgsDato(salg, LocalDate.now());
            controller.setBetalingsformPåSalg(cbxBetalingsform.getSelectionModel().getSelectedItem(), salg);
            controller.setSalgSomBetalt(salg,true);
        }
        this.hide();
    }



}
