package Test;

import App.DrawingArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class DrawingTest extends ApplicationTest {
    private Button incBut, decBut;
    private ComboBox<String> cmb;
    private DrawingArea drawingArea;

    @Before
    public void setUp() {
        incBut = find("#incBut");
        decBut = find("#decBut");
        cmb = find("#cmb");
        drawingArea = find("#drawingArea");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/App/UI.fxml"));
        primaryStage.setTitle("Drawing curves");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public <T extends Node> T find(final String query) {
        return lookup(query).query();
    }

    @Test
    public void testDrawing() {
        Assert.assertTrue(decBut.isDisable());
        Assert.assertTrue(incBut.isDisable());
        clickOn(cmb);
        clickOn("Ellipse: b^2*X^2 + a^2*Y^2 = a^2*b^2");
        Assert.assertFalse(decBut.isDisable());
        Assert.assertFalse(incBut.isDisable());
        clickOn(cmb);
        clickOn("Hyperbola: b^2*X^2 - a^2*Y^2 = 1");
        clickOn(cmb);
        clickOn("Folium: X^3 + Y^3 - 3*a*X*Y = 0");
    }

    @Test
    public void buttonsTest() {
        Assert.assertEquals(2d, drawingArea.getCoordinateStep(), 0.001);
        clickOn(cmb);
        clickOn("Hyperbola: b^2*X^2 - a^2*Y^2 = 1");
        clickOn(decBut);
        Assert.assertEquals(1.6d, drawingArea.getCoordinateStep(), 0.001);
        clickOn(incBut);
        Assert.assertEquals(1.92d, drawingArea.getCoordinateStep(), 0.001);
        for (int i = 0; i < 5; ++i) {
            clickOn(incBut);
        }
        Assert.assertEquals(4.777d, drawingArea.getCoordinateStep(), 0.001);
        for (int i = 0; i < 10; ++i) {
            clickOn(decBut);
        }
        Assert.assertEquals(0.5129d, drawingArea.getCoordinateStep(), 0.001);
    }

    @After
    public void close() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}