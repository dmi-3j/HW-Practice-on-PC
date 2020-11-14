package userInterface;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import Math.Curve;
import Math.Ellipse;
import Math.Hyperbola;
import Math.Parabola;

public class Controller<actionEvent> {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private ComboBox<Curve> curveBox;

    @FXML
    private AnchorPane pane;

    @FXML
    private Pane curvePane;

    private Scrim scrim;
    @FXML
    void initialize() {
        scrim = new Scrim(curvePane);
        pane.getChildren().add(scrim);

        ArrayList<Curve> options = new ArrayList<>();
        options.add(new Ellipse(0.5d, 1d, scrim));
        options.add(new Ellipse(1, 0.5d, scrim));
        options.add(new Ellipse(1,1, scrim));
        options.add(new Hyperbola(-2, 3, scrim));
        options.add(new Hyperbola(4.5, -5, scrim));
        options.add(new Parabola(0.5d, scrim));
        options.add(new Parabola(-10d, scrim));

        curveBox.setItems(FXCollections.observableArrayList(options));

        curveBox.setOnAction(actionEvent -> {
            scrim.qDraw(curveBox.getValue());
        });

        button1.setOnAction(actionEvent -> {
            scrim.zoomPlus();
            if (scrim.curveHavePrinted) {
                scrim.drawAll(curveBox.getValue());
            } else {
                scrim.drawPreAll();
            }
        });

        button2.setOnAction(actionEvent -> {
            scrim.zoomMinus();
            if (scrim.curveHavePrinted) {
                scrim.drawAll(curveBox.getValue());
            } else {
                scrim.drawPreAll();
            }
        });
    }
}
