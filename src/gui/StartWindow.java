package gui;

import application.controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {

//    @Override
//    public void init() {
//        Controller.init();
//    }

    // -----------------------------------------------------------------------------------------------------------------
    private Controller controller = Controller.getInstance();


    private RedigerPane redigerPane;
    private void initContent(BorderPane pane) {
        controller.loadStorage();
        TabPane tabPane = new TabPane();
        this.initTapPane(tabPane);
        pane.setCenter(tabPane);


    }

    private void initTapPane(TabPane tabPane) {
        Controller controller = Controller.getInstance();
        controller.loadStorage();

        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tabSalg = new Tab("Salg");
        tabPane.getTabs().add(tabSalg);

        SalgsPane salgsPane = new SalgsPane();
        tabSalg.setContent(salgsPane);




        Tab tabUdlejning = new Tab("Udlejning");
        tabPane.getTabs().add(tabUdlejning);

        UdlejningsPane udlejningsPane = new UdlejningsPane();
        tabUdlejning.setContent(udlejningsPane);

        Tab tabSortiment = new Tab("Sortiment");
        tabPane.getTabs().add(tabSortiment);

        SortimentPane sortimentPane = new SortimentPane();
        tabSortiment.setContent(sortimentPane);

        Tab tabPrisliste = new Tab("Prisliste");
        tabPane.getTabs().add(tabPrisliste);

        PrislistePane prislistePane = new PrislistePane();
        tabPrisliste.setContent(prislistePane);

        Tab tabRediger = new Tab("Ordreoversigt");
        tabPane.getTabs().add(tabRediger);

        RedigerPane redigerPane = new RedigerPane();
        tabRediger.setContent(redigerPane);
        this.redigerPane = redigerPane;

        Tab tabStatistik = new Tab("Statistik");
        tabPane.getTabs().add(tabStatistik);

        StatistikPane statistikPane = new StatistikPane();
        tabStatistik.setContent(statistikPane);

        Tab tabArrangement = new Tab("Arrangement");
        tabPane.getTabs().add(tabArrangement);

        ArrangementPane arrangementPane = new ArrangementPane();
        tabArrangement.setContent(arrangementPane);

        ChangeListener<Tab> listener = (ov,gammelTab,nyTab) -> this.selectedTabChanged();
        tabPane.getSelectionModel().selectedItemProperty().addListener(listener);

    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Kasseapparat");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void stop(){
        Controller controller = Controller.getInstance();
        controller.saveStorage();
    }

    private void selectedTabChanged(){
        redigerPane.updateLists();
    }

}
