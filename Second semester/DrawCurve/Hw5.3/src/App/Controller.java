package App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Math.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ComboBox<String> cmb;
    @FXML
    private Button incBut = new Button();
    @FXML
    private Button decBut = new Button();
    @FXML
    private DrawingArea drawingArea = new DrawingArea();

    private final String ellipse = "Ellipse: b^2*X^2 + a^2*Y^2 = a^2*b^2";
    private final String hyperbola = "Hyperbola: b^2*X^2 - a^2*Y^2 = 1";
    private final String folium = "Folium: X^3 + Y^3 - 3*a*X*Y = 0";

    public void draw() {
        decBut.setDisable(false);
        incBut.setDisable(false);
        switch (cmb.getSelectionModel().getSelectedItem()) {
            case ellipse:
                drawingArea.drawChart(Ellipse.class);
                break;
            case folium:
                drawingArea.drawChart(Folium.class);
                break;
            case hyperbola:
                drawingArea.drawChart(Hyperbola.class);
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList(ellipse, folium, hyperbola);
        cmb.setItems(items);
        decBut.setOnAction(actionEvent -> zoomMinus());
        incBut.setOnAction(actionEvent -> zoomPlus());
        decBut.setDisable(true);
        incBut.setDisable(true);
    }

    public void zoomPlus() {
        drawingArea.setCoordinateStep(1.2);
        draw();
    }

    public void zoomMinus() {
        drawingArea.setCoordinateStep(0.8);
        draw();
    }

}