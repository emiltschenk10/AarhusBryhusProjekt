package gui;

import application.controller.Controller;
import application.model.Betalingsform;
import application.model.Kunde;
import application.model.Udlejning;
import javafx.geometry.HPos;
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

import java.time.DateTimeException;

public class KundeWindowUdlejning extends Stage {

    public KundeWindowUdlejning(String title, Udlejning udlejning){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.udlejning = udlejning;
        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    //--------------------------------------------------------------------------------------

    private TextField txfName,txfAdresse,txfTlfNr;
    private ListView<Kunde> kundeListView;
    private RadioButton rbNyKunde,rbTidligereKunde;
    private ToggleGroup group = new ToggleGroup();
    private DatePicker afleveringsPicker, udleveringspicker;
    private ComboBox<Betalingsform> cbxBetalingsform;
    private Storage storage = Storage.getInstance();
    private Udlejning udlejning;
    private Label lblError;

    private void initContent(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        Controller controller = Controller.getInstance();

        rbNyKunde = new RadioButton("Ny kunde");
        rbNyKunde.setToggleGroup(group);
        rbNyKunde.setOnAction(event -> nyKundeAction());
        rbNyKunde.setSelected(true);

        rbTidligereKunde = new RadioButton("Tidligere kunde");
        rbTidligereKunde.setToggleGroup(group);
        rbTidligereKunde.setOnAction(event -> showKunderAction());


        HBox hBox = new HBox(rbNyKunde,rbTidligereKunde);
        pane.add(hBox,1,0);
        hBox.setSpacing(5);



        Label lblName = new Label("Name:");

        txfName = new TextField();

        Label lblAdresse = new Label("Adresse:");

        txfAdresse = new TextField();

        Label lblTlfNr = new Label("Tlf.nr:");

        txfTlfNr = new TextField();

        Label lblAflevering = new Label("Aflevering:");

        Label lblUdlevering = new Label("Udlevering:");

        afleveringsPicker = new DatePicker();

        udleveringspicker = new DatePicker();

        cbxBetalingsform = new ComboBox<>();
        cbxBetalingsform.getItems().setAll(controller.getBetalingsformer());


        lblError = new Label();
        pane.add(lblError, 0, 3, 2, 1);
        lblError.setStyle("-fx-text-fill: red");

        VBox vBox = new VBox(lblName,lblAdresse,lblTlfNr,lblAflevering, lblUdlevering);
        pane.add(vBox,0,1);
        vBox.setSpacing(20);
        vBox.setPrefWidth(65);

        VBox vBox1 = new VBox(txfName,txfAdresse,txfTlfNr,afleveringsPicker,udleveringspicker, cbxBetalingsform);
        pane.add(vBox1,1,1);
        vBox1.setSpacing(10);
        GridPane.setHalignment(vBox1, HPos.RIGHT);

        kundeListView = new ListView<>();
        pane.add(kundeListView,2,1);
        kundeListView.setPrefWidth(200);
        kundeListView.setPrefHeight(200);


        Button btnOk = new Button("OK");
        pane.add(btnOk,1,4);
        btnOk.setOnAction(event -> btnOkAction());

        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel,0,4);
        btnCancel.setOnAction(event -> cancelAction());




        //---------------------------------------------------------------------------------------------
    }


    private void cancelAction(){this.hide();}

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

    private void btnOkAction(){
        //TODO Vi skal have lavet en setKunde i controller til salg
        Controller controller = Controller.getInstance();
        try {

            if (rbNyKunde.isSelected()) {
                Kunde kunde = controller.createKunde(txfName.getText(), Integer.parseInt(txfTlfNr.getText()), txfAdresse.getText());
                controller.setAfleveringsDato(udlejning, afleveringsPicker.getValue());
                controller.setUdleveringsDato(udlejning, udleveringspicker.getValue());
                controller.setKundeP??Udlejning(kunde, udlejning);
                controller.setBetalingsformP??Udlejning(cbxBetalingsform.getSelectionModel().getSelectedItem(), udlejning);
            } else if (rbTidligereKunde.isSelected() && kundeListView.getSelectionModel().getSelectedItem() != null) {
                controller.setKundeP??Udlejning(kundeListView.getSelectionModel().getSelectedItem(), udlejning);
                controller.setAfleveringsDato(udlejning, afleveringsPicker.getValue());
                controller.setUdleveringsDato(udlejning, udleveringspicker.getValue());
                controller.setBetalingsformP??Udlejning(cbxBetalingsform.getSelectionModel().getSelectedItem(), udlejning);
            }
            this.hide();
        } catch (DateTimeException d){
            lblError.setText("Afleveringsdatoen skal v??re efter udlevering");
        }
    }


}
