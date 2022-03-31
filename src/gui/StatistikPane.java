package gui;

import application.controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class StatistikPane extends GridPane {

    private DatePicker datePickerSalgForDag;
    private RadioButton r1;


    public StatistikPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        Controller controller = new Controller();


        r1 = new RadioButton("Indtjeneste for dag");
        r1.setAlignment(Pos.TOP_CENTER);

        HBox hBox = new HBox(r1);
        this.add(hBox,2,6,2,1);

        datePickerSalgForDag = new DatePicker();
        this.add(datePickerSalgForDag, 3, 1);
    }
}
