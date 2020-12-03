package Test;

import Math.Curve;
import Math.Ellipse;
import Math.Hyperbola;
import Math.Folium;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathTest {

    private Curve ellipse;
    private Curve folium;
    private Curve hyperbola;
    @Before
    public void setUp() {
        ellipse = new Ellipse(2d, 500, 600);
        folium = new Folium(2d, 500, 600);
        hyperbola = new Hyperbola(2d, 500, 600);
    }

    @Test
    public void returnNextEllipseTest() {
        Point2D point1 = ellipse.returnNext(null);
        Point2D point2 = ellipse.returnNext(point1);
        Point2D point3 = ellipse.returnNext(point2);
        assertEquals(point1.getX(), -50 + 300, 0.001);
        assertEquals(point1.getY(), 0 + 250, 0.001);
        assertEquals(point2.getX(), -49.995 + 300, 0.001);
        assertEquals(point2.getY(), -0.424253 + 250, 0.001);
        assertEquals(point3.getX(), -49.99025 + 300, 0.001);
        assertEquals(point3.getY(), 0.592423 + 250, 0.001);
    }

    @Test
    public void returnNextHyperbolaTest() {
        Point2D point1 = hyperbola.returnNext(null);
        Point2D point2 = hyperbola.returnNext(point1);
        Point2D point3 = hyperbola.returnNext(point2);
        assertEquals(point1.getY(), -250 + 250, 0.001);
        assertEquals(point1.getX(), 111.263614 + 300, 0.001);
        assertEquals(point2.getY(), -249.995 + 250, 0.001);
        assertEquals(point2.getX(), -111.261551 + 300, 0.001);
        assertEquals(point3.getY(), -249.99025 + 250, 0.001);
        assertEquals(point3.getX(), 111.25959 + 300, 0.001);
    }

    @Test
    public void returnNextFoliumTest() {
        Point2D point1 = folium.returnNext(null);
        Point2D point2 = folium.returnNext(point1);
        Point2D point3 = folium.returnNext(point2);
        assertEquals(point1.getX(), 0.599999 + 300, 0.001);
        assertEquals(point1.getY(), -0.002999 + 250, 0.001);
        assertEquals(point2.getX(), 1.199999 + 300, 0.001);
        assertEquals(point2.getY(), -0.011999 + 250, 0.001);
        assertEquals(point3.getX(), 1.769999 + 300, 0.001);
        assertEquals(point3.getY(), -0.026107 + 250, 0.001);

    }
}