package userInterface;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import Math.Curve;

import java.util.ArrayList;

public class Scrim extends Canvas {

    private Pane pane;
    private int step = 1;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private double zoom = 1d;
    private ArrayList<Polyline> polylines;
    public boolean curveHavePrinted;

    public Scrim(Pane pane) {
        this.pane = pane;
        setWidth(WIDTH);
        setHeight(HEIGHT);
        polylines = new ArrayList<Polyline>();
        setLayoutX(0);
        setLayoutY(0);
        step = (int) ((getWidth() / 2) / 8 * zoom);
        fDraw();
    }

    public void deleteCurve() {
        for (Polyline polyline : polylines) {
            pane.getChildren().remove(polyline);
        }
    }

    public void qDraw(ArrayList<Point2D> pairs) {
        double zeroX = getWidth() / 2;
        double zeroY = getHeight() / 2;
        Polyline polyline = new Polyline();
        for (Point2D dot : pairs) {
            double x = dot.getX() + zeroX;
            double y = dot.getY() + zeroY;
            if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
                polyline.getPoints().add(x);
                polyline.getPoints().add(y);
            }
        }
        polyline.setStroke(Color.BLACK);
        pane.getChildren().add(polyline);
        polylines.add(polyline);
    }

    public void qDraw(Curve curve) {
        curveHavePrinted = true;
        deleteCurve();
        ArrayList<Point2D>[] all = curve.qPoints(step);
        qDraw(all[0]);
        qDraw(all[1]);
        qDraw(all[2]);
        qDraw(all[3]);
    }

    public void fDraw() {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setLineWidth(0.5);
        graphicsContext.strokeLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        graphicsContext.strokeLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        double zoomConverted = 1;
        if (zoom >= 4) {
            zoomConverted = 0.5d;
        }
        double distention = step * zoomConverted;
        float width = (float) getWidth();
        float height = (float) getHeight();
        for (double x = width/2 + distention; x < width; x += distention) {
            graphicsContext.strokeLine(x, height/2 - 2, x, height/2 + 2);
            printNumX((x - width/2) / step);
        }
        for (double x = width/2 - distention; x >= 0; x -= distention) {
            graphicsContext.strokeLine(x, height/2 - 2, x, height/2 + 2);
            printNumX((x - width/2) / step);
        }
        for (double y = height/2 + distention; y < height; y += distention) {
            graphicsContext.strokeLine(width/2 - 2, y, width/2 + 2, y);
            printNumY((y - height/2) / step);
        }
        for (double y = height/2 - distention; y >= 0; y -= distention) {
            graphicsContext.strokeLine(width/2 - 2, y, width/2 + 2, y);
            printNumY((y - height/2) / step);
        }
    }
    public void zoomPlus() {
        if (zoom  >= 5d) {
            zoom = 5.0d;
        } else {
            zoom += 0.1d;
        }
        if (zoom  >= 5.0d) {
            zoom = 5.0d;
        }
        System.out.println(zoom);
    }

    public void zoomMinus() {
        if (zoom <= 0.6d) {
            zoom = 0.6d;
        } else {
            zoom -= 0.1d;
        }
        if (zoom <= 0.6d) {
            zoom = 0.6d;
        }
        System.out.println(zoom);
    }

    private void printNumX(double num) {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setLineWidth(0.5);
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillText(Double.toString(num), getWidth() / 2 + num * step - 5, getHeight() / 2 + 15);
    }

    private void printNumY(double num) {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setLineWidth(0.5);
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillText(Double.toString(-num), getWidth() / 2 + 5, getHeight() / 2 + num * step + 5);
    }

    public void fDelete() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
    }

    public void drawAll(Curve curve) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        fDelete();
        step = (int) ((getWidth() / 2) / 8 * zoom);
        fDraw();
        qDraw(curve);
    }

    public void drawPreAll() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        fDelete();
        step = (int) ((getWidth() / 2) / 8 * zoom);
        fDraw();
    }
}
