package Math;

import javafx.geometry.Point2D;

public class Ellipse extends Curve {
    private int a = 25;
    private int b = 15;
    private double w, h;
    private double x, y;
    private double step, newStep;

    public Ellipse(double step, double height, double width) {
        w = width / 2;
        h = height / 2;
        x = -a * step;
        y = 0;
        newStep = 1e-2 / step;
        this.step = step;
    }

    @Override
    public Point2D returnNext(Point2D prevP) {
        if (x > a*step) {
            return null;
        }
        y = Math.sqrt(b * b * step * step - b * b * x * x / (a * a));
        if (prevP == null) {
            x += newStep;
            return (new Point2D(w + x - newStep, h));
        }
        if (prevP.getY() - h < 0) {
            y = - y;
        }
        if (dist(prevP, new Point2D(x + w, h + y)) > 1e-2 / step) {
            newStep *= 1 - 1e-1 / step;
        }
        else {
            newStep *= 1 + 1e-1 / step;
        }
        if (Math.abs(x) > w || Math.abs(y) > h) {
            newStep = 1e-2;
        }
        x += newStep;
        return (new Point2D(w + x - newStep, h - y));
    }
}
