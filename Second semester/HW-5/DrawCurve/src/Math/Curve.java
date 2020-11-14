package Math;

import javafx.geometry.Point2D;

import userInterface.Scrim;

import java.util.ArrayList;

public abstract class Curve {
    private float left;
    private float right;
    public String name;

    public abstract ArrayList<Double> valueY(double x);

    public Curve(Scrim scrim) {
        this.left = (float) -(scrim.getWidth() / 2);
        this.right = (float) (scrim.getWidth() / 2);
    }
    @Override
    public String toString() {
        return name;
    }

    public ArrayList<Point2D>[] qPoints(int step) {
        double k = 1000;
        double grid = (right - left) / (step * k);
        ArrayList<Point2D> first = new ArrayList<>();
        ArrayList<Point2D> second = new ArrayList<>();
        ArrayList<Point2D> third = new ArrayList<>();
        ArrayList<Point2D> fourth = new ArrayList<>();
        for (double x = left; x < right; x += grid) {
            ArrayList<Double> values = valueY(x / step);
            for (double standardY : values) {
                double y = standardY * step;
                Point2D pair = new Point2D(x,standardY * step);
                if (y >= 0 && x >= 0) {
                    first.add(pair);
                }
                if (y >= 0 && x < 0) {
                    second.add(pair);
                }
                if (y < 0 && x < 0) {
                    third.add(pair);
                }
                if (y < 0 && x > 0) {
                    fourth.add(pair);
                }
            }
        }
        return new ArrayList[] {first, second, third, fourth};
    }
}
