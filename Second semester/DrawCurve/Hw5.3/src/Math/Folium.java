package Math;

import javafx.geometry.Point2D;

public class Folium extends Curve {
    private double a = 20;
    private double w, h;
    private double x, y;
    private double p, newStep, step;

    public Folium(double step, double height, double width) {
        w = width / 2;
        h = height / 2;
        x = 0;
        y = 0;
        p = 1e-2 / step;
        newStep = 1e-2 / step;
        this.step = step;
        a = a * step;
    }
    private double fx (double p, double a){
        return 3 * a * p / (1 + p * p * p);
    }
    private double fy (double p, double a){
        return 3 * a * p * p / (1 + p * p * p);
    }

    public Point2D returnNext(Point2D prevP) {
        x = fx(p, a);
        y = fy(p, a);
        if (prevP == null) {
            p += newStep;
            return new Point2D(w + x, h - y);
        }
        if (p < 50 * step && p > 0) {
            if (dist(prevP, new Point2D(x + w, h - y)) > 1e-1 / step) {
                newStep *= (1 - 1e-1 / step);
            }
            else {
                newStep *= (1 + 1e-1 / step);
            }
            if (Math.abs(x) > w || Math.abs(y) > h) {
                newStep = 1e-2;
            }
            p += newStep;
            if (p >= 50 * step) {
                p = -1 - 1e-4 / step;
                newStep = 1e-4 / step;
            }
            return new Point2D(w + x, h - y);
        }
        if (p > -50 * step && p < -1) {
            if (dist(prevP, new Point2D(x + w, h - y)) > 1e-4 / step) {
                newStep *= (1 - 1e-4 / step);
            }
            else {
                newStep *= (1 + 1e-4 / step);
            }
            if (Math.abs(x) > w || Math.abs(y) > h) {
                newStep = 1e-3 / 2;
            }
            p -= newStep;
            if (p <= -50 * step) {
                p = -1 + 1e-4 / step;
                newStep = 1e-4 / step;
            }
            return new Point2D(w + x, h - y);
        }
        if (p < 0 && p > -1) {
            if (dist(prevP, new Point2D(x + w, h - y)) > 1e-4 / step) {
                newStep *= (1 - 1e-4 / step);
            }
            else {
                newStep *= (1 + 1e-4 / step);
            }
            if (Math.abs(x) > w || Math.abs(y) > h) {
                newStep = 1e-3 / 5;
            }
            p += newStep;
            if (p >= 0) {
                p = -1;
            }
            return new Point2D(x + w, h - y);
        }
        return null;
    }
}