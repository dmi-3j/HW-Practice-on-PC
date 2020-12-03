package Math;

import javafx.geometry.Point2D;

public class Hyperbola extends Curve {
    private int a = 15;
    private int b = 35;
    private double w, h;
    private double x, y;
    private double step, newStep;

    public Hyperbola(double step, double height, double width) {
        w = width / 2;
        h = height / 2;
        y = -h;
        newStep = 1e-2 / step;
        this.step = step;
    }

    public Point2D returnNext(Point2D prevP) {
        if (y > h) {
            return null;
        }
        x = Math.sqrt(a * a * step * step + a * a * y * y / (b * b));
        if (prevP == null) {
            y += newStep;
            return new Point2D(w + x, h + y - newStep);
        }
        if (prevP.getX() - w < 0) {
            x = -x;
        }
        if (dist(prevP, new Point2D( w + x, h + y)) > 1e-2 / step) {
            newStep *= 1 - 1e-2 / step;
        }
        else {
            newStep *= 1 + 1e-2 / step;
        }
        y += newStep;
        return new Point2D(w - x, h + y - newStep);
    }
}