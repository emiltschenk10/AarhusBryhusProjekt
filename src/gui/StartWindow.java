package gui;

import application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {


    @Override
    public void init() {
        Controller.init();
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

    // -----------------------------------------------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTapPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTapPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tabSalg = new Tab("Salg");
        tabPane.getTabs().add(tabSalg);

        KonferencePane konferencePane = new KonferencePane();
        tabSalg.setContent(konferencePane);

        /*
        Tab tabSalg = new Tab("Udlejning");
        tabPane.getTabs().add(tabSalg);

        DeltagerPane deltagerPane = new DeltagerPane();
        tabSalg.setContent(deltagerPane);

        Tab tabHoteller = new Tab("Hoteller");
        tabPane.getTabs().add(tabHoteller);

        HotelPane hotelPane = new HotelPane();
        tabHoteller.setContent(hotelPane);
         */
    }
}
